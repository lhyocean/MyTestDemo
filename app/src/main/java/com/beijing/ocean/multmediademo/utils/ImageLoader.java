package com.beijing.ocean.multmediademo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by lyq on 2016/6/07.
 * 加载图片的工具类 使用单例模式
 */
public class ImageLoader {

    public static final int ADD_TASK = 0x011;
    /**
     * 图片缓存的核心类
     */
    private LruCache<String,Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;

    /**
     * 默认的线程的数量
     */
    private final int DEFAULT_THREAD_COUNT = 1;


    /**
     * 任务队列
     */

    private LinkedList<Runnable> mTaskQueue;


    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    /**
     * 轮询线程中的handler
     */
    private Handler mPoolThreadHandler;

    /**
     * UI线程中的handler
     */
    private Handler mUIHandler;

    /**
     * 在有多个线程 并且线程中使用了另一个线程中的变量时,应注意线程并发问题
     *使用信号量机制 解决并发问题
     *
     * */

    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);

    private Semaphore mSemaphoreThreadPool;



    /**
     * 队列的调度方式 FIFO(先进先出)  LIFO(后进先出)
     */
    private Type mType = Type.LIFO;

    public enum Type{
        LIFO,FIFO;
    }



    private static ImageLoader mInstance;
    private ImageLoader(int threadCount,Type type) {
        init(threadCount,type);
    }


    /**
     * 初始化ImageLoader
     * @param threadCount 线程的数量
     * @param type 线程的类型
     */
    private void init(int threadCount, Type type) {


        //后台轮询线程
        mPoolThread = new Thread(){

            @Override
            public void run() {

                Looper.prepare();

                mPoolThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取一个任务去执行
                        if(mThreadPool!=null){
                            mThreadPool.execute(getTask());
                        }

                        try {
                            //线程执行的个数超过threadCount时,线程阻塞
                            if(mSemaphoreThreadPool!=null){
                                mSemaphoreThreadPool.acquire();
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //有可能还没有初始化便执行addTask方法 所以初始化后 发送一个信号
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

        //获取应用的最大的内存数量
        int maxMemory = (int)Runtime.getRuntime().maxMemory();
        //LruCatce所占据的内存为最大内存的1/8
        int mLruCacheSize = maxMemory/8;


        mLruCache = new LruCache<String,Bitmap>(mLruCacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                //返回每张图片的像素数

                return value.getRowBytes()*value.getHeight();
            }
        };


        //初始化线程
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mSemaphoreThreadPool = new Semaphore(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;




    }




    /**
     * 获取线程任务的方法
     * @return
     */
    private Runnable getTask() {
        if(mType== Type.FIFO){
            return mTaskQueue.removeFirst();
        }else if(mType == Type.LIFO){
            return mTaskQueue.removeLast();
        }

        return null;

    }

    public static ImageLoader getInstance(int threadCount ,Type type){

        if(mInstance==null){
            synchronized (ImageLoader.class){
                if(mInstance==null){
                    mInstance = new ImageLoader(threadCount,type);
                }
            }

        }

        return mInstance;


    }


    public void loadImage(final String path, final ImageView imageView){

        imageView.setTag(path);

        if(mUIHandler==null){
            mUIHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //获取得到的图片,为imageView 回调设值
                    ImgBeanHolder holder = (ImgBeanHolder)msg.obj;
                    ImageView imageView = holder.imageView;
                    String path = holder.path;
                    Bitmap bitmap = holder.bitmap;

                    if(imageView.getTag().toString().equals(path)){
                        imageView.setImageBitmap(bitmap);
                    }


                }
            };
        }



        //根据path 获取缓存中的bitmap

        Bitmap bitmap = getBitmapFromLruCache(path);

        if(bitmap!=null){
            //将图片以及图片的详细地址传递给UIHandler

            refreshBitmap(path, imageView, bitmap);

        }else{
            System.out.println("bitmap   is null");
            addTask(new Runnable() {
                @Override
                public void run() {
                    //加载图片

                    //1.先对图片进行压缩
                    //;获取图片显示的宽高
                    ImageSize imageSize = getImageSize(imageView);

                    //对图片进行压缩

                    Bitmap bm = decodeSampleBitmapFromPath(path, imageSize.imageWidth,imageSize.imageHeight);
                    //将图片加入到缓存中

                    addBitmapToLruCache(path, bm);

                    //通知主线程 刷新显示图片

                    refreshBitmap(path,imageView,bm);

                    //每执行完一个任务释放一个信号量
                    mSemaphoreThreadPool.release();

                }
            });
        }


    }




    /**
     * 通知主线程的handler跟新ui
     * @param path
     * @param imageView
     * @param bitmap
     */
    private void refreshBitmap(String path, ImageView imageView, Bitmap bitmap) {
        Message message = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bitmap;
        holder.imageView = imageView;
        holder.path = path;

        message.obj = holder;

        mUIHandler.sendMessage(message);
    }

    /**
     * 将图片加入到缓存中
     * @param path:图片的路径
     * @param bm :图片对象
     */
    private void addBitmapToLruCache(String path, Bitmap bm) {

        if(getBitmapFromLruCache(path)==null){

            if(bm!=null){
                mLruCache.put(path,bm);
            }
        }

    }

    /**
     * 根据需求的宽高值 对图片进行压缩
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */

    private Bitmap decodeSampleBitmapFromPath(String path, int reqWidth, int reqHeight) {

        /**
         *获取图片的options 根据options获取对应真实图片的宽和高
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置为true 解析图片时只返回图片的宽高值
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = caculateSampleSize(options,reqWidth,reqHeight);
//        System.out.print("---------------- options.inSampleSize ------"+ options.inSampleSize );

        //再次解析图片 此时需要真正的图片
        options.inJustDecodeBounds = false;//解析图片时返回真正的图片 (此时是压缩后的图片)
        Bitmap bitmap =    BitmapFactory.decodeFile(path, options);

        return bitmap;
    }


    /**
     * 计算图片的缩放比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int realWidth = options.outWidth;
        int realHeight = options.outHeight;
        int inSampleSize =1;
        if(realWidth>reqWidth && realHeight>reqHeight){
            //为了节约内存使用了Max
            int widthRatio = Math.round((float) realWidth / (float) reqWidth);
            int heightRatio = Math.round((float) realHeight / (float) reqHeight);
              inSampleSize = Math.max(widthRatio,heightRatio);
        }

        return  inSampleSize;
    }

    /**
     * 获取图片的尺寸
     * @param imageView
     * @return
     */
    private ImageSize getImageSize(ImageView imageView) {
        DisplayMetrics metrics = imageView.getContext().getResources().getDisplayMetrics();

        //控件是否设置了宽度
        int width = imageView.getWidth();

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();

        if(width<=0){
            //有没有给控件设置固定的宽度
            width = lp.width;
        }

        if(width<=0){
            //控件的最大可能宽度      imageView.getMaxWidth 在Api16中可以使用 如果小于16不能使用 所以使用反射机制 获取对应的值
//            width = imageView.getMaxWidth();
            width = getImageViewFile(imageView,"mMaxWidth");//其中的 mMaxWidth实在源码中查询的属性
        }

        if(width<=0){
            //屏幕的宽度
            width =metrics.widthPixels;
        }


        //高度

        //控件是否设置了宽度
        int height = imageView.getWidth();



        if(height<=0){
            //有没有给控件设置固定的宽度
            height = lp.height;
        }

        if(height<=0){
            //控件的最大可能宽度
//            height = imageView.getMaxHeight();
            height = getImageViewFile(imageView, "mMaxHeight");
        }

        if(height<=0){
            //屏幕的宽度
            height =metrics.heightPixels;
        }

        ImageSize imageSize = new ImageSize();

        imageSize.imageHeight = height;
        imageSize.imageWidth = width;

        return imageSize;
    }




    private int getImageViewFile(Object obj, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(obj);
            if(fieldValue>0&&fieldValue<Integer.MAX_VALUE){
                value = fieldValue;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    public class ImageSize {
        int imageHeight;
        int imageWidth;

        @Override
        public String toString() {
            return "ImageSize{" +
                    "imageHeight=" + imageHeight +
                    ", imageWidth=" + imageWidth +
                    '}';
        }
    }


    /**
     * 增加一个任务
     * @param runnable
     */
    private  synchronized  void addTask(Runnable runnable) {



        try {
            if(mPoolThreadHandler==null){
                //线程阻塞 等待信号量发送过来
                mSemaphorePoolThreadHandler.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mTaskQueue.add(runnable);
        mPoolThreadHandler.sendEmptyMessage(ADD_TASK);



    }


    /**
     * 根据 图片的地址 获取缓存中的图片
     * @param path :图片的路径
     * @return
     */
    private Bitmap getBitmapFromLruCache(String path) {

        return mLruCache.get(path);
    }


    private class ImgBeanHolder{
        String path;
        Bitmap bitmap;
        ImageView imageView;

    }

}

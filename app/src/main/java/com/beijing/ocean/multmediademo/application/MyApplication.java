package com.beijing.ocean.multmediademo.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by admin on 2016/11/12.
 */
public class MyApplication  extends Application{
    public static  MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

        initImageLoader(instance);

    }

    private void initImageLoader(Context context) {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.NONE).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new UsingFreqLimitedMemoryCache((int) (Runtime.getRuntime().maxMemory() / 8)))
                .memoryCacheSizePercentage(13) // default
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // .writeDebugLogs() // Remove for release app 日志log
                .build();

        ImageLoader.getInstance().init(config);


    }
}

package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.widge.ToastController;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *      ocean 2016/10/12
 */
public class PlayVideoActivity extends Activity implements View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, View.OnTouchListener ,GestureDetector.OnGestureListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnInfoListener {

    private float mCurBrightness = INVALID_VALUE;
    private int mCurVolume = INVALID_VALUE;
    public static final int INVALID_VALUE=-1;
    @Bind(R.id.video_relative)
    RelativeLayout mLayoutVideoContainer;

    @Bind(R.id.image_left_back)
    ImageView imageLeftBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.video_view)
    VideoView mVideoView;

    //控制按钮 // TODO: 2016/10/17

    @Bind(R.id.video_play_middle)
    ImageView mImagePlayMiddle;

    @Bind(R.id.act_video_tofull)
    ImageView mImageFullScreen;
    @Bind(R.id.act_video_time)
    TextView mTextCurrentTime;
    @Bind(R.id.act_video_totaltime)
    TextView mTextTotalTime;
    @Bind(R.id.seek_bar)
    SeekBar mseekBarrPlayProgress;

    // 是否是按下的标识，默认为其他动作，true为按下标识，false为其他动作
    private boolean isDownTouch;
    // 是否声音控制,默认为亮度控制，true为声音控制，false为亮度控制
    private boolean isVolume;
    // 是否横向滑动，默认为纵向滑动，true为横向滑动，false为纵向滑动
    private boolean isLandscape;


    private int maxVolume;
    private ToastController lightController,volumnController;

    private static  String mVideoPlayUrl="http://wirrorcdn.jikexueyuan.com/jiuye%2Fvideo%2Fandroidshow.mp4";





    private GestureDetector mGestureDetector;
    private int mCurrentPosition;
    private boolean mIsPlayCompleted;
    private boolean isPause=false;
    private boolean mDragging=false;
    private long mDuration;
    boolean isNetConected=false;
    private AudioManager mAudioManager;

    @Bind(R.id.ll_control)
    LinearLayout llControl;
    @Bind(R.id.rl)
    RelativeLayout  rl;
    @Bind(R.id.fl_touch_layout)
    FrameLayout mFlTouchLayout;
    @Bind(R.id.tv_volume)
    TextView mTvVolume;
    @Bind(R.id.tv_brightness)
     TextView mTvBrightness;



    long delayMillis=1000;
    private  static  final int showDefaultTime=3000;// 控制条显示延时
    private static  final  int hideWaitDialogTimeDelay=10000;//
    private static final int SHOW_PROGRESS = 2; //显示进度
    private static final int HIDE_CONTROL = 1;    //隐藏控制条
    private static final int HIDE_WAIT_DIALOG=3;  //隐藏等待dialog

    NetworkConnectChangedReceiver mNetReceiver;

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            long pos;
            switch (msg.what) {
                case SHOW_PROGRESS:
                    setCurrentProgress();
                    break;
                case HIDE_CONTROL:
                    hide();
                    break;
                case HIDE_WAIT_DIALOG:

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);

        int random=new Random().nextInt(Commen.VIDEOURLS.length);
        mVideoPlayUrl= Commen.VIDEOURLS[random];


        try {
            int e = Settings.System.getInt(PlayVideoActivity.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            float progress = 1.0F * (float) e / 255.0F;
            WindowManager.LayoutParams layout = PlayVideoActivity.this.getWindow().getAttributes();
            layout.screenBrightness = progress;
            PlayVideoActivity.this.getWindow().setAttributes(layout);
        } catch (Settings.SettingNotFoundException var7) {
            var7.printStackTrace();
        }


        //注册网络监听
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(mNetReceiver, filter);

        mAudioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumnController = new ToastController(this, ToastController.Type.VOLUMN_TYPE);
        lightController = new ToastController(this, ToastController.Type.LIGHT_TYPE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        initVideo();
        initData();

    }
    private void hide() {
        if (mFlTouchLayout!=null){
            mFlTouchLayout.setVisibility(View.GONE);
        }

        if (!mDragging&&llControl!=null){
            llControl.setVisibility(View.GONE);
        }
        if (!mDragging&&mImagePlayMiddle!=null){
            mImagePlayMiddle.setVisibility(View.GONE);
        }
    }
    private  void  show(int timeout){

        mHandler.sendEmptyMessage(SHOW_PROGRESS);
        if (llControl!=null){
            llControl.setVisibility(View.VISIBLE);
        }
        if (mImagePlayMiddle!=null){
            mImagePlayMiddle.setVisibility(View.VISIBLE);
        }
        if (timeout!=0){
            mHandler.removeMessages(HIDE_CONTROL);
            mHandler.sendMessageDelayed(mHandler.obtainMessage(HIDE_CONTROL),timeout);
        }
    }


    private void setCurrentProgress() {
        if (mVideoView==null){
            return ;
        }

        long position = mVideoView.getCurrentPosition();
        long duration = mVideoView.getDuration();
        int percent = mVideoView.getBufferPercentage();

        if (mseekBarrPlayProgress != null) {
            if (duration > 0) {
                long pos = 1000L * position / duration;
                mseekBarrPlayProgress.setProgress((int) pos);
            }
            mseekBarrPlayProgress.setSecondaryProgress(percent * 10);
        }
        mDuration = duration;
        if (mTextCurrentTime!=null){
            mTextCurrentTime.setText(transferTime(position)+"");
        }
        if (mTextTotalTime!=null){
            mTextTotalTime.setText(transferTime(duration)+"");
        }

        if (isNetConected){
            if (mVideoView.isPlaying()&&!mDragging){
                Message msg = mHandler.obtainMessage(SHOW_PROGRESS);
                mHandler.sendMessageDelayed(msg, delayMillis );
            }

        }else {

            if (mVideoView.isPlaying()&&!mDragging){
                int curPlayPercent=(int)(position*100/duration);

                if (percent-curPlayPercent>2){
                    Message msg = mHandler.obtainMessage(SHOW_PROGRESS);
                    mHandler.sendMessageDelayed(msg, delayMillis );
                }else {
                    if (mVideoView.canPause()){
                        mVideoView.pause();
                        mCurrentPosition=(int)position;
                        isPause=true;
                    }
                }

            }
        }
    }

    private void initVideo() {

        mGestureDetector = new GestureDetector(this, this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnTouchListener(this);
        mVideoView.setOnInfoListener(this);
        mseekBarrPlayProgress.setOnSeekBarChangeListener(this);
        mImagePlayMiddle.setOnClickListener(this);
        mImageFullScreen.setOnClickListener(this);
        mVideoView.setVideoPath(mVideoPlayUrl);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startPlayVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView.isPlaying()){
            if (mVideoView.canPause()){
                mCurrentPosition=mVideoView.getCurrentPosition();
                mVideoView.pause();
            }
        }

    }

    private void startPlayVideo() {

        if (mVideoView.isPlaying()){

            mImagePlayMiddle .setVisibility(View.GONE);
        }else {
            if (!isPause){
                mVideoView.seekTo(mCurrentPosition);
                mTextCurrentTime.setText(transferTime(mCurrentPosition)+"");
                mVideoView.start();

            }
        }

    }







    private void initData() {
        imageLeftBack.setOnClickListener(this);
        llBack.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.image_left_back:
            case R.id.ll_back  :

                //// TODO: 2016/10/17  video播放中处理

                if (Math.abs(getRequestedOrientation()) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    if (mVideoView!=null&&mVideoView.isPlaying()){
                        mCurrentPosition = mVideoView.getCurrentPosition();
                        mVideoView.stopPlayback();
                    }
                    mHandler.removeMessages(SHOW_PROGRESS);
                    this.finish();
                }

                break;

            case R.id.video_play_middle:


                if (mVideoView.isPlaying()){
                    mImagePlayMiddle.setImageResource(R.mipmap.play_video);
                    mCurrentPosition=mVideoView.getCurrentPosition();
                    if (mVideoView.canPause()){
                        mVideoView.pause();
                    }
                    isPause=true;

                }else {
                    if (isNetConected){
                        mImagePlayMiddle.setImageResource(R.mipmap.pause_video);
                        mVideoView.seekTo(mCurrentPosition);
                        mTextCurrentTime.setText(transferTime(mCurrentPosition)+"");
                        mVideoView.start();
                        mHandler.sendEmptyMessage(SHOW_PROGRESS);
                        isPause=false;}
                    else {
                        Toast.makeText(PlayVideoActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                    }

                }
                show(showDefaultTime);

                break;

            case R.id.act_video_tofull:
                if (mVideoView.isPlaying()){
                    if (mVideoView.canPause()){
                        mCurrentPosition=mVideoView.getCurrentPosition();
                        mVideoView.pause();
                    }
                }

                if (Math.abs(getRequestedOrientation()) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                else if (Math.abs(getRequestedOrientation()) == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                show(showDefaultTime);
                break;

        }

    }




    @Override
    public void onCompletion(MediaPlayer mp) {
        // 播放完成
        mIsPlayCompleted = true;
        mCurrentPosition=0;
        mImagePlayMiddle.setImageResource(R.mipmap.play_video);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //  准备回调，当页面Home键返回后也会回调

        if (isPause){

        }else {
            mImagePlayMiddle.setImageResource(R.mipmap.pause_video);
            int totalTime=  mVideoView.getDuration();
            mTextTotalTime.setText(""+transferTime(totalTime));
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
            show(showDefaultTime);
        }
    }

    private String transferTime(long totalTime) {
        int totalSeconds = (int) (totalTime / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        // 失败
        Log.e("tag","what="+what+"extr="+extra);
        mHandler.removeMessages(HIDE_WAIT_DIALOG);

        return true;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:



                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:


                break;
        }

        return false;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_UP){
            _endGesture();
        }

        return mGestureDetector.onTouchEvent(event);
    }

    private void _endGesture() {
        mImagePlayMiddle.setVisibility(llControl.getVisibility());
        mFlTouchLayout.setVisibility(View.GONE);
        mCurVolume=INVALID_VALUE;
        mCurBrightness=INVALID_VALUE;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        isDownTouch=true;

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // 单击一次监听
        show(showDefaultTime);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //todo  手势处理 调节声音等
        show(30000);
        boolean ret=false;
        float rawX1 = e1.getRawX();
        float rawX2 = e2.getRawX();

        float rawY1 = e1.getRawY();
        float rawY2 = e2.getRawY();

        float x = e1.getX();
        float y = e1.getY();
        float x1 = e2.getX();
        float y1 = e2.getY();

        float dx=Math.abs(distanceX);
        float dy =Math.abs(distanceY);

        if (isDownTouch){
            isDownTouch=false;
            isVolume=x>getResources().getDisplayMetrics().widthPixels*0.5f;
        }else {
            mFlTouchLayout.setVisibility(View.GONE);
        }


        if (dx>dy){
            //代表横向移动
            if (mVideoView.isPlaying()){
                int currentPo=mVideoView.getCurrentPosition();
                int dur=mVideoView.getDuration();

                if (rawX1>rawX2){
                    //向左
                    currentPo-=1000;
                    if (currentPo<0){
                        currentPo=0;
                    }
                }else {
                    currentPo+=1000;
                    if (currentPo>dur){
                        currentPo=dur;
                    }
                }

                mVideoView.seekTo(currentPo);
                int max=mseekBarrPlayProgress.getMax();
                mseekBarrPlayProgress.setProgress(currentPo*max/dur);

            }
            ret=true;
        }else if (dx<dy){

            float percent=(y-y1)/mVideoView.getHeight();
            if (isVolume) {
                _onVolumeSlide(percent);
            } else {
                _onBrightnessSlide(percent);
            }

            ret=true;
        }
        return ret;
    }

    private void _onBrightnessSlide(float percent) {
        if (mCurBrightness < 0) {
            mCurBrightness = PlayVideoActivity.this.getWindow().getAttributes().screenBrightness;
            if (mCurBrightness < 0.0f) {
                mCurBrightness = 0.5f;
            } else if (mCurBrightness < 0.01f) {
                mCurBrightness = 0.01f;
            }
        }
        WindowManager.LayoutParams attributes = PlayVideoActivity.this.getWindow().getAttributes();
        attributes.screenBrightness = mCurBrightness + percent;
        if (attributes.screenBrightness > 1.0f) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01f) {
            attributes.screenBrightness = 0.01f;
        }
        _setBrightnessInfo(attributes.screenBrightness);
        PlayVideoActivity.this.getWindow().setAttributes(attributes);

    }

    private void _setBrightnessInfo(float brightness) {
        mImagePlayMiddle.setVisibility(View.GONE);
        if (mFlTouchLayout.getVisibility() == View.GONE) {
            mFlTouchLayout.setVisibility(View.VISIBLE);
        }
        mTvVolume.setVisibility(View.GONE);
        if (mTvBrightness.getVisibility() == View.GONE) {
            mTvBrightness.setVisibility(View.VISIBLE);
        }
        mTvBrightness.setText(Math.ceil(brightness * 100) + "%");
    }

    // 当前音量
    // 当前亮度

    private void _onVolumeSlide(float percent) {
        if (mCurVolume == INVALID_VALUE) {
            mCurVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mCurVolume < 0) {
                mCurVolume = 0;
            }
        }
        int index = (int) (percent * maxVolume) + mCurVolume;
        if (index > maxVolume) {
            index = maxVolume;
        } else if (index < 0) {
            index = 0;
        }
        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        // 变更进度条
        _setVolumeInfo(index);
    }

    private void _setVolumeInfo(int volume) {
       mImagePlayMiddle.setVisibility(View.GONE);

        mTvBrightness.setVisibility(View.GONE);
        if (mFlTouchLayout.getVisibility() == View.GONE) {
            mFlTouchLayout.setVisibility(View.VISIBLE);
        }
        if (mTvVolume.getVisibility() == View.GONE) {
            mTvVolume.setVisibility(View.VISIBLE);
        }
        mTvVolume.setText((volume * 100 / maxVolume) + "%");
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser){
            return;
        }
        int dur=mVideoView.getDuration();
        long newposition=dur*progress/1000;
        mTextCurrentTime.setText(transferTime(newposition)+"");
        mVideoView.seekTo((int)newposition);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mDragging = true;
        mHandler.removeMessages(SHOW_PROGRESS);
        show(30000);// 显示一个长延时
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        mDragging = false;
        mVideoView.seekTo(mVideoView.getDuration()*seekBar.getProgress()/1000);
        mHandler.removeMessages(SHOW_PROGRESS);
        mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS,1000);
        show(showDefaultTime);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 屏幕改变回调
        Log.e("--Main--", "onConfigurationChanged");
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            // 横屏
            LinearLayout.LayoutParams l=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mLayoutVideoContainer.setLayoutParams(l);

            if (!isPause){
                mVideoView.seekTo(mCurrentPosition);
                mTextCurrentTime.setText(transferTime(mCurrentPosition)+"");
                mVideoView.start();
            }

        }else{
            mLayoutVideoContainer.setLayoutParams(rl.getLayoutParams());
            mVideoView.seekTo(mCurrentPosition);
            mTextCurrentTime.setText(transferTime(mCurrentPosition)+"");
            if (!isPause){
                mVideoView.start();

            }
        }
        mHandler.sendEmptyMessage(SHOW_PROGRESS);
        show(showDefaultTime);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (Math.abs(getRequestedOrientation()) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetReceiver);
        if (mVideoView!=null)
            mVideoView.stopPlayback();
    }
    public class NetworkConnectChangedReceiver extends BroadcastReceiver {
        private String getConnectionType(int type) {
            String connType = "";
            if (type == ConnectivityManager.TYPE_MOBILE) {
                connType = "3G网络数据";
            } else if (type == ConnectivityManager.TYPE_WIFI) {
                connType = "WIFI网络";
            }
            return connType;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 监听wifi的打开与关闭，与wifi的连接无关
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                Log.e("TAG", "wifiState:" + wifiState);
                switch (wifiState) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                }
            }
            // 监听wifi的连接状态即是否连上了一个有效无线路由
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    // 获取联网状态的NetWorkInfo对象
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    //获取的State对象则代表着连接成功与否等状态
                    NetworkInfo.State state = networkInfo.getState();
                    //判断网络是否已经连接
                    boolean isConnected = state == NetworkInfo.State.CONNECTED;
                    Log.e("TAG", "isConnected:" + isConnected);
                    if (isConnected) {

                    } else {

                    }
                }
            }

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

                NetworkInfo info = intent
                        .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (info != null) {

                    if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                        if (info.getType() == ConnectivityManager.TYPE_WIFI|| info.getType() == ConnectivityManager.TYPE_MOBILE) {
                            // 连接上
                            isNetConected=true;
                        }
                    } else {

                        mHandler.sendMessageDelayed(mHandler.obtainMessage(HIDE_WAIT_DIALOG),hideWaitDialogTimeDelay);
                        isNetConected=false;
                    }
                }
            }
        }
    }
}

package com.beijing.ocean.multmediademo.view;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by caizhiming on 2015/10/11.
 */
public class XCDanmuView extends RelativeLayout {
    private int mWidth;
    private int mScreenWidth;
    private List<View> mChildList;
    private boolean mIsWorking = false;
    private Context mContext;
    private int mMaxShowNum = 15;
    private int mRowNum = 4;
    private int count=0;
    private int[] mSpeeds = {
            3000,4000,5000,6000
    };
    private int mDelayDuration = 500;
    private int[] mBgResIds = {
            R.color.yellow_bg,
            R.color.gray_normal,
            R.color.holo_blue_bright,
            R.color.red_light
    };
    private int[] mRowPos = {
            150,140,160,150
    };
    private Random mRandom;
    private String[] mStrContents;
    public static enum XCDirection{
        FROM_RIGHT_TO_LEFT,
        FORM_LEFT_TO_RIGHT
    }
    public enum XCAction{
        SHOW,HIDE
    }
    private XCDirection mDirection = XCDirection.FROM_RIGHT_TO_LEFT;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            final int pos = msg.what;
            ViewPropertyAnimator animator;
            if(mDirection == XCDirection.FROM_RIGHT_TO_LEFT){
                animator = mChildList.get(msg.what).animate()
                        .translationXBy(-(mScreenWidth + mChildList.get(msg.what).getWidth()));
            }else{
                animator = mChildList.get(msg.what).animate()
                        .translationXBy(mScreenWidth + mChildList.get(msg.what).getWidth());
            }

            Random random = new Random(System.currentTimeMillis());
            int index = random.nextInt(100) % mSpeeds.length;
            animator.setDuration(mSpeeds[index]);
            animator.setInterpolator(new LinearInterpolator());
            animator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    XCDanmuView.this.removeView(mChildList.get(pos));
                    int index = mRandom.nextInt(100) % mStrContents.length;
                    createDanmuView(pos, mStrContents[index], true);
                    mHandler.sendEmptyMessageDelayed(pos, mDelayDuration);
                    Log.v("czm", "size=" + mChildList.size());
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animator.start();
        }
    };

    public XCDanmuView(Context context) {
        this(context, null, 0);
    }
    public XCDanmuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public XCDanmuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }
    public boolean isWorking(){
        return mIsWorking;
    }
    private void init() {
        mScreenWidth = getScreenWidth();
        mChildList = new ArrayList<>();
        mRandom = new Random();
    }
    public void setDirection(XCDirection direction){
        mDirection = direction;
    }
    int lastRow = 0;
    public void initDanmuItemViews(String[] strContents){
        mStrContents = strContents;
        for(int i = 0; i < mMaxShowNum; i ++){
            int index =  mRandom.nextInt(100) % strContents.length;
            createDanmuView(i,strContents[index],false);
        }
    }
    public void createDanmuView(int index, final String content, boolean reset){
        final TextView textView = new TextView(mContext);
        textView.setTextColor(Color.WHITE);
        int r = mRandom.nextInt(100) % mRowNum;
        textView.setBackgroundColor(mBgResIds[r]);
        textView.setText(content +"_"+ (index+1));
        RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int row = mRandom.nextInt(100) % mRowNum;
        while(row == lastRow){
            row = mRandom.nextInt(100)% mRowNum;
        }
        int pos = mRandom.nextInt(100)% mRowNum;
        lp.topMargin = row * mRowPos[pos];
        lastRow = row;
        textView.setLayoutParams(lp);
        textView.setPadding(40, 2, 40, 2);
        this.addView(textView);
        if(reset){
            mChildList.set(index,textView);
        }else{
            mChildList.add(index,textView);
        }
        textView.setClickable(true);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(GONE);
                count++;
               if (mOnTextClickListenerl!=null){
                   mOnTextClickListenerl.onTextClickL(count);
               }

            }
        });
    }
    boolean isFirst = true;
    public void start(){
        switchAnimation(XCAction.SHOW);
        if(isFirst){
            for(int i =0;i< mChildList.size();i++){
                mHandler.sendEmptyMessageDelayed(i,i * mDelayDuration);
            }
            isFirst = false;
        }

        mIsWorking = true;
    }
    public void hide(){
        switchAnimation(XCAction.HIDE);
        mIsWorking =false;
    }
    public void stop(){
        this.setVisibility(View.GONE);
        for(int i =0;i< mChildList.size();i++){
            mChildList.get(i).clearAnimation();
            mHandler.removeMessages(i);
        }
        mIsWorking =false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = this.getChildCount();
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            RelativeLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            if(lp.leftMargin <= 0){
                if(mDirection == XCDirection.FORM_LEFT_TO_RIGHT){
                    view.layout(-view.getMeasuredWidth(), lp.topMargin,
                            0,lp.topMargin + view.getMeasuredHeight());
                }else{
                    view.layout(mScreenWidth,lp.topMargin,mScreenWidth+view.getMeasuredWidth(),
                            lp.topMargin+view.getMeasuredHeight());
                }

            }else{
                continue;
            }
        }
    }


    private void switchAnimation(final XCAction action){
        AlphaAnimation animation;
        if(action == XCAction.HIDE){
            animation = new AlphaAnimation(1.0f,0.0f);
            animation.setDuration(400);
        }else{
            animation = new AlphaAnimation(0.0f,1.0f);
            animation.setDuration(1000);
        }
        XCDanmuView.this.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(action == XCAction.HIDE){
                    XCDanmuView.this.setVisibility(View.GONE);
                }else{
                    XCDanmuView.this.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private int getScreenWidth(){
        WindowManager mWm = (WindowManager) this.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        mWm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    private OnTextClickListener mOnTextClickListenerl;

    public OnTextClickListener getOnTextClickListenerl() {
        return mOnTextClickListenerl;
    }

    public void setOnTextClickListenerl(OnTextClickListener onTextClickListenerl) {
        mOnTextClickListenerl = onTextClickListenerl;
    }

    public interface OnTextClickListener{
       void onTextClickL(int count);
    }
}
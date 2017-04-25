package com.beijing.ocean.multmediademo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;

/**
 * Created by ocean on 2017/4/19.
 */
public class MyTitleLayout extends RelativeLayout{
    TextView leftText;
    TextView titleText;
    TextView rightText;
    ImageView imgLeft,imgRight;
    private String mTitle;

    public MyTitleLayout(Context context) {
        super(context);
    }

    public MyTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        initAttr(context,attrs);

    }

    private void initAttr(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.head_title,this,true);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitleLayout);
        mTitle = ta.getString(R.styleable.MyTitleLayout_my_title);
        ta.recycle();


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
        if (titleText!=null&&!TextUtils.isEmpty(mTitle)){
            titleText.setVisibility(VISIBLE);
            titleText.setText(mTitle);
        }
    }

    private void init() {

        leftText= (TextView) findViewById(R.id.tv_left);
        titleText=(TextView) findViewById(R.id.titile_content);
        rightText=(TextView) findViewById(R.id.tv_right);
        imgLeft= (ImageView) findViewById(R.id.img_left);
        imgRight= (ImageView) findViewById(R.id.img_right);

    }

    public TextView getLeftText() {
        return leftText;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public TextView getRightText() {
        return rightText;
    }

    public ImageView getImgLeft() {
        return imgLeft;
    }

    public ImageView getImgRight() {
        return imgRight;
    }

    public void setLeftText(CharSequence str){
        if (leftText==null){
            return;
        }
        if (!(leftText.getVisibility()==VISIBLE)){
            leftText.setVisibility(VISIBLE);
        }
        this.leftText.setText(str);
    }
    public void setRightText(CharSequence str){
        if (rightText==null){
            return;
        }
        if (!(rightText.getVisibility()==VISIBLE)){
            rightText.setVisibility(VISIBLE);
        }
        this.rightText.setText(str);
    }
    public void setLeftOnclickListener(OnClickListener listener){

        if (leftText!=null&&listener!=null){
            this.leftText.setOnClickListener(listener);
        }

    }

}

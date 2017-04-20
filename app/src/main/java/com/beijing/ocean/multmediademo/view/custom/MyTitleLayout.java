package com.beijing.ocean.multmediademo.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
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
    public MyTitleLayout(Context context) {
        super(context);
        init();
    }

    public MyTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitleLayout);
        String title = ta.getString(R.styleable.MyTitleLayout_title);
        ta.recycle();
        if (!TextUtils.isEmpty(title)){
            titleText.setText(title);
        }

    }
    private void init() {
        View view =inflate(getContext(), R.layout.head_title,null);
        leftText= (TextView) view.findViewById(R.id.tv_left);
        titleText=(TextView) view.findViewById(R.id.titile_content);
        rightText=(TextView) view.findViewById(R.id.tv_right);
        imgLeft= (ImageView) view.findViewById(R.id.img_left);
        imgRight= (ImageView) view.findViewById(R.id.img_right);
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
        this.leftText.setText(str);
    }

}

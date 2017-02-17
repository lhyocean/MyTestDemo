package com.beijing.ocean.multimediademo.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 16-3-11.
 */
public class FullyVideoView extends VideoView{
    public FullyVideoView(Context context) {
        super(context);

    }

    public FullyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int wSize=MeasureSpec.getSize(widthMeasureSpec);
        int hSize=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(wSize,hSize);

       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}

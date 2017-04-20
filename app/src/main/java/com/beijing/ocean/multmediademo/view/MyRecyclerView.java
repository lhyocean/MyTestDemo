package com.beijing.ocean.multmediademo.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by ocean on 2017/4/12.
 */
public class MyRecyclerView extends XRecyclerView{
    private int distance;
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private ScrollViewListener mScrollViewListener;

    public ScrollViewListener getScrollViewListener() {
        return mScrollViewListener;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener {

        void onScrollChanged(MyRecyclerView scrollView, int distance);

    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        distance =getDistance();
        if (mScrollViewListener!=null){
            mScrollViewListener.onScrollChanged(this,distance);
        }
    }

    private int getDistance() {
        if (this.getLayoutManager() instanceof LinearLayoutManager){
            LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();
            int position = layoutManager.findFirstVisibleItemPosition();

            if (position<0){
                return 0;
            }
            View firstVisiableChildView = layoutManager.findViewByPosition(position);
            if (firstVisiableChildView!=null){
                int itemHeight = firstVisiableChildView.getHeight();
                return (position-1) * itemHeight - firstVisiableChildView.getTop();
            }else {
                return 0;
            }

        }else {
            return 0;
        }
    }


}

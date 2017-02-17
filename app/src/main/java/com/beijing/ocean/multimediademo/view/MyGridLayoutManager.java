package com.beijing.ocean.multimediademo.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/11/23.
 */
public class MyGridLayoutManager extends GridLayoutManager {
    private int mChildPerLines;
    private int[] mMeasuredDimension = new int[2];
    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        this.mChildPerLines = spanCount;
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {



        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        int height = 0;

          try{
              for (int i = 0; i < getItemCount(); ) {
                  measureScrapChild(recycler, i,
                          View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                          View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                          mMeasuredDimension);
                  height = height + mMeasuredDimension[1];
                  i = i + mChildPerLines;
              }

              // If child view is more than screen size, there is no need to make it wrap content. We can use original onMeasure() so we can scroll view.
              if (height >heightSize) {
                  switch (heightMode) {
                      case View.MeasureSpec.EXACTLY:
                          height = heightSize;
                      case View.MeasureSpec.AT_MOST:
                      case View.MeasureSpec.UNSPECIFIED:
                  }
                  setMeasuredDimension(widthSize, height);
              } else {
                  super.onMeasure(recycler, state, widthSpec, heightSpec);
              }
          }catch (Exception e){
              super.onMeasure(recycler, state, widthSpec, heightSpec);
          }

    }
    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {


        View view = recycler.getViewForPosition(position);

        // For adding Item Decor Insets to view
        super.measureChildWithMargins(view, 0, 0);
        if (view != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                    getPaddingLeft() + getPaddingRight() + getDecoratedLeft(view) + getDecoratedRight(view), p.width);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                    getPaddingTop() + getPaddingBottom() + getPaddingBottom() + getDecoratedBottom(view), p.height);
            view.measure(childWidthSpec, childHeightSpec);

            // Get decorated measurements
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }


}

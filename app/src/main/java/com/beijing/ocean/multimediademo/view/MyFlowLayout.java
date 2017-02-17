package com.beijing.ocean.multimediademo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.beijing.ocean.multimediademo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: xiaolijuan
 * @description: 流式布局-标签流容器
 * @projectName: FlowLayoutDome
 * @date: 2016-06-16
 * @time: 16:21
 */
public class MyFlowLayout extends ViewGroup{
    /**
     * 每个item纵向间距
     */
    private int mVerticalSpacing;
    /**
     * 每个item横向间距
     */

    private static final String TAG = "FlowLayout";
    private static final int LEFT = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;

    protected List<List<View>> mAllViews = new ArrayList<List<View>>();
    protected List<Integer> mLineHeight = new ArrayList<Integer>();
    protected List<Integer> mLineWidth = new ArrayList<Integer>();
    private int mGravity;
    private List<View> lineViews = new ArrayList<>();

    private int mHorizontalSpacing;
    private BaseAdapter mAdapter;
    private TagItemClickListener mListener;
    private DataChangeObserver mObserver;

    public MyFlowLayout(Context context) {
        this(context, null);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyFlowLayout, defStyle, 0);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyFlowLayout_verticalSpacing:
                    mVerticalSpacing = a.getDimensionPixelSize(R.styleable.MyFlowLayout_verticalSpacing, 5);
                    break;
                case R.styleable.MyFlowLayout_horizontalSpacing:
                    mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.MyFlowLayout_horizontalSpacing, 10);
                    break;
            }
        }
        a.recycle();
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        /**
         * 默认宽为父容器为它设置的测量宽度
         */
        int width = MeasureSpec.getSize(widthMeasureSpec);
        /**
         * 高
         */
        int height = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;
        /**
         * 在warp_content情况下，记录当前childView的左边的一个位置
         */
        int childLeft = getPaddingLeft();
        /**
         * 在warp_content情况下，记录当前childView的上边的一个位置
         */
        int childTop = getPaddingTop();
        // getChildCount得到子view的数目,遍历循环出每个子View
        for (int i = 0; i < getChildCount(); i++) {
            //拿到index上的子view
            View childView = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //当前子空间实际占据的高度
            int childHeight = childView.getMeasuredHeight();
            //当前子空间实际占据的宽度
            int childWidth = childView.getMeasuredWidth();

            //如果加入当前childView，超出最大宽度，则将目前最大宽度给width，类加height 然后开启新行
            if (childWidth + childLeft + getPaddingRight() > width) {
                childLeft = getPaddingLeft();// 重新开启新行，开始记录childLeft
                childTop += mVerticalSpacing + childHeight;// 叠加当前的高度
                lineHeight = childHeight;// 开启记录下一行的高度
            }else{
                //否则累加当前childView的宽度
                childLeft += childWidth + mHorizontalSpacing;
                lineHeight = Math.max(childHeight, lineHeight);// 取最大值
            }


        }
        height += childTop + lineHeight + getPaddingBottom();
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;
        //遍历所有childView根据其宽和高，计算子控件应该出现的位置
        for (int i = 0; i < getChildCount(); i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);
            // 如果已经需要换行
            if (childLeft + childWidth + getPaddingRight() > width) {
                childLeft = getPaddingLeft();
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }
            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + mHorizontalSpacing;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }

    private void drawLayout() {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, null, null);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.itemClick(position);
                    }
                }
            });
            addView(view);
        }
    }

    /// 将Adapter暴露
    public void setAdapter(BaseAdapter adapter) {
        if (mAdapter == null) {
            mAdapter = adapter;
            if (mObserver == null) {
                mObserver = new DataChangeObserver();
                mAdapter.registerDataSetObserver(mObserver);
            }
            drawLayout();
        }
    }

    public void setItemClickListener(TagItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface TagItemClickListener {
        void itemClick(int position);
    }

    class DataChangeObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
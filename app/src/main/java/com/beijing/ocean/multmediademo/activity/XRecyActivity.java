package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.recadapter.RecyclerAdapter;
import com.beijing.ocean.multmediademo.recadapter.RecyclerGoodAdapter;
import com.beijing.ocean.multmediademo.view.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.ItemTouchHelperAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XRecyActivity extends Activity implements XRecyclerView.LoadingListener {
    @Bind( R.id.back)
    TextView mBack;
    @Bind(R.id.xrec_view)
    XRecyclerView mXRecyclerView;


    @Bind(R.id.refresh)
    TextView mTextView;

    List<GoodBean> mList=new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private View mView;
    private String TAG="--------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecy);
        ButterKnife.bind(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XRecyActivity.this.finish();
            }
        });

        GridLayoutManager manager=new GridLayoutManager(XRecyActivity.this,1, LinearLayoutManager.VERTICAL,false);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setLayoutManager(manager);
        mXRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        mAdapter = new RecyclerAdapter(mList,XRecyActivity.this);
        mXRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper helper=new ItemTouchHelper(callBack);
        helper.attachToRecyclerView(mXRecyclerView);
        mXRecyclerView.setLoadingListener(this);

        mAdapter.setClickListener(new RecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {

                Toast.makeText(XRecyActivity.this, "点击了===="+pos+"-----", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onItemClick: ,"+pos );

            }
        });
        getDate(true);




        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();

                mAdapter.notifyDataSetChanged();
              mXRecyclerView.refresh();

            }
        });

    }


    private void getDate(final boolean isRefresh) {

                mXRecyclerView.loadMoreComplete();
                mXRecyclerView.refreshComplete();
//                List<GoodBean> list= Commen.getGoods();
//
//                if (list!=null){
//                    if (isRefresh){
//                        mList.clear();
//                    }
//                    mList.addAll(list);
//                    mAdapter.notifyDataSetChanged();
//                    if (list.size()>=10){
//                        mXRecyclerView.setLoadingMoreEnabled(true);
//                    }else {
//                        mXRecyclerView.setLoadingMoreEnabled(false);
//                    }
//                }
//                mAdapter.notifyDataSetChanged();

        List<GoodBean> list= Commen.getGoodsRandom(10);
            mList.clear();
            mList.addAll(list);
            mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDate(true);
            }
        },3000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDate(false);
            }
        },3000);
    }

    private ItemTouchHelper.Callback callBack=new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0, swipeFlags = 0;
            if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //设置侧滑方向为从左到右和从右到左都可以
                swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            if (viewHolder.getItemViewType() != target.getItemViewType()) {
                return false;
            } else {
                mAdapter.onItemDragMoving(viewHolder, target);
                return true;//返回true表示执行拖动
            }
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


            int position =viewHolder.getAdapterPosition()-1;
            Log.e("AppLog","-----"+position);
            if (position>=0&&position<=mAdapter.getData().size()){
                mAdapter.getData().remove(position);
                mAdapter.notifyItemRemoved(position);
            }

        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //滑动时改变Item的透明度
                final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            }
        }

    };
}

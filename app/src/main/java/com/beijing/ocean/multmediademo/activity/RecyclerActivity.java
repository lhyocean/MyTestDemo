package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.recadapter.RecyclerGoodAdapter;
import com.beijing.ocean.multmediademo.view.MyRecyclerView;
import com.beijing.ocean.multmediademo.view.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerActivity extends Activity implements RecyclerGoodAdapter.ItemClickListener, XRecyclerView.LoadingListener {
    @Bind( R.id.back)
    TextView mBack;
    public static final String MANAGER_TYPE="MANAGER_TYPE";

    @Bind(R.id.rec_view)
    MyRecyclerView mRecyclerView;
    @Bind(R.id.title)
    TextView title;

    private  int type=0;

    RecyclerView.LayoutManager mManager=null;
    List<GoodBean> mlist=new ArrayList<>();
    private RecyclerGoodAdapter mAdapter;


    private int backAltha=0;
    private float textAltha=0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);



        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerActivity.this.finish();
            }
        });

        type=getIntent().getIntExtra(MANAGER_TYPE,0);
        switch (type) {

            case 0:
                mManager=new LinearLayoutManager(RecyclerActivity.this,LinearLayoutManager.HORIZONTAL,false);
                break;

            case 1:
                mManager=new LinearLayoutManager(RecyclerActivity.this,LinearLayoutManager.VERTICAL,false);
                break;
            case 2:
                mManager=new GridLayoutManager(RecyclerActivity.this,2,LinearLayoutManager.HORIZONTAL,false);
                break;
            case 3:
                mManager=new GridLayoutManager(RecyclerActivity.this,4,LinearLayoutManager.VERTICAL,false);
                break;
            case 4:
                 mManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                break;
        }

        if (mManager!=null){
            mRecyclerView.setLayoutManager(mManager);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
            mAdapter = new RecyclerGoodAdapter(mlist,RecyclerActivity.this);
            mAdapter.setType(type);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setClickListener(this);
            mRecyclerView.setPullRefreshEnabled(false);
            mRecyclerView.setLoadingMoreEnabled(false);
            mRecyclerView.setLoadingListener(this);
            ItemTouchHelper helper=new ItemTouchHelper(callBack);
            helper.attachToRecyclerView(mRecyclerView);
            if (type==1){
                mRecyclerView.setScrollViewListener(new MyRecyclerView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyRecyclerView scrollView, int distance) {
                        Log.e("distance","----------------》》》》》"+distance+"");

                        int height=800;
                        if (distance>height){
                            textAltha=1.0f;
                            backAltha=255;
                            title.setAlpha(textAltha);
                            title.getBackground().setAlpha(backAltha);

                        }else {
                            float backA=255*((float)distance/(float)800);
                            backAltha=(int)backA;
                            textAltha=(float)distance/(float)800;
                            title.setAlpha(textAltha);
                            title.getBackground().setAlpha(backAltha);
                        }


                    }
                });
            }

        }

        getDate();
    }

    private void getDate() {
        mlist.clear();
        mlist.addAll(Commen.getGoods());
        mAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onResume() {
        super.onResume();
        title.setAlpha(textAltha);
        title.getBackground().setAlpha(backAltha);

    }

    @Override
    public void onItemClick(View view, int pos) {
        if (mlist!=null&&mlist.get(pos)!=null){
            GoodBean bean=mlist.get(pos);
            if (bean.getGoodImg()!=null){

                List<String> urls=new ArrayList<>();
                urls.add(bean.getGoodImg());
                ImagePagerActivity.startAct(RecyclerActivity.this,urls,0,view);

            }
        }
    }

    @Override
    public void onRefresh() {
        mRecyclerView.loadMoreComplete();
        mRecyclerView.refreshComplete();
        getDate();
    }

    @Override
    public void onLoadMore() {
        mRecyclerView.loadMoreComplete();
        mRecyclerView.refreshComplete();
        mlist.addAll(Commen.getGoods());
        mAdapter.notifyDataSetChanged();
    }


    private ItemTouchHelper.Callback callBack=new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0, swipeFlags = 0;
            if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                // 拖拽任何方向都可以
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN| ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
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

                mAdapter.getData().remove(position);
                mAdapter.notifyDataSetChanged();

        }
    };
}

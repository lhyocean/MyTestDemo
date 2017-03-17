package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
}

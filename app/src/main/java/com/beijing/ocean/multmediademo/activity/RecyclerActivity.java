package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.recadapter.RecyclerGoodAdapter;
import com.beijing.ocean.multmediademo.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerActivity extends Activity implements RecyclerGoodAdapter.ItemClickListener {
    @Bind( R.id.back)
    TextView mBack;
    public static final String MANAGER_TYPE="MANAGER_TYPE";

    @Bind(R.id.rec_view)
    RecyclerView mRecyclerView;

    private  int type=0;

    RecyclerView.LayoutManager mManager=null;
    List<GoodBean> mlist=new ArrayList<>();
    private RecyclerGoodAdapter mAdapter;

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
                mManager=new GridLayoutManager(RecyclerActivity.this,2,LinearLayoutManager.VERTICAL,false);
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


        }


        getDate();
    }

    private void getDate() {
        mlist.clear();
        mlist.addAll(Commen.getGoods());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(View view, int pos) {
        if (mlist!=null&&mlist.get(pos)!=null){
            GoodBean bean=mlist.get(pos);
            if (bean.getGoodImg()!=null){

                List<String> urls=new ArrayList<>();
                urls.add(bean.getGoodImg());
                ImagePagerActivity.startImagePagerActivity(RecyclerActivity.this,urls,0);
            }
        }
    }
}

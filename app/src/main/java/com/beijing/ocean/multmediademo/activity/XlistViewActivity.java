package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.InfoAdapter;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.UserInfo;
import com.beijing.ocean.multmediademo.view.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XlistViewActivity extends Activity implements XListView.IXListViewListener {
    @Bind( R.id.back)
    TextView mBack;
    @Bind(R.id.common_lv)
    XListView mListView;
    private List<UserInfo> mList=new ArrayList<>();
    int page=0;
    private InfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlist_view);
        ButterKnife.bind(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XlistViewActivity.this.finish();
            }
        });
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);
        mListView.setXListViewListener(this);

        mAdapter = new InfoAdapter(mList,this);
        mListView.setAdapter(mAdapter);

        page=0;
        getDate(true);

    }

    private void getDate(final boolean isRefresh) {

                mListView.stopLoadMore();
                mListView.stopRefresh();
                List<UserInfo> list= Commen.getUsers();

                if (list!=null){
                    if (isRefresh){
                       mList.clear();
                    }
                    mList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                    if (list.size()>=10){
                        mListView.setPullLoadEnable(true);
                    }else {
                        mListView.setPullLoadEnable(false);
                    }
                }
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

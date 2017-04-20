package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.MyExpandableAdapter;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.MyBean;
import com.beijing.ocean.multmediademo.view.XExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpandableListActivity extends Activity implements XExpandableListView.IXListViewListener {

    @Bind(R.id.expand_list)
    XExpandableListView mListView;
    private MyExpandableAdapter mAdapter;
    private List<MyBean> mBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expandable_list);
        ButterKnife.bind(this);


        mListView.setGroupIndicator(null);
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
        mAdapter=new MyExpandableAdapter(this,mBeanList);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);



        mListView.setAdapter(mAdapter);
        initDate();
    }

    private void initDate() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mBeanList.clear();
        for (int i = 0; i < 10; i++) {
            MyBean m=new MyBean();
            m.setName(Commen.getRandomName());
            m.setList(Commen.getGoodsRandom(3));
            mBeanList.add(m);
        }

       mAdapter.notifyDataSetChanged();
        for (int i = 0; i < mBeanList.size(); i++) {
            mListView.expandGroup(i);
        }
    }

    @Override
    public void onRefresh() {
        initDate();
    }

    @Override
    public void onLoadMore() {
        initDate();
    }
}

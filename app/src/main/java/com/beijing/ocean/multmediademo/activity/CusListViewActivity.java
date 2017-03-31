package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.CustomListViewAdapter;
import com.beijing.ocean.multmediademo.view.CustomListView;

import java.util.ArrayList;
import java.util.List;

public class CusListViewActivity extends Activity {
    // 自定义Lv
    private CustomListView mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cus_list_view);

        initContentList();

        mCustomLv = (CustomListView) findViewById(R.id.counter_view);
        mCustomLv.setOnDeleteListener(new CustomListView.OnDeleteListener() {

            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);
    }

    // 初始化内容列表
    private void initContentList() {
        for (int i = 0; i < 20; i++) {
            contentList.add("内容项" + i);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCustomLv.isDeleteShown()) {
            mCustomLv.hideDelete();
            return;
        }
        super.onBackPressed();
    }

}
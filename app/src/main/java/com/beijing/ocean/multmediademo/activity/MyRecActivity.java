package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.MyBean;
import com.beijing.ocean.multmediademo.recadapter.MyGoodAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyRecActivity extends Activity {
    private List<MyBean> mBeanList=new ArrayList<>();
    @Bind(R.id.my_rec)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rec);
        ButterKnife.bind(this);

        getMyDate();
        MyGoodAdapter adapter=new MyGoodAdapter(mBeanList,this);
        GridLayoutManager manager=new GridLayoutManager(MyRecActivity.this,1, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        

    }

    private void getMyDate() {

        mBeanList.clear();
        for (int i = 0; i < 10; i++) {
            MyBean m=new MyBean();
            m.setName(Commen.getRandomName());
            m.setList(Commen.getGoodsRandom(3));
            mBeanList.add(m);
        }



    }


}



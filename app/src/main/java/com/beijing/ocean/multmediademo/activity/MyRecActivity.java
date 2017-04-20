package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.UserAdapter;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.MyBean;
import com.beijing.ocean.multmediademo.bean.UserInfo;
import com.beijing.ocean.multmediademo.recadapter.MyGoodAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyRecActivity extends Activity implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private List<MyBean> mBeanList=new ArrayList<>();
    @Bind(R.id.my_rec)
    RecyclerView mRecyclerView;
    private List<UserInfo> mList=new ArrayList<>();
    private UserAdapter mAdapter;
    @Bind(R.id.btn_test)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rec);
        ButterKnife.bind(this);

        getMyDate();
//        MyGoodAdapter adapter=new MyGoodAdapter(mBeanList,this);
        mAdapter = new UserAdapter(this,mList);
        GridLayoutManager manager=new GridLayoutManager(MyRecActivity.this,1, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.addHeaderView(View.inflate(this,R.layout.foolter_view,null));

        LinearLayout l=new LinearLayout(this);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView t=new TextView(this);
        t.setText("foooter_______________View");
        l.addView(t);
        mAdapter.isNextLoad(true);
        mAdapter.addFooterView(l);
        mAdapter.openLoadAnimation();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyDate();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private void getMyDate() {

//        mBeanList.clear();
//        for (int i = 0; i < 10; i++) {
//            MyBean m=new MyBean();
//            m.setName(Commen.getRandomName());
//            m.setList(Commen.getGoodsRandom(3));
//            mBeanList.add(m);
//        }

        /**
         * 测试BaseAdapter
         */
        List<UserInfo> list= Commen.getUsers();
        mList.addAll(list);

    }


    @Override
    public void onLoadMoreRequested() {
           getMyDate();
           mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int i) {
        Toast.makeText(MyRecActivity.this, mList.get(i).getName(), Toast.LENGTH_SHORT).show();
    }
}



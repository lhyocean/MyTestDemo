package com.beijing.ocean.multmediademo.activity;



import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.vpPageAdapter.OrderPagerAdapter;
import com.beijing.ocean.multmediademo.base.BaseFragment;
import com.beijing.ocean.multmediademo.fragment.AFragment;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends FragmentActivity {

    List<String> tabNames=new ArrayList<>();
    List<BaseFragment> fragments=new ArrayList<>();
    private TabLayout mTablayout;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

       if (tabNames!=null&&tabNames.size()>0){
           tabNames.clear();
       }
        for (int i = 0; i < 10; i++) {
            tabNames.add("标题---"+i+""+i);
        }
        View viewById = findViewById(R.id.tv_check);
        mTablayout = (TabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.view_pager);

        if (tabNames!=null&&tabNames.size()>0&&fragments!=null&&fragments.size()==0){


            for (int i = 0; i < tabNames.size(); i++) {

                AFragment f=new AFragment();
                Bundle b=new Bundle();
                b.putString("test",tabNames.get(i));
                f.setArguments(b);
                fragments.add(f);
            }
        }

        OrderPagerAdapter adapter=new OrderPagerAdapter(getSupportFragmentManager(),fragments,tabNames);
        mPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mPager);

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPager.setCurrentItem(5);
            }
        });






    }
}

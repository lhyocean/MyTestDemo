package com.beijing.ocean.multimediademo.adapter.vpPageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import com.beijing.ocean.multimediademo.base.BaseFragment;

import java.util.List;

/**
 * Created by lyq on 2016/5/18.
 * 我的订单 对应的adapter
 */
public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<String> titles;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    private FragmentManager fm;
    private String tag = "OrderPagerAdapter";

    public OrderPagerAdapter(FragmentManager fm, List<BaseFragment> fragments,List<String> title) {
        super(fm);
        this.fragments =fragments;
        this.fm = fm;
        this.titles=title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles==null?"":titles.get(position);
    }
}

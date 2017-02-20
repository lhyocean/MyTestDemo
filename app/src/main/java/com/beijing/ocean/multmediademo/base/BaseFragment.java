package com.beijing.ocean.multmediademo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2016/12/30.
 */
public abstract class BaseFragment extends Fragment{
    /**
     * 根元素
     */
    protected View mRootView;
    /**
     * 布局渲染工具
     */
    protected Context mContext;
    private String TAG = getClass().getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mContext=getActivity();
         mRootView=initRootView();

       return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViews();
    }

    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    /**
     * 初始化View,加载布局
     *
     * @return 布局
     */
    public abstract View initRootView();

    public View getRootView() {
        return mRootView;
    }
    /**
     * 初始化数据
     */
    public abstract void setViews();

    /**
     * 加载网络数据
     */
    public abstract void initData();
}

package com.beijing.ocean.multimediademo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multimediademo.base.BaseFragment;

/**
 * Created by lenovo on 2016/12/30.
 */
public class AFragment extends BaseFragment{

    @Override
    public View initRootView() {
        mRootView= LayoutInflater.from(mContext).inflate(R.layout.fragment_a,null);
        return mRootView;
    }

    @Override
    public void setViews() {
        TextView textView= (TextView) mRootView.findViewById(R.id.tv_test);
        if (getArguments()!=null){
            String str=getArguments().getString("test");
            if (str!=null){
                textView.setText(str);
            }
        }

    }

    @Override
    public void initData() {

    }
}

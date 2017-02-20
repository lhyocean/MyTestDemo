package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by lyq on 2016/5/17.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> mData;
    private Context mContext;

    public MyBaseAdapter(List<T> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void append(List<T> list) {
        if (this.mData != null) {
            this.mData.addAll(list);
        }
        notifyDataSetChanged();
    }
}

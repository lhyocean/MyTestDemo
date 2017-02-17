package com.beijing.ocean.multimediademo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multimediademo.bean.Commen;

import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */
public class FlowAdapter extends MyBaseAdapter<String> {

    private List<String> mDates;
    private Context mContext;

    public FlowAdapter(List<String> data, Context context) {
        super(data, context);
        mDates = data;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
       if (  convertView==null){
           convertView =View.inflate(mContext, R.layout.item_tab,null);
           holder=new ViewHolder();
           holder.mTextView= (TextView) convertView.findViewById(R.id.btn_tab);
           convertView.setTag(holder);
       }else {
           holder= (ViewHolder) convertView.getTag();
       }

        if (mDates!=null){
            final String text=mDates.get(position);
            if (text!=null){
                holder.mTextView.setText(text);
            }
            holder.mTextView.setTextColor(mContext.getResources().getColor(Commen.getRandomCor()));

        }

        return convertView;
    }

    class  ViewHolder{
        TextView mTextView;
    }
}

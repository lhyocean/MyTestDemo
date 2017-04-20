package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.utils.ImageUtil;

import java.util.List;

/**
 * Created by ocean on 2017/4/14.
 */
public class MyTestWheelAdapter extends MyBaseAdapter<GoodBean> {
    private List<GoodBean>  mData;
    private Context mContext;
    public MyTestWheelAdapter(List<GoodBean> data, Context context) {
        super(data, context);
        mData=data;
        mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(mContext, R.layout.item_goods,null);
            holder=new ViewHolder();
            holder.img= (ImageView) convertView.findViewById(R.id.good_img);
            holder.mTextView= (TextView) convertView.findViewById(R.id.goods_des);

            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if (mData!=null&&mData.size()!=0){
            GoodBean goodBean = mData.get(position);
            holder.mTextView.setText(goodBean.getGoodDes());
            ImageUtil.loadHeadImgNet(goodBean.getGoodImg(),holder.img);
        }
        return convertView;
    }

    class ViewHolder{
        TextView mTextView;
        ImageView img;
    }
}

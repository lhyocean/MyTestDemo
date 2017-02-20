package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.UserInfo;
import com.beijing.ocean.multmediademo.utils.ImageUtil;
import com.beijing.ocean.multmediademo.view.CircleImageView;

import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */
public class InfoAdapter extends MyBaseAdapter<UserInfo> {
    private List<UserInfo>  mData;
    private Context mContext;

    public InfoAdapter(List<UserInfo> date, Context context) {
        super(date, context);
        mData = date;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;

        if (convertView==null){
            convertView=View.inflate(mContext, R.layout.item_my_info,null);
            holder=new ViewHolder();
            holder.headImg= (CircleImageView) convertView.findViewById(R.id.head_pic);
            holder.mTvName= (TextView) convertView.findViewById(R.id.item_my_info_name);
            holder.mTvDes= (TextView) convertView.findViewById(R.id.item_my_info_des);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }

       UserInfo mbean=mData.get(position);
        if (mbean!=null){

            holder.mTvName.setText(mbean.getName()==null?"":mbean.getName());
            holder.mTvDes.setText(mbean.getDes()==null?"":mbean.getDes());
            if (mbean.getHeadurl()!=null){
                ImageUtil.loadHeadImgNet(mbean.getHeadurl(),holder.headImg);
            }
        }

        return convertView;
    }

    class  ViewHolder{

        TextView mTvName;
        TextView mTvDes;
        CircleImageView headImg;
    }
}

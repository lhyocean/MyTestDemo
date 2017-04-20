package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.bean.MyBean;
import com.beijing.ocean.multmediademo.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/4/7.
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<MyBean> mBeanList =new ArrayList<>();


    public MyExpandableAdapter(Context context, List<MyBean> beanList) {
        mContext = context;
        mBeanList = beanList;
    }

    @Override
    public int getGroupCount() {
        return mBeanList==null?0:mBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mBeanList==null?0:mBeanList.get(groupPosition).getList()==null?0:mBeanList.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBeanList.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder=null;
        if (convertView==null){
            holder=new GroupViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.adapter_expandable_group_item,parent,false);
            holder.tvGroupName= (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(holder);
        }else {
            holder= (GroupViewHolder) convertView.getTag();
        }
        MyBean bean=mBeanList.get(groupPosition);
        if (bean!=null&&!TextUtils.isEmpty(bean.getName())){
            holder.tvGroupName.setText(bean.getName());
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder=null;
        if (convertView==null){
            holder=new ChildViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.adapter_expandable_child_item,parent,false);
            holder.childName= (TextView) convertView.findViewById(R.id.tv_text);
            holder.childImg= (ImageView) convertView.findViewById(R.id.img_left);
            convertView.setTag(holder);
        }else {
            holder= (ChildViewHolder) convertView.getTag();
        }

        if (mBeanList!=null&&mBeanList.get(groupPosition)!=null&&mBeanList.get(groupPosition).getList()!=null){
            GoodBean mGoodBean  = mBeanList.get(groupPosition).getList().get(childPosition);
            if (mGoodBean!=null){
                holder.childName.setText(mGoodBean.getGoodDes()==null?"":mGoodBean.getGoodDes());
                if (!TextUtils.isEmpty(mGoodBean.getGoodImg())){
                    ImageUtil.loadHeadImgNet(mGoodBean.getGoodImg(),holder.childImg);
                }
            }
        }
        return convertView;
    }

    class GroupViewHolder{
        TextView tvGroupName;
    }
   class  ChildViewHolder{
       TextView childName;
       ImageView childImg;
   }

}

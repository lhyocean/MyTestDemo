package com.beijing.ocean.multmediademo.recadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.utils.ImageUtil;
import com.beijing.ocean.multmediademo.view.RoundedImageView;

import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */

  public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{
    private List<GoodBean> mData;
    private Context mContext;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RecyclerAdapter(List<GoodBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_goods,null);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
          if (mData!=null&&mData.get(position)!=null){

              GoodBean bean=mData.get(position);
              holder.mLayout.setOnClickListener(new MyOnClickListener(holder.mLayout,position));
              holder.mTextView.setText(bean.getGoodDes()==null?"":bean.getGoodDes());
              if (bean.getGoodImg()!=null){
                  ImageUtil.loadHeadImgNet(bean.getGoodImg(),holder.img);
              }

              if (type==4){

                  holder.mTextView.setHeight(100+(position%3)*40);
              }
          }
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    class  Holder extends RecyclerView.ViewHolder{

        private LinearLayout mLayout;
        private RoundedImageView img;
        private TextView mTextView;
        public Holder(View itemView) {
            super(itemView);
            mLayout= (LinearLayout) itemView.findViewById(R.id.ll_container);
            img= (RoundedImageView) itemView.findViewById(R.id.good_img);
            mTextView= (TextView) itemView.findViewById(R.id.goods_des);
        }
    }

    public  interface  ItemClickListener{
        void onItemClick(View view, int pos);
    }
    private ItemClickListener mClickListener;

    public void setClickListener(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }
    class MyOnClickListener implements View.OnClickListener{
        private LinearLayout mLayout;
        private int position;

        public MyOnClickListener(LinearLayout layout, int position) {
            this.mLayout = layout;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener!=null){
                mClickListener.onItemClick(mLayout,position);
            }
        }
    }
}
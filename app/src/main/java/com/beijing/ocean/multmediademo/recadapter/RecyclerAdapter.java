package com.beijing.ocean.multmediademo.recadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.utils.ImageUtil;
import com.beijing.ocean.multmediademo.view.RoundedImageView;

import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */

  public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<GoodBean> mData;
    private Context mContext;
    private int type;
    public static final int HEAD_VIEW=0x00;
    public static final int BODY_VIEW=0x01;

    public List<GoodBean> getData() {
        return mData;
    }

    public void setData(List<GoodBean> data) {
        mData = data;
    }

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if (viewType==HEAD_VIEW){
            View view= LayoutInflater.from(mContext).inflate(R.layout.foolter_view,null);
             holder=new HeadHolder(view);

        }else {
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_goods,null);
             holder=new Holder(view);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        if (mData==null||(mData!=null&&mData.size()==0)){
            return HEAD_VIEW;
        }else {
            return BODY_VIEW;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

         if (holder instanceof HeadHolder){

             HeadHolder headHolder= (HeadHolder) holder;
             headHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(mContext, "点击了头部", Toast.LENGTH_SHORT).show();
                 }
             });

         }else {
             Holder holder1=(Holder)holder;
             GoodBean bean=mData.get(position);
             if (bean!=null){
                 holder1.mLayout.setOnClickListener(new MyOnClickListener(holder1.mLayout,position));
                 holder1.mTextView.setText(bean.getGoodDes()==null?"":bean.getGoodDes());
                 if (bean.getGoodImg()!=null){
                     ImageUtil.loadHeadImgNet(bean.getGoodImg(),holder1.img);
                 }
             }
         }
    }

    @Override
    public int getItemCount() {
        return (mData==null||mData.size()==0)?1:mData.size();
    }

    public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = getViewHolderPosition(viewHolder);
        int to = getViewHolderPosition(target);

        if (from < to) {
            for (int i = from; i < to; i++) {
                Collections.swap(getData(), i, i + 1);
            }
        } else {
            for (int i = from; i > to; i--) {
                Collections.swap(getData(), i, i - 1);
            }
        }
        notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

    }
    public int getViewHolderPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - (0 + 1);
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

    class HeadHolder extends RecyclerView.ViewHolder{
         LinearLayout mLayout;
        public HeadHolder(View view)
        {
            super(view);
            mLayout= (LinearLayout) view.findViewById(R.id.footer_view);
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
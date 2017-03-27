package com.beijing.ocean.multmediademo.recadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

  public class RecyclerHeadAdapter extends RecyclerView.Adapter{
    private List<GoodBean> mData;
    private Context mContext;
    private int type;
    public static final int TYPE_HEADER=0x01;
    public static final int TYPE_BODY=0x02;



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RecyclerHeadAdapter(List<GoodBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){return TYPE_HEADER;}
        else
            return TYPE_BODY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if (viewType==TYPE_BODY){
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_goods,null);
            holder=new Holder(view);
        }else if (viewType==TYPE_HEADER){
            View view= LayoutInflater.from(mContext).inflate(R.layout.foolter_view,null);
            holder=new HeadHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeadHolder){


        }else if (holder instanceof Holder){
            Holder bodyHolder= (Holder) holder;


            int location = position - 1;
            if (mData!=null&&mData.get(location)!=null){

                GoodBean bean=mData.get(location);
                bodyHolder.mLayout.setOnClickListener(new MyOnClickListener(bodyHolder.mLayout,location));
                bodyHolder.mTextView.setText(bean.getGoodDes()==null?"":bean.getGoodDes());
                if (bean.getGoodImg()!=null){
                    ImageUtil.loadHeadImgNet(bean.getGoodImg(),bodyHolder.img);
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return mData==null?1:mData.size()+1;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager= (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int rety=ishead(position)?gridLayoutManager.getSpanCount():1;
                    int  ret=ishead(position)?1:gridLayoutManager.getSpanCount();

                    Log.e("tag", ret+"getSpanSize: ---------------->>>"+rety );
                    return rety;

                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    private boolean ishead(int position) {
        if (position==0){
              return true;
        }else {
            return false;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {


    }

    class HeadHolder extends RecyclerView.ViewHolder{

        public HeadHolder(View view){
            super(view);
        }
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
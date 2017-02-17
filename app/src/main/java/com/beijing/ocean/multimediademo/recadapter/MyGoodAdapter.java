package com.beijing.ocean.multimediademo.recadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multimediademo.bean.GoodBean;
import com.beijing.ocean.multimediademo.bean.MyBean;
import com.beijing.ocean.multimediademo.utils.ImageUtil;
import com.beijing.ocean.multimediademo.view.MyGridLayoutManager;
import com.beijing.ocean.multimediademo.view.RoundedImageView;
import com.beijing.ocean.multimediademo.view.SpacesItemDecoration;

import java.util.List;

/**
 * Created by admin on 2016/11/12.
 */

  public class MyGoodAdapter extends RecyclerView.Adapter<MyGoodAdapter.Holder>{
    private List<MyBean> mData;
    private Context mContext;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MyGoodAdapter(List<MyBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_rec_rec,null);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
          if (mData!=null&&mData.get(position)!=null){

              MyBean bean=mData.get(position);

              holder.mTextView.setText(bean.getName()==null?"":bean.getName());
             if (bean.getList()!=null){

                 List<GoodBean> list=bean.getList();
                 RecyclerGoodAdapter adapter=new RecyclerGoodAdapter(list,mContext);
                 GridLayoutManager manager=new GridLayoutManager(mContext,3);
                 holder.mRecyclerView.addItemDecoration(new SpacesItemDecoration(1));
//                 manager.setAutoMeasureEnabled(false);
                 holder.mRecyclerView.setLayoutManager(manager);
                 holder.mRecyclerView.setAdapter(adapter);

                 adapter.setClickListener(new RecyclerGoodAdapter.ItemClickListener() {
                     @Override
                     public void onItemClick(View view, int pos) {

                     }
                 });
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


       private RecyclerView mRecyclerView;
        private TextView mTextView;
        public Holder(View itemView) {
            super(itemView);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.item_rec);
            mTextView= (TextView) itemView.findViewById(R.id.item_title);
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
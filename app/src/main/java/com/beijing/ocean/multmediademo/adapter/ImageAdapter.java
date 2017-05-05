package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.activity.ImagePagerActivity;
import com.beijing.ocean.multmediademo.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by lenovo on 2016/11/10.
 */
public class ImageAdapter extends PagerAdapter {

    private List<String> datas=new ArrayList<>();
    private Context mContext;

    public ImageAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            View view= LayoutInflater.from(mContext).inflate(R.layout.image_item,container,false);
          if (view!=null){
              PhotoView photoView= (PhotoView) view.findViewById(R.id.image);
              if (datas!=null&&datas.get(position)!=null){
                  ImageUtil.loadHeadImgNet(datas.get(position),photoView);
              }
              ViewCompat.setTransitionName(photoView, ImagePagerActivity.TRANSIT_PIC);
              container.addView(view,0);

          }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

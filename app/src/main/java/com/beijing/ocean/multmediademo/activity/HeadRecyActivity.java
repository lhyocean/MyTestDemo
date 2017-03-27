package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.bean.GoodBean;
import com.beijing.ocean.multmediademo.recadapter.RecyclerAdapter;

import com.beijing.ocean.multmediademo.view.SpacesItemDecoration;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HeadRecyActivity extends Activity implements XRecyclerView.LoadingListener {

    @Bind( R.id.back)
    TextView mBack;
    @Bind(R.id.xrec_view)
    XRecyclerView mXRecyclerView;


    @Bind(R.id.refresh)
    TextView mTextView;

    List<GoodBean> mList=new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private View mView;
    private String TAG="--------------------";
    private SliderLayout mAdSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecy);
        ButterKnife.bind(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeadRecyActivity.this.finish();
            }
        });

        GridLayoutManager manager=new GridLayoutManager(HeadRecyActivity.this,2, LinearLayoutManager.VERTICAL,false);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setLayoutManager(manager);
        View view= View.inflate(HeadRecyActivity.this,R.layout.foolter_view,null);
        mAdSlider = (SliderLayout) view.findViewById(R.id.slideLayout);
        mXRecyclerView.addHeaderView(view);

        mXRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        mAdapter = new RecyclerAdapter(mList,HeadRecyActivity.this);
        mXRecyclerView.setAdapter(mAdapter);

        mXRecyclerView.setLoadingListener(this);

        mAdapter.setClickListener(new RecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {

                Toast.makeText(HeadRecyActivity.this, "点击了===="+pos+"-----", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onItemClick: ,"+pos );

            }
        });
        getDate(true);


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();

                mAdapter.notifyDataSetChanged();
                mXRecyclerView.refresh();


            }
        });


        setSlideDate(this,mAdSlider,mList);


    }

    private void setSlideDate(Context context, SliderLayout sliderLayout, List<GoodBean> list) {

        for (GoodBean adData :list) {
            TextSliderView textSliderView = new TextSliderView(context);
            // initialize a SliderLayout
            textSliderView.description(adData.getGoodDes())
                    .image(adData.getGoodImg())
                    .empty(R.drawable.ssdk_oks_classic_douban)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putParcelable("", adData);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);

    }

    private void getDate(final boolean isRefresh) {

        mXRecyclerView.loadMoreComplete();
        mXRecyclerView.refreshComplete();
//                List<GoodBean> list= Commen.getGoods();
//
//                if (list!=null){
//                    if (isRefresh){
//                        mList.clear();
//                    }
//                    mList.addAll(list);
//                    mAdapter.notifyDataSetChanged();
//                    if (list.size()>=10){
//                        mXRecyclerView.setLoadingMoreEnabled(true);
//                    }else {
//                        mXRecyclerView.setLoadingMoreEnabled(false);
//                    }
//                }
//                mAdapter.notifyDataSetChanged();

        List<GoodBean> list= Commen.getGoodsRandom(10);
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();



    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDate(true);
            }
        },3000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDate(false);
            }
        },3000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdSlider!=null){
            mAdSlider.startAutoCycle();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mAdSlider!=null){
            mAdSlider.stopAutoCycle();
        }
    }
}


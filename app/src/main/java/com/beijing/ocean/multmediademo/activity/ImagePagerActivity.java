package com.beijing.ocean.multmediademo.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;


import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.ImageAdapter;
import com.beijing.ocean.multmediademo.utils.CheckUtils;
import com.beijing.ocean.multmediademo.view.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;
import  static com.beijing.ocean.multmediademo.utils.CheckUtils.checkNotNull;


public class ImagePagerActivity extends Activity {

    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    public static final String TRANSIT_PIC = "picture";


    private List<View> guideViewList = new ArrayList<View>();
    private LinearLayout guideGroup;
    private int startPos;
    private ArrayList<String> imgUrls;

    public static void startImagePagerActivity(Context context, List<String> imgUrls, int position){
        CheckUtils.checkNotNull(context);
        checkNotNull(imgUrls);
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        context.startActivity(intent);

    }


    public static void startAct(@NonNull
                                Activity context, List<String> imgUrls, int position, View view){
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);

        ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(context,view,TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(context, intent, compat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            context.startActivity(intent);
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_pager);


        LinearLayout llBack= (LinearLayout) findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePagerActivity.this.finish();
            }
        });


        PhotoViewPager viewPager= (PhotoViewPager) findViewById(R.id.pager);
        guideGroup= (LinearLayout) findViewById(R.id.guideGroup);
        startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);

        ImageAdapter mAdapter = new ImageAdapter(imgUrls,this);

        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<guideViewList.size(); i++){
                    guideViewList.get(i).setSelected(i==position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(startPos);
        addGuideView(guideGroup, startPos, imgUrls);

    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if(imgUrls!=null && imgUrls.size()>0){
            guideViewList.clear();
            for (int i=0; i<imgUrls.size(); i++){
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i==startPos ? true : false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dimen_12),
                        getResources().getDimensionPixelSize(R.dimen.dimen_12));
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }



}

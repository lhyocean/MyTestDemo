package com.beijing.ocean.multmediademo;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.activity.MyRecActivity;
import com.beijing.ocean.multmediademo.activity.FlowActivity;
import com.beijing.ocean.multmediademo.activity.ImagePagerActivity;
import com.beijing.ocean.multmediademo.activity.PlayVideoActivity;
import com.beijing.ocean.multmediademo.activity.RecyclerActivity;
import com.beijing.ocean.multmediademo.activity.TabLayoutActivity;
import com.beijing.ocean.multmediademo.activity.XRecyActivity;
import com.beijing.ocean.multmediademo.activity.XlistViewActivity;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.view.DateTimePickDialogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity  {

    @Bind(R.id.btn_play)
    TextView btn;
    @Bind(R.id.btn_date_picker)
    TextView tvEndTime;
    @Bind(R.id.btn_flowLayout)
    TextView mTvFlow;
    @Bind(R.id.btn_photo_pager)
    TextView mTvPhoto;
    @Bind(R.id.btn_XListView)
    TextView mTvXlist;
    @Bind(R.id.btn_XRecyclerView)
    TextView mTvXR;
    @Bind(R.id.btn_rec_line_hor)
    TextView mTvRecLH;
    @Bind(R.id.btn_rec_line_ver)
    TextView mTvRecLV;
    @Bind(R.id.btn_rec_grid_hor)
    TextView mTvRecGH;
    @Bind(R.id.btn_rec_grid_ver)
    TextView mTvRecGV;
    @Bind(R.id.btn_rec_fl)
    TextView mTvRecFl;
    List<String> urls=new ArrayList<>();
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.btn_image)
    TextView tvImg;
    @Bind(R.id.btn_changeUrl)
    TextView tvUrl;
    @Bind(R.id.btn_changeUrl0)
    TextView tvUrl0;
    @Bind(R.id.tv_okHttp)
    TextView tvOk;
    @Bind(R.id.tv_asyncHttp)
    TextView tvAsync;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);





    }

    @OnClick({R.id.btn_play,R.id.btn_date_picker,R.id.btn_rec_line_hor,R.id.btn_rec_line_ver
           ,R.id.btn_rec_grid_hor,R.id.btn_rec_grid_ver,R.id.btn_rec_fl,
            R.id.btn_XRecyclerView,R.id.btn_flowLayout,R.id.btn_XListView,R.id.btn_photo_pager,R.id.btn_image,R.id.btn_changeUrl,R.id.btn_changeUrl0})
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.btn_play:
                intent=new Intent(MainActivity.this, PlayVideoActivity.class);
                break;
            case R.id.btn_date_picker:
                long currentTime= System.currentTimeMillis();
               String inittime=new SimpleDateFormat("yyyy年MM月dd日").format(new Date(currentTime));
                DateTimePickDialogUtil dateTimePicKDialogend = new DateTimePickDialogUtil(
                        MainActivity.this, inittime);
                dateTimePicKDialogend.dateTimePicKDialog(tvEndTime);

                break;
            case R.id.btn_rec_line_hor:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,0);
               break;
            case R.id.btn_rec_line_ver:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,1);
                break;
            case R.id.btn_rec_grid_hor:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,2);
                break;
            case R.id.btn_rec_grid_ver:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,3);
                break;
            case R.id.btn_rec_fl:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,4);
                break;

            case R.id.btn_XRecyclerView:
                intent=new Intent(MainActivity.this, XRecyActivity.class);
                break;

            case R.id.btn_flowLayout:
               intent=new Intent(MainActivity.this, FlowActivity.class);

                break;
            case R.id.btn_XListView:
                intent=new Intent(MainActivity.this, XlistViewActivity.class);

                break;
            case R.id.btn_photo_pager:
                urls= Commen.creatURLs();
                int pos=new Random().nextInt(9);
                ImagePagerActivity.startImagePagerActivity(MainActivity.this,urls,pos);
                break;
            case R.id.btn_image:

                intent=new Intent(MainActivity.this, MyRecActivity.class);
                break;

            case R.id.btn_changeUrl:

                intent=new Intent(MainActivity.this, TabLayoutActivity.class);
                break;
            case R.id.btn_changeUrl0:



                break;

            case R.id.tv_asyncHttp:


                break;
            case R.id.tv_okHttp:

                break;


        }

        if (intent!=null){
            this.startActivity(intent);
        }
    }
}

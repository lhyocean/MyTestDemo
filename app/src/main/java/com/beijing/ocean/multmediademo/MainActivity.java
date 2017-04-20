package com.beijing.ocean.multmediademo;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.activity.CanvasActivity;
import com.beijing.ocean.multmediademo.activity.CusListViewActivity;
import com.beijing.ocean.multmediademo.activity.DaoMasterActivity;
import com.beijing.ocean.multmediademo.activity.DragFlowlayoutActivity;
import com.beijing.ocean.multmediademo.activity.ExpandableListActivity;
import com.beijing.ocean.multmediademo.activity.HeadRecyActivity;
import com.beijing.ocean.multmediademo.activity.LoopWheelActivity;
import com.beijing.ocean.multmediademo.activity.MyRecActivity;
import com.beijing.ocean.multmediademo.activity.FlowActivity;
import com.beijing.ocean.multmediademo.activity.ImagePagerActivity;
import com.beijing.ocean.multmediademo.activity.PlayVideoActivity;
import com.beijing.ocean.multmediademo.activity.RecyclerActivity;
import com.beijing.ocean.multmediademo.activity.SQLiteActivity;
import com.beijing.ocean.multmediademo.activity.TabLayoutActivity;
import com.beijing.ocean.multmediademo.activity.WebVideoActivity;
import com.beijing.ocean.multmediademo.activity.WebiframeActivity;
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
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    @Bind(R.id.btn_XR)
   TextView tvXR;
    @Bind(R.id.my_btn_canvas)
    TextView tvCanvas;
    @Bind(R.id.my_btn_customListView)
    TextView tvCustomListView;
    @Bind(R.id.btn_expandable)
    TextView tvExpandleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ShareSDK.initSDK(this);

    }

    @OnClick({R.id.btn_play,R.id.btn_date_picker,R.id.btn_rec_line_hor,R.id.btn_rec_line_ver
           ,R.id.btn_rec_grid_hor,R.id.btn_rec_grid_ver,R.id.btn_rec_fl,R.id.btn_XR,
            R.id.btn_XRecyclerView,R.id.btn_flowLayout,R.id.btn_XListView,R.id.btn_photo_pager,
            R.id.btn_image,R.id.btn_changeUrl,R.id.btn_changeUrl0,R.id.tv_asyncHttp,R.id.tv_okHttp,
            R.id.my_btn_canvas,R.id.my_btn_customListView,R.id.btn_expandable})
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
                intent =new Intent(MainActivity.this, LoopWheelActivity.class);

               break;
            case R.id.btn_rec_line_ver:
                intent =new Intent(MainActivity.this, RecyclerActivity.class);
                intent.putExtra(RecyclerActivity.MANAGER_TYPE,1);
                break;
            case R.id.btn_rec_grid_hor:
                intent =new Intent(MainActivity.this, DaoMasterActivity.class);
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

               showShare();


                break;

            case R.id.tv_asyncHttp:
                intent=new Intent(MainActivity.this, SQLiteActivity.class);

                break;
            case R.id.tv_okHttp:

                intent=new Intent(MainActivity.this, WebiframeActivity.class);


                break;
            case R.id.btn_XR:

                intent=new Intent(MainActivity.this, HeadRecyActivity.class);

                break;

            case R.id.my_btn_canvas:

                intent=new Intent(MainActivity.this, CanvasActivity.class);
                break;

            case R.id.my_btn_customListView:

                intent=new Intent(MainActivity.this, CusListViewActivity.class);

                break;
            case R.id.btn_expandable:
                intent=new Intent(MainActivity.this, ExpandableListActivity.class);
                break;

        }

        if (intent!=null){
            this.startActivity(intent);
        }
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}

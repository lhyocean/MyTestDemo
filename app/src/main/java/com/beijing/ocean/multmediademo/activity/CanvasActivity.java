package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.view.DrawView;
import com.beijing.ocean.multmediademo.view.XCDanmuView;

import java.util.List;

public class CanvasActivity extends Activity implements XCDanmuView.OnTextClickListener {
    private XCDanmuView mDanmuView;
    private List<View> mViewList;
    private String[] mStrItems = {
            "搜狗","百度",
            "腾讯","360",
            "阿里巴巴","搜狐",
            "网易","新浪",
            "搜狗-上网从搜狗开始","百度一下,你就知道",
            "必应搜索-有求必应","好搜-用好搜，特顺手",
            "Android-谷歌","IOS-苹果",
            "Windows-微软","Linux"
    };
    private TextView mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.ll_container);

//        DrawView child = new DrawView(this);
//        child.setMinimumHeight(1000);
//        child.setMinimumWidth(500);
//        child.invalidate();
//        linearLayout.addView(child);
        mCount = (TextView) findViewById(R.id.btn_count);

        initDanmuView();
        initListener();
    }

    private void initListener() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDanmuView.isWorking()) {
                    mDanmuView.hide();
                    ((Button) view).setText("开启弹幕");
                } else {
                    mDanmuView.start();
                    ((Button) view).setText("关闭弹幕");
                }
            }
        });
    }

    private void initDanmuView() {
        mDanmuView = (XCDanmuView)findViewById(R.id.danmu);
//        mDanmuView.setDirection(XCDanmuView.XCDirection.FORM_LEFT_TO_RIGHT);
        mDanmuView.initDanmuItemViews(mStrItems);
        mDanmuView.setOnTextClickListenerl(this);
    }

    @Override
    public void onTextClickL(int count) {
        if (mCount!=null){

            mCount.setText("共点击了--------"+count+"   个");
        }


    }
}
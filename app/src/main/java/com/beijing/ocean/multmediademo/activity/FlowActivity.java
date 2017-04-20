package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.adapter.FlowAdapter;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FlowActivity extends Activity implements FlowLayout.TagItemClickListener, View.OnTouchListener {


    @Bind(R.id.my_flow)
    FlowLayout mFlowLayout;
    @Bind( R.id.back)
    TextView mBack;

    ImageView iv;
    int screenWidth,screenHeight;
    int lastX,lastY;
    List<String> texts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowActivity.this.finish();
            }
        });
        texts = Commen.getTexts(0);
        Log.e("----count",texts.size()+"--");
        FlowAdapter mAdapter=new FlowAdapter(texts,FlowActivity.this);
        mFlowLayout.setAdapter(mAdapter);
        mFlowLayout.setItemClickListener(this);
        Log.e("----count",mFlowLayout.getChildCount()+"-fl-");


         iv= (ImageView) findViewById(R.id.img_test);
        PhotoViewAttacher attacher=new PhotoViewAttacher(iv);
        iv.setOnTouchListener(this);
        Display dis=this.getWindowManager().getDefaultDisplay();
        screenWidth=dis.getWidth();
        screenHeight=dis.getHeight();

    }

    @Override
    public void itemClick(int position) {
        Toast.makeText(FlowActivity.this, texts.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX=(int)event.getRawX();
                lastY=(int)event.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:
                int dx=(int)event.getRawX()-lastX;
                int dy=(int)event.getRawY()-lastY;

                int top=v.getTop()+dy;

                int left=v.getLeft()+dx;


                if(top<=0)
                {
                    top=0;
                }
                if(top>=screenHeight-iv.getHeight())
                {
                    top=screenHeight-iv.getHeight();
                }
                if(left>=screenWidth-iv.getWidth())
                {
                    left=screenWidth-iv.getWidth();
                }

                if(left<=0)
                {
                    left=0;
                }


                v.layout(left, top, left+iv.getWidth(), top+iv.getHeight());
                lastX=(int)event.getRawX();
                lastY=(int)event.getRawY();

                break;
            case MotionEvent.ACTION_UP:

                break;

        }

        return false;
    }
}

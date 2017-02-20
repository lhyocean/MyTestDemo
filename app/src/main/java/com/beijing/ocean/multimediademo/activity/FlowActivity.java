package com.beijing.ocean.multimediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multimediademo.adapter.FlowAdapter;
import com.beijing.ocean.multimediademo.bean.Commen;
import com.beijing.ocean.multimediademo.view.FlowLayout;
import com.beijing.ocean.multimediademo.view.MyFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FlowActivity extends Activity implements FlowLayout.TagItemClickListener {


    @Bind(R.id.my_flow)
    FlowLayout mFlowLayout;
    @Bind( R.id.back)
    TextView mBack;


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
        
    }

    @Override
    public void itemClick(int position) {
        Toast.makeText(FlowActivity.this, texts.get(position), Toast.LENGTH_SHORT).show();
    }
}

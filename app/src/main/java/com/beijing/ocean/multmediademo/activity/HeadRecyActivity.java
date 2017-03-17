package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.beijing.ocean.multimediademo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HeadRecyActivity extends Activity {

    @Bind(R.id.xrec_view)
    XRecyclerView mXRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecy);
        ButterKnife.bind(this);











    }
}

package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.dragflowlayout.DragAdapter;
import com.beijing.ocean.multmediademo.dragflowlayout.DragFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class DragFlowlayoutActivity extends Activity {
    List<String> texts=new ArrayList<>();
    private DragFlowLayout mDragFlowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_flowlayout);
        mDragFlowLayout= (DragFlowLayout) findViewById(R.id.drag_flowLayout);
        texts = Commen.getTexts(0);


//        mDragFlowLayout.setDragAdapter(new DragAdapter<String>() {
//            @Override
//            public int getItemLayoutId() {
//                return R.layout.item_tab;
//            }
//
//            @Override
//            public void onBindData(View itemView, int dragState, String data) {
//                itemView.setTag(data);
//                TextView tv = (TextView) itemView.findViewById(R.id.btn_tab);
//                tv.setText(data);
//            }
//
//            @NonNull
//            @Override
//            public String getData(View itemView) {
//                return (String) itemView.getTag();
//            }
//        });
//        mDragFlowLayout.prepareItemsByCount(10);
//        for (int i = 0; i < texts.size(); i++) {
//            int k=0;
//            if (i==0){
//                k=0;
//            }else {
//                k=1;
//            }
//            mDragFlowLayout.getDragItemManager().addItem(k, texts.get(i));
//        }
    }
}

package com.beijing.ocean.multmediademo.widge;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.beijing.ocean.multmediademo.activity.ImagePagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/27.
 */

public class MyTestJS {
    private Context context;

    public MyTestJS(Context context) {
        this.context = context;
    }
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        System.out.println(img);
        List<String> list=new ArrayList();
        list.add(img);
        ImagePagerActivity.startImagePagerActivity(context,list,0);
    }

    @android.webkit.JavascriptInterface
    public void copyText() {
        Log.e("tag", "copyText: "+"-----------------------------------------------" );
    }
}

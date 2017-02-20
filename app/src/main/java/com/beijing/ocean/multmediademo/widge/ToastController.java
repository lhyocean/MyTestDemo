package com.beijing.ocean.multmediademo.widge;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beijing.ocean.multimediademo.R;


/**
 * Created by lyq on 2016/5/12.
 */
public class ToastController {
    private Toast t;
    private Type mType;

    private Context context;
    private ImageView mfunctionIV;
    private ProgressBar mProgressBar;

    public ToastController(Context context, Type type) {
        this.context = context;
        this.mType = type;
    }

    public void show(float progress) {
        if (t == null) {
            t = new Toast(context);
            View layout = LayoutInflater.from(context).inflate(R.layout.volumn_view, null);
            mfunctionIV = (ImageView) layout.findViewById(R.id.function_iv);
            mProgressBar = (ProgressBar) layout.findViewById(R.id.progress_bar);
                   if(mType== Type.LIGHT_TYPE){
                       mfunctionIV.setImageResource(R.mipmap.light_pic);
                       mProgressBar.setMax(255);

                   }else if(mType == Type.VOLUMN_TYPE){
                       mfunctionIV.setImageResource(R.mipmap.volumn_pic);
                       mProgressBar.setMax(100);

                   }
            t.setView(layout);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.setDuration(Toast.LENGTH_SHORT);
        }
        mProgressBar.setProgress((int)progress);
        t.show();
    }



    public  enum  Type{
       VOLUMN_TYPE ,
        LIGHT_TYPE;
    }

}

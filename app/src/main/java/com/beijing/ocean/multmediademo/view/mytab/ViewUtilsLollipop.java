package com.beijing.ocean.multmediademo.view.mytab;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by ocean on 2017/5/3.
 */
public class ViewUtilsLollipop {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }
}

package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.beijing.ocean.multimediademo.R;

public class WebiframeActivity extends Activity {


    String s="<iframe frameborder=\"0\" width=\"640\" height=\"498\" src=\"https://v.qq.com/iframe/player.html?vid=o0381g4xyrx&tiny=0&auto=0 \" allowfullscreen></iframe>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webiframe);



         WebView mWebView= (WebView) findViewById(R.id.webView);

        WebSettings settings = mWebView.getSettings();


        settings.setJavaScriptEnabled(true);
//            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM); //----设置缩放级别
        settings.setDefaultTextEncodingName("UTF-8");//---设置编码格式
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(s);
                return true;
            }
        });

        mWebView.loadDataWithBaseURL("", s, "text/html", "UTF-8","");






    }
}

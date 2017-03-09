package com.beijing.ocean.multmediademo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.beijing.ocean.multimediademo.R;

import butterknife.Bind;

public class WebshowActivity extends Activity {


    @Bind(R.id.webView)
    WebView mWebView;
    private String mShowUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webshow);


        if( getIntent()!=null){
            mShowUrl = getIntent().getStringExtra("show_url");
        }


        if (!TextUtils.isEmpty(mShowUrl)) {
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
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }


            });

            WebChromeClient wvcc = new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);

                }
            };
// 设置setWebChromeClient对象
            mWebView.setWebChromeClient(wvcc);

            mWebView.loadUrl(mShowUrl);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    if(url.startsWith("http:") || url.startsWith("https:") ) {
                        view.loadUrl(url);
                        return false;
                    }else{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }

                }
            });
        }



    }
}

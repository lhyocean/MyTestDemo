package com.beijing.ocean.multmediademo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.Commen;
import com.beijing.ocean.multmediademo.widge.MyTestJS;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressLint("SetJavaScriptEnabled")
public class WebiframeActivity extends Activity {


    String s="<iframe frameborder=\"0\" width=\"640\" height=\"498\" src=\"https://v.qq.com/iframe/player.html?vid=o0381g4xyrx&tiny=0&auto=0 \" allowfullscreen></iframe>";
    private WebView mWebView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webiframe);


        mWebView = (WebView) findViewById(R.id.webView);

//        WebSettings settings = mWebView.getSettings();
//
//
//        settings.setJavaScriptEnabled(true);
////            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM); //----设置缩放级别
//        settings.setDefaultTextEncodingName("UTF-8");//---设置编码格式
//        settings.setAllowContentAccess(true);
//        settings.setAppCacheEnabled(false);
//        settings.setBuiltInZoomControls(false);
//        settings.setUseWideViewPort(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//
//
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(s);
//                return true;
//            }
//        });
//
//        mWebView.loadDataWithBaseURL("", s, "text/html", "UTF-8","");



         String htmlstr=getNewContent(Commen.htmltext2);


        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
//        ws.setBuiltInZoomControls(true);
//        ws.setLoadWithOverviewMode(true);
//        ws.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadDataWithBaseURL("", htmlstr, "text/html", "UTF-8","");
//        mWebView.loadDataWithBaseURL("", Commen.htmltext2, "text/html", "UTF-8","");

        mWebView.addJavascriptInterface(new MyTestJS(this),"imagelistner");
        mWebView.setWebViewClient(new MyWebViewClient());

    }

    private String getNewContent(String htmltext){

        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto").attr("align","");
        }

        Log.d("VACK", doc.toString());
        return doc.toString();
    }
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);

            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }
}

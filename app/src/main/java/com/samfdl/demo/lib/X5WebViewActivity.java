package com.samfdl.demo.lib;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.samfdl.demo.R;
import com.samfdl.demo.lib.x5.WalletPayEvent;
import com.samfdl.demo.lib.x5.X5JSBridge;
import com.samfdl.demo.lib.x5.X5WebChromeClientInterface;
import com.samfdl.demo.lib.x5.X5WebView;
import com.samfdl.demo.lib.x5.X5WebViewClientInterface;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

@SuppressLint("SetJavaScriptEnabled")
public class X5WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    X5WebView x5WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_x5webview);

        x5WebView = findViewById(R.id.x5webview);


        x5WebView.addJavascriptInterface(new X5JSBridge(this), "tinfiniteBridge");

        x5WebView.getX5WebViewClient().setX5WebViewClientInterface(new X5WebViewClientInterface() {

            @Override
            public void onPageFinished(WebView view, String url) {
//                placeHolderHeader.setText(getString(R.string.text_web_provider, AppUtil.getDomainName(url)));
//                view.loadUrl("javascript: " + getAssetFileContents(JS_BRIDGE_FILE_NAME));

                x5WebView.getSharePreviewImage();
            }

            @Override
            public void onPageStart(WebView view, String url) {
//                isLoadFinished = false;
                invalidateOptionsMenu();
            }
        });

        x5WebView.getX5WebChromeClient().setX5WeChromeInterface(new X5WebChromeClientInterface() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });

        x5WebView.setOnActionDownListener(new X5WebView.OnActionDownListener() {
            @Override
            public void onActionDown() {
            }
        });

//        x5WebView.setOnLongClickListener(new X5LongPressListenerWrapper(x5WebView, this));
        final WebSettings webSetting = x5WebView.getSettings();
        // 添加浏览器标识
//        String ua = webSetting.getUserAgentString() + " native_app_tinfinite/" + AppUtil.appVersionName();
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setUserAgentString(ua);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
//         webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);

        // 解决https中不能加载http资源问题
        if (Build.VERSION.SDK_INT >= 21) {
            webSetting.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        }

        x5WebView.loadUrl("http://tst.candy.one/jsbridge");

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    //    @Subscribe
    public void handlePayForStatus(WalletPayEvent event) {
        if (x5WebView != null && event != null) {
//            x5WebView.loadUrl("http://www.qq.com/");

//            x5WebView.loadUrl("javascript:alert(234567)");

//            x5WebView.loadUrl("javascript:tinfiniteListener.uploadCallback('abc')");
            x5WebView.loadUrl("javascript:tinfiniteListener.uploadCallback(JSON.stringify('" + event.json + "'))");
        }
    }

    @Override
    public void onClick(View view) {
        handlePayForStatus(new WalletPayEvent("来自java"));
    }
}
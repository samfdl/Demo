package com.samfdl.demo.lib.x5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebSettings;

/**
 * 封装论坛相关逻辑，简化交互流程
 */
public class X5SimplifiedWebView extends X5WebView {
    public X5SimplifiedWebView(Context context) {
        super(context);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5SimplifiedWebView(Context context, AttributeSet attribute) {
        super(context, attribute);

//        addJavascriptInterface(new X5JSBridge(getContext()), "tinfiniteBridge");

        final WebSettings webSetting = getSettings();
        // 添加浏览器标识
//        String ua = webSetting.getUserAgentString() + " Tinfinite/" + AppUtil.appVersionName();
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
        webSetting.setAppCachePath(context.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(context.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(context.getDir("geolocation", 0).getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
    }
}
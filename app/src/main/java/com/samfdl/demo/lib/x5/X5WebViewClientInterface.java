package com.samfdl.demo.lib.x5;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by caiying on 22/04/2016.
 */
public interface X5WebViewClientInterface {

    void onPageFinished(WebView view, String url);

    void onPageStart(WebView view, String url);
}
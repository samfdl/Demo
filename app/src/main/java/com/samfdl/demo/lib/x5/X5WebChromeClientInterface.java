package com.samfdl.demo.lib.x5;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by caiying on 22/04/2016.
 */
public interface X5WebChromeClientInterface {
    void onReceivedTitle(WebView view, String title);

    void onProgressChanged(WebView view, int newProgress);
}
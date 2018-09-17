package com.samfdl.demo.lib.x5;

import android.graphics.Bitmap;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by caiying on 22/04/2016.
 */
public class X5WebViewClient extends WebViewClient {
    private X5WebViewClientInterface mX5WebViewClientInterface;

    /**
     * 防止加载网页时调起系统浏览器
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView webview,
                                          com.tencent.smtt.export.external.interfaces.HttpAuthHandler httpAuthHandlerhost, String host,
                                          String realm) {
        boolean flag = httpAuthHandlerhost.useHttpAuthUsernamePassword();
    }

    @Override
    public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
//        AppUtil.setCookie(webView, s);
        if (mX5WebViewClientInterface != null) {
            mX5WebViewClientInterface.onPageStart(webView, s);
        }
        super.onPageStarted(webView, s, bitmap);
    }

    @Override
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        if (mX5WebViewClientInterface != null) {
            mX5WebViewClientInterface.onPageFinished(webView, url);
        }
    }

    public void setX5WebViewClientInterface(X5WebViewClientInterface x5WebViewClientInterface) {
        this.mX5WebViewClientInterface = x5WebViewClientInterface;
    }
}
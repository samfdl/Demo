package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.samfdl.demo.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_webview);

        webview = findViewById(R.id.webview);

        Bundle bundle = getIntent().getExtras();
        String projectUrl = null;
        if (bundle != null) {
            projectUrl = bundle.getString("projectUrl");
        }
        if (projectUrl != null) {
            webview.loadUrl(projectUrl);
        }

        // 防止调用外部浏览器
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
package com.samfdl.demo.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.samfdl.demo.R;
import com.samfdl.demo.lib.x5.X5WebView;

public class X5WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_x5webview);

        X5WebView x5webview = findViewById(R.id.x5webview);
        x5webview.loadUrl("http://tst.candy.one/jsbridge");
    }
}
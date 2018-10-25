package com.samfdl.demo;

import android.app.Application;

import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdkx5.YouZanSDKX5Adapter;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化有赞云SDK
        YouzanSDK.init(this, "client_id", new YouZanSDKX5Adapter());
    }
}
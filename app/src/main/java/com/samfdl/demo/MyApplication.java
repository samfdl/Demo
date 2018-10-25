package com.samfdl.demo;

import android.app.Application;

import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdkx5.YouZanSDKX5Adapter;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化有赞云SDK
        YouzanSDK.init(this, "292f38b8e5b6417f0c", new YouZanSDKX5Adapter());
    }
}
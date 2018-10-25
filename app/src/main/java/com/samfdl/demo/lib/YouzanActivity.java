package com.samfdl.demo.lib;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.samfdl.demo.R;
import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdk.YouzanToken;
import com.youzan.androidsdk.event.AbsAuthEvent;
import com.youzan.androidsdkx5.YouzanBrowser;

public class YouzanActivity extends AppCompatActivity {
    private YouzanBrowser webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_youzan);

        webview = findViewById(R.id.webview);

        setupYouzan();

        webview.loadUrl("https://h5.youzan.com/wscshop/home/UdAZylO6Ho");
    }

    private void setupYouzan() {
        //认证事件, 回调表示: 需要需要新的认证信息传入
        webview.subscribe(new AbsAuthEvent() {
            @Override
            public void call(Context context, boolean needLogin) {
                /**
                 * 建议实现逻辑:
                 *
                 *     判断App内的用户是否登录?
                 *       => 已登录: 请求带用户角色的认证信息(login接口);
                 *       => 未登录: needLogin为true, 唤起App内登录界面, 请求带用户角色的认证信息(login接口);
                 *       => 未登录: needLogin为false, 请求不带用户角色的认证信息(initToken接口).
                 *
                 *      服务端接入文档: https://www.youzanyun.com/docs/guide/appsdk/683
                 */
                //TODO 自行编码实现. 具体可参考开发文档中的伪代码实现

                //伪代码
                if (true) {
                    //       AppUserManager.isLogin()) { //判断App内的用户是否登录
                    //调用login接口, 获取数据, 组装成YouzanToken, 回传给SDK
                    YouzanToken token = new YouzanToken();
                    token.setAccessToken("1e58998a028a3272b50700a510a5466e");
                    token.setCookieKey("open_cookie_292f38b8e5b6417f0c");
                    token.setCookieValue("YZ505053888978661376YZJ5OytoRq");

                    // 这里注意调用顺序。先传递给sdk，再刷新view
                    YouzanSDK.sync(getApplicationContext(), token);
                    webview.sync(token);
                } else if (needLogin) {
//                    Intent intent = new Intent(YouzanActivity.this, LoginActivity.class);
//                    startActivityForResult(intent, REQUEST_CODE_LOGIN);
                } else {
                    //调用initToken接口, 获取数据, 组装成YouzanToken, 回传给 mView.sync()
                }
            }
        });
    }
}
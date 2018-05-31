package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.samfdl.demo.R;
import com.samfdl.demo.custom.view.IosActionSheet;
import com.samfdl.demo.custom.view.IosActionSheetLogout;

public class IosActionSheetActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_iosactionsheet);

        Button share = findViewById(R.id.share);
        share.setOnClickListener(this);

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                IosActionSheet mActionSheet = IosActionSheet.createBuilder(this)
                        .setListener(new IosActionSheet.ActionSheetListener() {
                            @Override
                            public void onClick(int index) {
                                switch (index) {
                                    case R.id.save:
//                                saveBitmap();
                                        break;
                                    case R.id.wechat:
//                                wechatShare(SendMessageToWX.Req.WXSceneSession);
                                        break;
                                    case R.id.friends:
//                                wechatShare(SendMessageToWX.Req.WXSceneTimeline);
                                        break;
                                    case R.id.weibo:
//                                weiboShare();
                                        break;
                                    case R.id.qq:
//                                qqShare();
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.logout:
                IosActionSheetLogout.createBuilder(this)
                        .setListener(new IosActionSheetLogout.ActionSheetListener() {
                            @Override
                            public void onClick(int index) {
                            }
                        }).show();
                break;
        }
    }
}
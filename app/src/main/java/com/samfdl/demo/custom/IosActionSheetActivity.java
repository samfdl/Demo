package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.samfdl.demo.R;
import com.samfdl.demo.custom.view.IosActionSheet;

public class IosActionSheetActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_iosactionsheet);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
    }
}
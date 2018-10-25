package com.samfdl.demo.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.samfdl.demo.R;
import com.samfdl.demo.lib.tools.QRCodeUtil;

public class YouzanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_qrcode);

        ImageView image = findViewById(R.id.image);
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap("I Love you!!!", 96, 96);
        image.setImageBitmap(mBitmap);
    }
}
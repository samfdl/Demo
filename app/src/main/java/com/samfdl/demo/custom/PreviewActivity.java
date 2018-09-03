package com.samfdl.demo.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.samfdl.demo.R;
import com.samfdl.demo.custom.view.SmoothImageView;

public class PreviewActivity extends AppCompatActivity {
    public static final String PHOTO_SOURCE_ID = "PHOTO_SOURCE_ID";
    public static final String PHOTO_SELECT_POSITION = "PHOTO_SELECT_POSITION";
    public static final String PHOTO_SELECT_X_TAG = "PHOTO_SELECT_X_TAG";
    public static final String PHOTO_SELECT_Y_TAG = "PHOTO_SELECT_Y_TAG";
    public static final String PHOTO_SELECT_W_TAG = "PHOTO_SELECT_W_TAG";
    public static final String PHOTO_SELECT_H_TAG = "PHOTO_SELECT_H_TAG";

    private int locationX;
    private int locationY;
    private int locationW;
    private int locationH;
    private int position;
    private String urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_preview);

        Intent intent = getIntent();
        if (intent != null) {
            urls = intent.getStringExtra(PHOTO_SOURCE_ID);
            position = intent.getIntExtra(PHOTO_SELECT_POSITION, 0);
            locationX = intent.getIntExtra(PHOTO_SELECT_X_TAG, 0);
            locationY = intent.getIntExtra(PHOTO_SELECT_Y_TAG, 0);
            locationW = intent.getIntExtra(PHOTO_SELECT_W_TAG, 0);
            locationH = intent.getIntExtra(PHOTO_SELECT_H_TAG, 0);
        }

        SmoothImageView smoothImageView = findViewById(R.id.smoothImageView);

        smoothImageView.setOriginalInfo(locationW, locationH, locationX, locationY);
        smoothImageView.transformIn();

        Glide.with(this)
                .load(urls)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.IMMEDIATE)
                .into(smoothImageView);

        smoothImageView.setOnTransformListener(new SmoothImageView.TransformListener() {
            @Override
            public void onTransformComplete(int mode) {
                if (mode == 2) {
                    PreviewActivity.this.finish();
                    PreviewActivity.this.overridePendingTransition(0, 0);
                }
            }
        });

        smoothImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SmoothImageView) v).transformOut();
            }
        });
    }
}
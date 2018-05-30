package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.samfdl.demo.R;
import com.samfdl.demo.custom.view.HeadZoomScrollView;

public class ReboundActivity extends AppCompatActivity implements View.OnTouchListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rebound);

        HeadZoomScrollView zoom_scroll = findViewById(R.id.zoom_scroll);
        zoom_scroll.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 回弹后 刷新数据
        }
        return false;
    }
}
package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.samfdl.demo.R;
import com.samfdl.demo.custom.view.ProgressRing;

public class ProgressRingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progressring);

        ProgressRing progressRing = findViewById(R.id.progressring);
        progressRing.setText("9");
    }
}
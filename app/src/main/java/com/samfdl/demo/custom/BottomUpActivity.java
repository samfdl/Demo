package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.samfdl.demo.R;

public class BottomUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bottomup);

        publish = findViewById(R.id.publish);
        publish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "onClick ", Toast.LENGTH_SHORT).show();
    }
}
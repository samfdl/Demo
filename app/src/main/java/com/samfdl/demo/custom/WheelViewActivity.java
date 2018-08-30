package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samfdl.demo.R;
import com.samfdl.demo.RecyclerViewAdapter;

import java.util.ArrayList;

public class WheelViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_wheelview);

        RecyclerView year = findViewById(R.id.year);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        year.setLayoutManager(linearLayoutManager);
        ArrayList list = new ArrayList<String>();
        list.add("RecyclerView");
        list.add("自定义控件");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);
        year.setAdapter(recyclerViewAdapter);
    }
}
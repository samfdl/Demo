package com.samfdl.demo.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samfdl.demo.R;
import com.samfdl.demo.RecyclerViewAdapter;
import com.tencent.stat.StatService;

import java.util.ArrayList;

public class LibActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList list = new ArrayList<String>();
        list.add("极光推送");
        list.add("腾讯统计");
        list.add("谷歌崩溃统计 firebase");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(recyclerViewAdapter);

        // 腾讯统计
        StatService.trackCustomKVEvent(this, "LibActivity", null);
    }
}
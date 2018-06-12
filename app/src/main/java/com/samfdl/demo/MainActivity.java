package com.samfdl.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samfdl.demo.custom.CustomActivity;
import com.samfdl.demo.function.SalaryActivity;
import com.tencent.stat.StatService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList list = new ArrayList<String>();
        list.add("RecyclerView");
        list.add("自定义控件");
        list.add("小功能");
        list.add("极光推送");
        list.add("腾讯统计");
        list.add("谷歌崩溃统计 firebase");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, CustomActivity.class);
                switch (position) {
                    case 1:
                        intent = new Intent(MainActivity.this, CustomActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, SalaryActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);

        // 腾讯统计
        StatService.trackCustomKVEvent(this, "homepage", null);
    }
}
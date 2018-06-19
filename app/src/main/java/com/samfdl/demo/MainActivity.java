package com.samfdl.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samfdl.demo.animation.GemActivity;
import com.samfdl.demo.custom.CustomActivity;
import com.samfdl.demo.function.SalaryActivity;
import com.samfdl.demo.graphic.GraphicActivity;
import com.samfdl.demo.lib.LibActivity;
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
        list.add("图形");
        list.add("动画");
        list.add("小功能");
        list.add("集成库详单");
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
                        intent = new Intent(MainActivity.this, GraphicActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, GemActivity.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, SalaryActivity.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, LibActivity.class);
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
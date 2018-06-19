package com.samfdl.demo.graphic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samfdl.demo.R;
import com.samfdl.demo.RecyclerViewAdapter;
import com.tencent.stat.StatService;

import java.util.ArrayList;

public class GraphicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList list = new ArrayList<String>();
        list.add("圆形");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GraphicActivity.this, CircleActivity.class);
                switch (position) {
                    case 0:
                        intent = new Intent(GraphicActivity.this, CircleActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);

        // 腾讯统计
        StatService.trackCustomKVEvent(this, "GraphicActivity", null);
    }
}
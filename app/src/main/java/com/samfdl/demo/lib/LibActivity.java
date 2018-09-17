package com.samfdl.demo.lib;

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

public class LibActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList list = new ArrayList<String>();
        list.add("ZXing二维码");
        list.add("极光推送");
        list.add("腾讯统计");
        list.add("谷歌崩溃统计 firebase");
        list.add("OkHttp");
        list.add("JsBridge");
        list.add("腾讯 X5 TBS 浏览器内核");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(LibActivity.this, QRCodeActivity.class);
                switch (position) {
                    case 0:
                        intent = new Intent(LibActivity.this, QRCodeActivity.class);
                        break;
                    case 5:
                        intent = new Intent(LibActivity.this, JsBridgeActivity.class);
                        break;
                    case 6:
                        intent = new Intent(LibActivity.this, X5WebViewActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);

        // 腾讯统计
        StatService.trackCustomKVEvent(this, "LibActivity", null);
    }
}
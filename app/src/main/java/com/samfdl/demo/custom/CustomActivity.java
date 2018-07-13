package com.samfdl.demo.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samfdl.demo.R;
import com.samfdl.demo.RecyclerViewAdapter;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList list = new ArrayList<String>();
        list.add("圆环渐变进度条");
        list.add("文字淡出屏幕效果");
        list.add("下拉刷新的阻尼回弹效果");
        list.add("仿IOS底部弹出菜单");
        list.add("软键盘弹出，底部控件上移");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CustomActivity.this, ProgressRingActivity.class);
                switch (position) {
                    case 0:
                        intent = new Intent(CustomActivity.this, ProgressRingActivity.class);
                        break;
                    case 1:
                        intent = new Intent(CustomActivity.this, FadeOutActivity.class);
                        break;
                    case 2:
                        intent = new Intent(CustomActivity.this, ReboundActivity.class);
                        break;
                    case 3:
                        intent = new Intent(CustomActivity.this, IosActionSheetActivity.class);
                        break;
                    case 4:
                        intent = new Intent(CustomActivity.this, BottomUpActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
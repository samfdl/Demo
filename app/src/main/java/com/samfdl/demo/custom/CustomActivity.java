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
        list.add("圆形头像");
        list.add("TextView超级链接");
        list.add("可折叠式标题栏");
        list.add("ViewPager");
        list.add("调用系统相机拍照");
        list.add("超长图");
        list.add("仿IOS日期选择器 wheelview");
        list.add("隐藏底部导航栏");
        list.add("调用系统相册");
        list.add("图片轮播（banner）");
        list.add("移动控件");
        list.add("View拖拽跟随手指移动");
        list.add("View拖拽可移动缩放");
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
                    case 5:
                        intent = new Intent(CustomActivity.this, CircleImageViewActivity.class);
                        break;
                    case 6:
                        intent = new Intent(CustomActivity.this, TextViewLinkActivity.class);
                        break;
                    case 7:
                        intent = new Intent(CustomActivity.this, CollapsingToolbarActivity.class);
                        break;
                    case 8:
                        intent = new Intent(CustomActivity.this, ViewPagerActivity.class);
                        break;
                    case 9:
                        intent = new Intent(CustomActivity.this, PhotoActivity.class);
                        break;
                    case 10:
                        intent = new Intent(CustomActivity.this, LongPictureActivity.class);
                        break;
                    case 11:
                        intent = new Intent(CustomActivity.this, WheelViewActivity.class);
                        break;
                    case 12:
                        intent = new Intent(CustomActivity.this, HideVirtualKeysActivity.class);
                        break;
                    case 13:
                        intent = new Intent(CustomActivity.this, AlbumActivity.class);
                        break;
                    case 14:
                        intent = new Intent(CustomActivity.this, BannerActivity.class);
                        break;
                    case 15:
                        intent = new Intent(CustomActivity.this, MoveViewActivity.class);
                        break;
                    case 16:
                        intent = new Intent(CustomActivity.this, DragViewActivity.class);
                        break;
                    case 17:
                        intent = new Intent(CustomActivity.this, DragScaleViewActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samfdl.demo.R;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewpager);

        viewpager = findViewById(R.id.viewpager);

        final ArrayList<View> views = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        views.add(li.inflate(R.layout.activity_animation_gem, null));
        views.add(li.inflate(R.layout.activity_animation_gem, null));
        views.add(li.inflate(R.layout.activity_animation_gem, null));
        //需要给ViewPager设置适配器
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            //有多少个切换页
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return views.size();
            }

            //对超出范围的资源进行销毁
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                //super.destroyItem(container, position, object);
                container.removeView(views.get(position));
            }

            //对显示的资源进行初始化
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                //return super.instantiateItem(container, position);
                container.addView(views.get(position));
                return views.get(position);
            }
        };
        viewpager.setAdapter(adapter);
    }
}
package com.samfdl.demo.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.samfdl.demo.R;

import java.util.Timer;
import java.util.TimerTask;

public class GemActivity extends AppCompatActivity implements View.OnClickListener {
    // 中间宝石
    private View light;
    private View gem_center;

    // 飞出屏幕的宝石幻影组
    private View fly_gem;
    private View fly_gem1;
    private View fly_gem2;
    private View fly_gem3;
    private View fly_gem4;
    private View gem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_gem);

        light = findViewById(R.id.light);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_gem_light);
        light.startAnimation(animation);

        gem_center = findViewById(R.id.gem_center);
        gem_center.setOnClickListener(this);

        fly_gem = findViewById(R.id.fly_gem);
        fly_gem1 = findViewById(R.id.fly_gem1);
        fly_gem2 = findViewById(R.id.fly_gem2);
        fly_gem3 = findViewById(R.id.fly_gem3);
        fly_gem4 = findViewById(R.id.fly_gem4);
        gem = findViewById(R.id.gem);
    }

    @Override
    public void onClick(View v) {
        Timer timer = new Timer();
        timer.schedule(getTask(fly_gem, 0), 0);
        timer.schedule(getTask(fly_gem1, 0), 200);
        timer.schedule(getTask(fly_gem2, 0), 400);
        timer.schedule(getTask(fly_gem3, 0), 600);
        timer.schedule(getTask(fly_gem4, 1), 800);
        timer.schedule(getTask(gem, 2), 1210);
        timer.schedule(getTask(gem, 3), 2100);
        timer.schedule(getTask(gem, 4), 2940);
    }

    private TimerTask getTask(final View view, final int what) {
        return new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.obj = view;
                msg.what = what;
                handler.sendMessage(msg);
            }
        };
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            View view = (View) msg.obj;
            Animation animation = AnimationUtils.loadAnimation(GemActivity.this, R.anim.animation_gem_fly);
            if (msg.what == 0) {
                animation = AnimationUtils.loadAnimation(GemActivity.this, R.anim.animation_gem_fly);
            } else if (msg.what == 1) {
                animation = AnimationUtils.loadAnimation(GemActivity.this, R.anim.animation_gem_fly1);
            } else if (msg.what == 2) {
                animation = AnimationUtils.loadAnimation(GemActivity.this, R.anim.animation_gem_fly2);
                fly_gem.setVisibility(View.GONE);
                fly_gem1.setVisibility(View.GONE);
                fly_gem2.setVisibility(View.GONE);
                fly_gem3.setVisibility(View.GONE);
                fly_gem4.setVisibility(View.GONE);
            } else if (msg.what == 3) {
                AnimationDrawable anim = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_gem_flash);
                view.setBackground(anim);
                anim.start();
                return;
            } else {
                view.setBackgroundResource(R.mipmap.gem);
                return;
            }
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
    };
}
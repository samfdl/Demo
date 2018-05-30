package com.samfdl.demo.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.samfdl.demo.R;

public class FadeOutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_fadeout);

        final TextView fadeout_text = findViewById(R.id.fadeout_text);

        ObjectAnimator mFadeOutObjectAnimator;
        //由于淡出，是向上移动，坐标为负，这里设定向上移动350个像素
        mFadeOutObjectAnimator = ObjectAnimator.ofFloat(fadeout_text, "translationY", 0, -350);
        //动画执行时间设定为2000毫秒
        mFadeOutObjectAnimator.setDuration(2000);
        mFadeOutObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //当前动画时间点的动画值，在0到-350之间
                float value = (float) animation.getAnimatedValue();
                //由于不透明度取值为0-1，故而除以350，value/350取值在-1到0之间
                fadeout_text.setAlpha(1 + value / 350);
            }
        });
        mFadeOutObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画执行前修改TextView的值
                fadeout_text.setText("+50g");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        mFadeOutObjectAnimator.start();
    }
}
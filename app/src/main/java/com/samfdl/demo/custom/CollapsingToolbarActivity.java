package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.samfdl.demo.R;

public class CollapsingToolbarActivity extends AppCompatActivity {
    AppBarLayout app_bar;
    ImageView image;

    View title_background;
    LinearLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collapsingtoolbar);

        app_bar = findViewById(R.id.app_bar);
        image = findViewById(R.id.image);

        title_background = findViewById(R.id.title_background);
        tab = findViewById(R.id.tab);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //滑动一次 得到渐变缩放值
                float alphaScale = -verticalOffset / 539f;

                title_background.setAlpha(alphaScale);
                image.setAlpha(1.0f - alphaScale);
            }
        });
    }
}
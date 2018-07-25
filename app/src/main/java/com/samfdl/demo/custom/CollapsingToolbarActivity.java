package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.samfdl.demo.R;

public class CollapsingToolbarActivity extends AppCompatActivity {
    RelativeLayout frameLayout;

    AppBarLayout app_bar;
    ImageView image;

    View title_background;
    LinearLayout tab;

    private RelativeLayout.LayoutParams params;

    private float toolBarHeight;    //toolBar高度
    private float offSetHeight;     //总高度 -  toolBar高度  布局位移值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collapsingtoolbar);

        frameLayout = findViewById(R.id.frameLayout);

        app_bar = findViewById(R.id.app_bar);
        image = findViewById(R.id.image);

        title_background = findViewById(R.id.title_background);
        tab = findViewById(R.id.tab);

        title_background.setAlpha(0f);

        //第一次进入获取高度，以及差值 高度差比值
        params = (RelativeLayout.LayoutParams) tab.getLayoutParams();

        final float totalHeight = params.topMargin;
        toolBarHeight = getResources().getDimension(R.dimen.custom_collapsingtoolbar_tool_bar_height);
        offSetHeight = totalHeight - toolBarHeight;

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //滑动一次 得到渐变缩放值
                float alphaScale = -verticalOffset / offSetHeight;

                //计算maigintop值
                float distance = totalHeight + verticalOffset;

                title_background.setAlpha(alphaScale);
                image.setAlpha(1.0f - alphaScale);
                params.setMargins(0, (int) distance, 0, 0);

                frameLayout.requestLayout();
            }
        });
    }
}
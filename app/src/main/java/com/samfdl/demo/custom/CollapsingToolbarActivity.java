package com.samfdl.demo.custom;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.samfdl.demo.R;

public class CollapsingToolbarActivity extends AppCompatActivity {
    private float totalHeight;      //总高度
    private float toolBarHeight;    //toolBar高度
    private float offSetHeight;     //总高度 -  toolBar高度  布局位移值
    private float llHeight;         //搜索框高度

    private float llHeightOffScale;     //高度差比值
    private float llOffDistance;        //距离差
    private float llOffDistanceScale;   //距离差比值
    private FrameLayout.LayoutParams params;

    FrameLayout frameLayout;

    AppBarLayout app_bar;
    ImageView image;

    RelativeLayout title;
    LinearLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collapsingtoolbar);

        frameLayout = findViewById(R.id.frameLayout);

        app_bar = findViewById(R.id.app_bar);
        image = findViewById(R.id.image);

        title = findViewById(R.id.title);
        tab = findViewById(R.id.tab);

        title.setAlpha(0f);
        totalHeight = getResources().getDimension(R.dimen.custom_collapsingtoolbar_app_bar_height);
        toolBarHeight = getResources().getDimension(R.dimen.custom_collapsingtoolbar_tool_bar_height);
        offSetHeight = totalHeight - toolBarHeight;

        /**
         *   移动效果值／最终效果值 =  移动距离／ 能移动总距离（确定）
         */
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //第一次进入获取高度，以及差值 高度差比值
                if (llHeight == 0) {
                    llHeight = tab.getMeasuredHeight();
                    params = (FrameLayout.LayoutParams) tab.getLayoutParams();

                    //算出高度偏移量比值  相对与llHeight
                    llHeightOffScale = 1.0f - (toolBarHeight / llHeight);

                    //得到滑动差值 就是布局marginTop
                    llOffDistance = params.topMargin;
                    //得到滑动比值
                    llOffDistanceScale = llOffDistance / offSetHeight;
                }

                //滑动一次 得到渐变缩放值
                float alphaScale = (-verticalOffset) / offSetHeight;

                //获取高度缩放值
                float llHeightScale = 1.0f - (llHeightOffScale * ((-verticalOffset) / offSetHeight));
                //计算maigintop值
                float distance = llOffDistance - (-verticalOffset) * llOffDistanceScale;

                image.setAlpha(1.0f - alphaScale);
                title.setAlpha(alphaScale);
                params.height = (int) (llHeight * llHeightScale);
                params.setMargins(0, (int) distance, 0, 0);

                frameLayout.requestLayout();
            }
        });
    }
}
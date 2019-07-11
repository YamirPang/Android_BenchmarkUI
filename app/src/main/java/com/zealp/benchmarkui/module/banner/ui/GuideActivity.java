package com.zealp.benchmarkui.module.banner.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zealp.benchmark_ui.widget.banner.anim.select.ZoomInEnter;
import com.zealp.benchmark_ui.widget.banner.transform.FadeSlideTransformer;
import com.zealp.benchmark_ui.widget.banner.widget.banner.SimpleGuideBanner;
import com.zealp.benchmarkui.R;
import com.zealp.benchmarkui.utils.DemoDataProvider;

/**
 * GuideActivity:
 * Author: ZealP
 * Created On: 2019/7/11
 */
public class GuideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_guide);
        initGuide();
    }
    private void initGuide(){
        SimpleGuideBanner banner = findViewById(R.id.launch_guide_banner);
        banner
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(FadeSlideTransformer.class)
                .barPadding(0, 10, 0, 10)
                .setSource(DemoDataProvider.getUsertGuides())
                .startScroll();

        banner.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                Toast.makeText(GuideActivity.this, "关闭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

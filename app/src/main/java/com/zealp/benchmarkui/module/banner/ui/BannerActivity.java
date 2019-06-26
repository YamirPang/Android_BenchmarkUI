package com.zealp.benchmarkui.module.banner.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zealp.benchmark_ui.widget.banner.anim.select.ZoomInEnter;
import com.zealp.benchmark_ui.widget.banner.widget.banner.BannerItem;
import com.zealp.benchmark_ui.widget.banner.widget.banner.SimpleCustomImageBanner;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.BaseBanner;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.BaseCustomBanner;
import com.zealp.benchmarkui.R;
import com.zealp.benchmarkui.utils.DemoDataProvider;

import java.util.List;

/**
 * BannerActivity:
 * Author: ZealP
 * Created On: 2019/6/26
 */
public class BannerActivity extends AppCompatActivity {

    SimpleCustomImageBanner mBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mBanner = findViewById(R.id.sib_anim);
        initBanner();
    }

    private void initBanner(){
        List<BannerItem> mData = DemoDataProvider.getBannerList();
        mBanner.setSelectAnimClass(ZoomInEnter.class)
                .setSource(mData)
                .setOnItemClickL(new BaseCustomBanner.OnItemClickL() {
                    @Override
                    public void onItemClick(int position) {
//                        ToastUtils.toast("position--->" + position);
                    }
                })
                .startScroll();
    }
}

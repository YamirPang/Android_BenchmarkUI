package com.zealp.benchmark_ui.widget.banner.transform;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * 翻转切换
 *
 * @author xuexiang
 * @since 2019/1/14 下午10:12
 */
public class FlowTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        ViewHelper.setRotationY(page, position * -30f);
    }
}

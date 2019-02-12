package com.zealp.benchmark_ui.config;

import android.graphics.drawable.Drawable;

import com.zealp.benchmark_ui.common.BenchmarkUI;
import com.zealp.benchmark_ui.utils.CommonUtils;

/**
 * Author: ZealP
 * Created On: 2019/2/12
 */
public class UIConfig {

    private static volatile UIConfig sInstance = null;

    /**
     * 应用的图标
     */
    private Drawable mAppIcon;

    private UIConfig() {
        mAppIcon = CommonUtils.getAppIcon(BenchmarkUI.getContext());
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static UIConfig getInstance() {
        if (sInstance == null) {
            synchronized (UIConfig.class) {
                if (sInstance == null) {
                    sInstance = new UIConfig();
                }
            }
        }
        return sInstance;
    }

    //================== 应用 =====================

    public UIConfig setAppIcon(Drawable appIcon) {
        mAppIcon = appIcon;
        return this;
    }

    public Drawable getAppIcon() {
        return mAppIcon;
    }
}

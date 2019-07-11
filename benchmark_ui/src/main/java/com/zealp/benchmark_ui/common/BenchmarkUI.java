package com.zealp.benchmark_ui.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Author: Dev_@ZealP
 * Created On: 2019/2/12
 * Email: zealpeng@163.com
 * Description:
 */
public class BenchmarkUI {

    private static volatile BenchmarkUI sInstance = null;

    private static Application sContext;

    private static boolean sIsTabletChecked;

    private static int sScreenType;

    private BenchmarkUI() {
    }

    public static BenchmarkUI getInstance() {
        if (sInstance == null) {
            synchronized (BenchmarkUI.class) {
                if (sInstance == null) {
                    sInstance = new BenchmarkUI();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Application context) {
        sContext = context;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在Application中初始化组件");
        }
    }

//=======================字体===========================//
    /**
     * @return 获取默认字体
     */
    public static Typeface getDefaultTypeface() {
        String fontPath = CalligraphyConfig.get().getFontPath();
        if (!TextUtils.isEmpty(fontPath)) {
            return TypefaceUtils.load(getContext().getAssets(), fontPath);
        }
        return null;
    }

    /**
     * @param fontPath 字体路径
     * @return 获取默认字体
     */
    public static Typeface getDefaultTypeface(String fontPath) {
        if (TextUtils.isEmpty(fontPath)) {
            fontPath = CalligraphyConfig.get().getFontPath();
        }
        if (!TextUtils.isEmpty(fontPath)) {
            return TypefaceUtils.load(getContext().getAssets(), fontPath);
        }
        return null;
    }
}

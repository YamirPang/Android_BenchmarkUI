package com.zealp.benchmark_ui.common;

import android.app.Application;
import android.content.Context;

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


}

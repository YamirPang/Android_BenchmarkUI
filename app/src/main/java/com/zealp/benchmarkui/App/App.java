package com.zealp.benchmarkui.App;

import android.app.Application;

import com.zealp.benchmark_ui.common.BenchmarkUI;

/**
 * Author: ZealP
 * Created On: 2019/2/12
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BenchmarkUI.init(this);
    }
}

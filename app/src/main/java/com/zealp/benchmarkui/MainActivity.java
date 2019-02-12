package com.zealp.benchmarkui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.zealp.benchmark_ui.widget.loading.LoadingDialog;

public class MainActivity extends AppCompatActivity {

    LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoadingView();
    }

    /**
     * 初始化加载页面
     */
    private void initLoadingView() {
        mDialog = new LoadingDialog(this);
        mDialog.setIconScale(0.8f).setLoadingSpeed(5);
        mDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isLoading())
                    mDialog.updateMsg("识别中...");
            }
        }, 5000);
    }

    @Override
    protected void onDestroy() {
        if (mDialog != null)
            mDialog.recycle();
        super.onDestroy();
    }
}

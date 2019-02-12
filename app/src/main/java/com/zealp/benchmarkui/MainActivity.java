package com.zealp.benchmarkui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.zealp.benchmark_ui.widget.loading.ChrysanthemumLoadingDialog;
import com.zealp.benchmark_ui.widget.loading.LoadingDialog;

public class MainActivity extends AppCompatActivity {

    LoadingDialog mDialog;
    ChrysanthemumLoadingDialog mChrysanthemumDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initLoadingView();
        initChrysanthemumLoadingView();
    }

    /**
     * 初始化加载页面
     */
    private void initChrysanthemumLoadingView() {
        mChrysanthemumDialog = new ChrysanthemumLoadingDialog(this);
        mChrysanthemumDialog.setCancelable(true);
        mChrysanthemumDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mChrysanthemumDialog != null && mChrysanthemumDialog.isLoading())
                    mChrysanthemumDialog.dismiss();
            }
        }, 5000);
    }

    /**
     * 初始化加载页面
     */
    private void initLoadingView() {
        mDialog = new LoadingDialog(this);
        mDialog.setLoadingIcon(R.drawable.app_logo).setIconScale(0.5f).setLoadingSpeed(7);
        mDialog.setCancelable(true);
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

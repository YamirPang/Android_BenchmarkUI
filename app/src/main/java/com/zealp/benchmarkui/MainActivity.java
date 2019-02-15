package com.zealp.benchmarkui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zealp.benchmark_ui.widget.loading.ChrysanthemumLoadingDialog;
import com.zealp.benchmark_ui.widget.loading.LoadingDialog;
import com.zealp.benchmarkui.module.loading.ui.LoadingActivity;
import com.zealp.benchmarkui.module.photopicker.ui.PhotoPickerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        routerAction("loading");
        routerAction("photopicker");
    }

    private void routerAction(String className) {
        Intent intent = new Intent();
        switch (className) {
            case "loading":
                intent.setClass(this, LoadingActivity.class);
                startActivity(intent);
                break;

            case "photopicker":
                intent.setClass(this, PhotoPickerActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}

package com.zealp.benchmarkui;

import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zealp.benchmark_ui.widget.loading.ChrysanthemumLoadingDialog;
import com.zealp.benchmark_ui.widget.loading.LoadingDialog;
import com.zealp.benchmarkui.module.loading.ui.LoadingActivity;
import com.zealp.benchmarkui.module.photopicker.ui.PhotoPickerActivity;
import com.zealp.benchmarkui.module.ratingstar.ui.RatingStarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        routerAction("loading");
        routerAction("photopicker");
//        routerAction("ratingstar");
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

            case "ratingstar":
                intent.setClass(this, RatingStarActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}

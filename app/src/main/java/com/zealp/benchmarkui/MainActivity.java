package com.zealp.benchmarkui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.zealp.benchmarkui.module.banner.ui.BannerActivity;
import com.zealp.benchmarkui.module.image.ui.PreviewActivity;
import com.zealp.benchmarkui.module.loading.ui.LoadingActivity;
import com.zealp.benchmarkui.module.photopicker.ui.PhotoPickerActivity;
import com.zealp.benchmarkui.module.ratingstar.ui.RatingStarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        routerAction("loading");
//        routerAction("photopicker");
//        routerAction("ratingstar");
//        routerAction("preview");
        routerAction("banner");
    }

    private void routerAction(String className) {
        Intent intent = new Intent();
        switch (className) {
            /**
             * 加载页面
             */
            case "loading":
                intent.setClass(this, LoadingActivity.class);
                startActivity(intent);
                this.finish();
                break;

            /**
             * 图片选择器
             */
            case "photopicker":
                intent.setClass(this, PhotoPickerActivity.class);
                startActivity(intent);
                this.finish();
                break;

            /**
             * 打分控件
             */
            case "ratingstar":
                intent.setClass(this, RatingStarActivity.class);
                startActivity(intent);
                this.finish();
                break;

            /**
             * 图片预览
             */
            case "preview":
                intent.setClass(this, PreviewActivity.class);
                startActivity(intent);
                this.finish();
                break;

            /**
             * 轮播图
             */
            case "banner":
                intent.setClass(this, BannerActivity.class);
                startActivity(intent);
                this.finish();
                break;

            default:
        }
    }
}

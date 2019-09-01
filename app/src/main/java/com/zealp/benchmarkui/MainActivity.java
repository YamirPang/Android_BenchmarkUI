package com.zealp.benchmarkui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.zealp.benchmarkui.module.banner.ui.BannerActivity;
import com.zealp.benchmarkui.module.banner.ui.GuideActivity;
import com.zealp.benchmarkui.module.image.ui.PreviewActivity;
import com.zealp.benchmarkui.module.loading.ui.LoadingActivity;
import com.zealp.benchmarkui.module.photopicker.ui.PhotoPickerActivity;
import com.zealp.benchmarkui.module.ratingstar.ui.RatingStarActivity;
import com.zealp.benchmarkui.module.textview.ui.SuperTextViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        routerAction("loading");
//        routerAction("photopicker");
//        routerAction("ratingstar");
//        routerAction("preview");
//        routerAction("supertextview");
        routerAction("banner");
//        routerAction("guide");
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

            /**
             * 启动引导页
             */
            case "guide":
                intent.setClass(this, GuideActivity.class);
                startActivity(intent);
                this.finish();
                break;

            /**
             * 自定义文本控件
             */
            case "supertextview":
                intent.setClass(this, SuperTextViewActivity.class);
                startActivity(intent);
                this.finish();
                break;

            default:
        }
    }
}

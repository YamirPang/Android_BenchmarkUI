package com.zealp.benchmarkui;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

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
        routerAction("preview");
    }

    private void routerAction(String className) {
        Intent intent = new Intent();
        switch (className) {
            case "loading":
                intent.setClass(this, LoadingActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case "photopicker":
                intent.setClass(this, PhotoPickerActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case "ratingstar":
                intent.setClass(this, RatingStarActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case "preview":
                intent.setClass(this, PreviewActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
        }
    }
}

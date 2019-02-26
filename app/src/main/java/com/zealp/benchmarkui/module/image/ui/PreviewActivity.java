package com.zealp.benchmarkui.module.image.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zealp.benchmark_ui.widget.preview.PreviewBuilder;
import com.zealp.benchmark_ui.widget.preview.entity.IImagePreview;
import com.zealp.benchmarkui.R;
import com.zealp.benchmarkui.module.image.entity.ImageEnt;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ZealP
 * Created On: 2019/2/25
 */
public class PreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<IImagePreview> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(new ImageEnt("http://pic1.win4000.com/wallpaper/c/539925ed63eab.jpg"));
//        }
        list.add(new ImageEnt("http://pic1.win4000.com/wallpaper/c/539925ed63eab.jpg"));
        list.add(new ImageEnt("http://newsimg.5054399.com/uploads/userup/1712/2612022235Z.jpg"));
        list.add(new ImageEnt("http://sj.xiaopi.com/uploadfile/2018/1112/20181112101436987.jpg"));

        initPreview(list);
    }

    private void initPreview(List<IImagePreview> list){
        PreviewBuilder.from(this)
                .setImgs(list)
                .setCurrentIndex(1)
                .setType(PreviewBuilder.IndicatorType.Dot)
                .start();//启动
    }
}

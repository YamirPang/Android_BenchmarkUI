package com.zealp.benchmarkui.module.textview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zealp.benchmark_ui.widget.textview.supertextview.SuperTextView;
import com.zealp.benchmarkui.R;

/**
 * SuperTextViewActivity:
 * Author: ZealP
 * Created On: 2019/6/27
 */
public class SuperTextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_textview);
        SuperTextView textview = findViewById(R.id.super01);
        initNetPicTextView(textview);
    }

    /**
     * 带网络图片的textview
     */
    private void initNetPicTextView(final SuperTextView textView){
        Glide.with(this)
                .load("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3860616424,1789830124&fm=80&w=179&h=119&img.PNG")
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        textView.setRightTvDrawableRight(resource);
                    }
                });
    }
}

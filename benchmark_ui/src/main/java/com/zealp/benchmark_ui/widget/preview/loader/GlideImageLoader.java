package com.zealp.benchmark_ui.widget.preview.loader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Glide 图片加载
 *
 * @author xuexiang
 * @since 2018/12/6 下午5:26
 */
public class GlideImageLoader implements ImageLoader {
    /**
     * 展示图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .into(imageView);
    }

    /**
     * 展示图片
     *
     * @param context
     * @param path
     * @param imageView
     * @param width
     * @param height
     * @param placeholder
     * @param strategy
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView, int width, int height, Drawable placeholder, DiskCacheStrategy strategy) {
        Glide
                .with(context)
                .load(path)
                .centerCrop()
                .override(width, height)
                .placeholder(placeholder)
                .diskCacheStrategy(strategy)
                .into(imageView);
    }
}
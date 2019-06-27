package com.zealp.benchmark_ui.widget.preview.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.utils.GlideApp;

/**
 * Glide多媒体加载
 *
 * @author xuexiang
 * @since 2018/12/8 下午5:50
 */
public class GlideMediaLoader implements IMediaLoader {

    public GlideMediaLoader() {
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final ISimpleTarget simpleTarget) {
        GlideApp.with(context)
                .asBitmap()
                .load(path)
//                .placeholder(R.drawable.benchmark_ui_ic_default_img)
                .error(R.drawable.benchmark_ui_ic_no_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Bitmap>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 加载gif 图
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    @Override
    public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final ISimpleTarget simpleTarget) {
        GlideApp.with(context)
                .asGif()
                .load(path)
                .placeholder(R.drawable.benchmark_ui_ic_default_img)
                .error(R.drawable.benchmark_ui_ic_no_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 停止
     *
     * @param context 容器
     **/
    @Override
    public void onStop(@NonNull Fragment context) {
        GlideApp.with(context).onStop();
    }

    /**
     * 停止
     *
     * @param c 容器
     **/
    @Override
    public void clearMemory(@NonNull Context c) {
        GlideApp.get(c).clearMemory();
    }
}

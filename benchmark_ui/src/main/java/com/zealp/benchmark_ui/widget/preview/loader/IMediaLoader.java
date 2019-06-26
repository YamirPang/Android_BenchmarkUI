package com.zealp.benchmark_ui.widget.preview.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * 加载器接口
 *
 * Author: ZealP
 * Created On: 2019/2/25
 */
public interface IMediaLoader {

    /**
     * 加载图片
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull ISimpleTarget simpleTarget);

    /**
     * 加载gif 图
     *
     * @param context
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull ISimpleTarget simpleTarget);

    /**
     * 停止
     *
     * @param context 容器
     **/
    void onStop(@NonNull Fragment context);

    /**
     * 停止
     *
     * @param c 容器
     **/
    void clearMemory(@NonNull Context c);
}

package com.zealp.benchmark_ui.widget.preview;

import com.zealp.benchmark_ui.widget.preview.loader.GlideImageLoader;
import com.zealp.benchmark_ui.widget.preview.loader.GlideMediaLoader;
import com.zealp.benchmark_ui.widget.preview.loader.IMediaLoader;
import com.zealp.benchmark_ui.widget.preview.loader.ImageLoader;

/**
 * Author: ZealP
 * Created On: 2019/2/25
 */
public class MediaLoader {

    private static volatile MediaLoader sInstance = null;

    private volatile IMediaLoader mLoader;

    private volatile ImageLoader mImageLoader;

    private MediaLoader() {

    }

    /**
     * 获取单例
     *
     * @return
     */
    public static MediaLoader getInstance() {
        if (sInstance == null) {
            synchronized (MediaLoader.class) {
                if (sInstance == null) {
                    sInstance = new MediaLoader();
                }
            }
        }
        return sInstance;
    }

    public static IMediaLoader get() {
        return getInstance().getLoader();
    }


    /**
     * 初始化加载图片类
     *
     * @param loader
     */
    public void initLoader(IMediaLoader loader) {
        mLoader = loader;
    }

    public IMediaLoader getLoader() {
        if (mLoader == null) {
            mLoader = new GlideMediaLoader();
        }
        return mLoader;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new GlideImageLoader();
        }
        return mImageLoader;
    }
}

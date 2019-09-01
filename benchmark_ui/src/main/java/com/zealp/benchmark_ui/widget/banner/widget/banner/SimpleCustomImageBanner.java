package com.zealp.benchmark_ui.widget.banner.widget.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.BaseCustomIndicatorBanner;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.GlideImageLoader;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.ImageLoader;
import com.zealp.benchmark_ui.widget.image.RoundImageView;

import java.lang.ref.WeakReference;

/**
 * 简单的图片轮播
 *
 * @author xuexiang
 * @since 2019/1/14 下午10:07
 */
public class SimpleCustomImageBanner extends BaseCustomIndicatorBanner<BannerItem, SimpleCustomImageBanner> {
    /**
     * 默认加载图片
     */
    private ColorDrawable mColorDrawable;
    /**
     * 是否允许进行缓存
     */
    private boolean mEnableCache = true;
    /**
     * 高／宽比率
     */
    private double mScale = 0.5625D;

    private ImageLoader mImageLoader;

    public SimpleCustomImageBanner(Context context) {
        super(context);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    public SimpleCustomImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    public SimpleCustomImageBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    @Override
    public void onTitleSelect(TextView tv, TextView tv2, int position) {
        final BannerItem item = getItem(position);
        if (item != null) {
            tv.setText(item.title);
            tv2.setText(item.subTitle);
        }
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.benchmarkui_adapter_simple_image, null);
        RoundImageView iv = inflate.findViewById(R.id.iv);
        //解决Glide资源释放的问题，详细见http://blog.csdn.net/shangmingchao/article/details/51125554
        WeakReference<RoundImageView> imageViewWeakReference = new WeakReference<>(iv);
        RoundImageView target = imageViewWeakReference.get();
        BannerItem item = getItem(position);
        if (item != null && target != null) {
            loadingImageView(target, item);
        }
        return inflate;
    }

    private ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new GlideImageLoader();
        }
        return mImageLoader;
    }

    /**
     * 加载图片
     *
     * @param iv
     * @param item
     */
    protected void loadingImageView(ImageView iv, BannerItem item) {
        int itemWidth = mDisplayMetrics.widthPixels;
        int itemHeight = (int) (itemWidth * mScale);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth , LinearLayout.LayoutParams.MATCH_PARENT);
//        lp.setMargins(10, 0, 10, 0);
        iv.setLayoutParams(lp);
//        iv.setScaleType(ImageView.ScaleType.);

        if (!TextUtils.isEmpty(item.imgUrl)) {
            String imgUrl = item.imgUrl;
            if (!TextUtils.isEmpty(imgUrl)) {
                getImageLoader().displayImage(mContext, imgUrl, iv,
                        itemWidth, itemHeight, mColorDrawable,
                        mEnableCache ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE);
            } else {
                iv.setImageDrawable(mColorDrawable);
            }

        } else if (item.imgRes != 0) {
            getImageLoader().displayImage(mContext, item.imgRes, iv,
                    itemWidth, itemHeight, mColorDrawable,
                    mEnableCache ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE);
        } else {
            iv.setImageDrawable(mColorDrawable);
        }
    }

    /**
     * 设置是否允许缓存
     *
     * @param enableCache
     * @return
     */
    public SimpleCustomImageBanner enableCache(boolean enableCache) {
        mEnableCache = enableCache;
        return this;
    }

    /**
     * 获取是否允许缓存
     *
     * @return
     */
    public boolean getEnableCache() {
        return mEnableCache;
    }

    public ColorDrawable getColorDrawable() {
        return mColorDrawable;
    }

    public SimpleCustomImageBanner setColorDrawable(ColorDrawable colorDrawable) {
        mColorDrawable = colorDrawable;
        return this;
    }

    public double getScale() {
        return mScale;
    }

    public SimpleCustomImageBanner setScale(double scale) {
        mScale = scale;
        return this;
    }

    public SimpleCustomImageBanner setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        return this;
    }

    //解决内存泄漏的问题
    @Override
    protected void onDetachedFromWindow() {
        pauseScroll();
        super.onDetachedFromWindow();
    }
}

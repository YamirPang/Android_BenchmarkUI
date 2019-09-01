package com.zealp.benchmark_ui.widget.banner.widget.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.BaseIndicatorBanner;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.GlideImageLoader;
import com.zealp.benchmark_ui.widget.banner.widget.banner.base.ImageLoader;

/**
 * 简单的引导页
 *
 * @author xuexiang
 * @since 2018/12/6 下午4:32
 */
public class SimpleGuideBanner extends BaseIndicatorBanner<Integer, SimpleGuideBanner> {

    private ImageLoader mImageLoader;

    public SimpleGuideBanner(Context context) {
        super(context);
        onCreateBanner();
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreateBanner();
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onCreateBanner();
    }

    protected void onCreateBanner() {
        setBarShowWhenLast(true);
        //不进行自动滚动
        setAutoScrollEnable(false);
    }

    private ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new GlideImageLoader();
        }
        return  mImageLoader;
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.benchmarkui_adapter_simple_guide, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        AppCompatButton tv_jump = inflate.findViewById(R.id.tv_jump);

        final Integer resId = mDatas.get(position);
        tv_jump.setVisibility(position == mDatas.size() - 1 ? VISIBLE : GONE);

        getImageLoader().displayImage(mContext, resId, iv);

        tv_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJumpClickListener != null)
                    onJumpClickListener.onJumpClick();
            }
        });

        return inflate;
    }

    private OnJumpClickListener onJumpClickListener;

    public interface OnJumpClickListener {
        void onJumpClick();
    }

    public void setOnJumpClickListener(OnJumpClickListener onJumpClickListener) {
        this.onJumpClickListener = onJumpClickListener;
    }

    public SimpleGuideBanner setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        return this;
    }
}

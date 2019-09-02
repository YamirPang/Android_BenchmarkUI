package com.zealp.benchmark_ui.widget.banner.widget.banner.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.config.UILog;
import com.zealp.benchmark_ui.widget.banner.widget.RoundRectLayout;
import com.zealp.benchmark_ui.widget.banner.widget.loopviewpager.FixedSpeedScroller;
import com.zealp.benchmark_ui.widget.banner.widget.loopviewpager.LoopViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * BaseCustomBanner:
 * Author: ZealP
 * Created On: 2019/6/26
 */
public abstract class BaseCustomBanner<E, T extends BaseCustomBanner<E, T>> extends RelativeLayout {
    /**
     * 单线程池定时任务
     */
    private ScheduledExecutorService mExecutorService;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 设备密度
     */
    protected DisplayMetrics mDisplayMetrics;
    /**
     * ViewPager
     */
    protected ViewPager mViewPager;
    /**
     * 数据源
     */
    protected List<E> mDatas = new ArrayList<>();
    /**
     * 当前position
     */
    protected int mCurrentPositon;
    /**
     * 上一个position
     */
    protected int mLastPositon;
    /**
     * 多久后开始滚动
     */
    private long mDelay;
    /**
     * 滚动间隔
     */
    private long mPeriod;
    /**
     * 是否自动滚动
     */
    private boolean mIsAutoScrollEnable;
    /**
     * 是否正在自动滚动中
     */
    private boolean mIsAutoScrolling;
    /**
     * 滚动速度
     */
    private int mScrollSpeed = 450;
    /**
     * 切换动画
     */
    private Class<? extends ViewPager.PageTransformer> mTransformerClass;

    /**
     * 显示器(小点)的最顶层父容器
     */
    private RelativeLayout mRlBottomBarParent;
    private int mItemWidth;
    private int mItemHeight;

    /**
     * 显示器和标题的直接父容器
     */
    private LinearLayout mLlBottomBar;
    /**
     * 最后一条item是否显示背景条
     */
    private boolean mIsBarShowWhenLast;

    /**
     * 显示器的的直接容器
     */
    private LinearLayout mLlIndicatorContainer;

    /**
     * 标题
     */
    private TextView mTvTitle;

    private TextView mTvSubTitle;

    /**
     * 数据是否发生变化
     */
    private boolean mIsDataChanged = false;
    /**
     * 当页面只有一条时，是否轮播
     */
    private boolean mIsOnePageLoop = true;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            scrollToNextItem(mCurrentPositon);
            return true;
        }
    });

    public BaseCustomBanner(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public BaseCustomBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public BaseCustomBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        mContext = context;
        mDisplayMetrics = context.getResources().getDisplayMetrics();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseBanner);
        float scale = ta.getFloat(R.styleable.BaseBanner_bb_scale, -1);

        boolean isLoopEnable = ta.getBoolean(R.styleable.BaseBanner_bb_isLoopEnable, true);
        mDelay = ta.getInt(R.styleable.BaseBanner_bb_delay, 5);
        mPeriod = ta.getInt(R.styleable.BaseBanner_bb_period, 5);
        mIsAutoScrollEnable = ta.getBoolean(R.styleable.BaseBanner_bb_isAutoScrollEnable, true);

        int barColor = ta.getColor(R.styleable.BaseBanner_bb_barColor, Color.TRANSPARENT);
        mIsBarShowWhenLast = ta.getBoolean(R.styleable.BaseBanner_bb_isBarShowWhenLast, true);
        int indicatorGravity = ta.getInt(R.styleable.BaseBanner_bb_indicatorGravity, Gravity.CENTER);
        float barPaddingLeft = ta.getDimension(R.styleable.BaseBanner_bb_barPaddingLeft, dp2px(10));
        float barPaddingTop = ta.getDimension(R.styleable.BaseBanner_bb_barPaddingTop, dp2px(indicatorGravity == Gravity.CENTER ? 6 : 2));
        float barPaddingRight = ta.getDimension(R.styleable.BaseBanner_bb_barPaddingRight, dp2px(10));
        float barPaddingBottom = ta.getDimension(R.styleable.BaseBanner_bb_barPaddingBottom, dp2px(indicatorGravity == Gravity.CENTER ? 6 : 2));
        int textColor = ta.getColor(R.styleable.BaseBanner_bb_textColor, Color.parseColor("#ffffff"));
        float textSize = ta.getDimension(R.styleable.BaseBanner_bb_textSize, sp2px(12.5f));
        boolean isTitleShow = ta.getBoolean(R.styleable.BaseBanner_bb_isTitleShow, true);
        boolean isIndicatorShow = ta.getBoolean(R.styleable.BaseBanner_bb_isIndicatorShow, true);
        ta.recycle();

        //get layout_height
        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");

        //create ViewPager
        mViewPager = isLoopEnable ? new LoopViewPager(context) : new ViewPager(context);
        mItemWidth = mDisplayMetrics.widthPixels;
        if (scale < 0) {//scale not set in xml
            if (height.equals(ViewGroup.LayoutParams.MATCH_PARENT + "")) {
                mItemHeight = LayoutParams.MATCH_PARENT;
            } else if (height.equals(ViewGroup.LayoutParams.WRAP_CONTENT + "")) {
                mItemHeight = LayoutParams.WRAP_CONTENT;
            } else {
                int[] systemAttrs = {android.R.attr.layout_height};
                TypedArray a = context.obtainStyledAttributes(attrs, systemAttrs);
                int h = a.getDimensionPixelSize(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                a.recycle();
                mItemHeight = h;
            }
        } else {
            if (scale > 1) {
                scale = 1;
            }
            mItemHeight = (int) (mItemWidth * scale);
        }

        LayoutParams lp = new LayoutParams(mItemWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(30, 30, 30, 30);

        LayoutParams viewPagerLayoutParams = new LayoutParams(mItemWidth, ViewGroup.LayoutParams.MATCH_PARENT);
//        viewPagerLayoutParams.setMargins(30, 30, 30, 30);
        mViewPager.setLayoutParams(viewPagerLayoutParams);
//        mViewPager.setPadding(dp2px(10), dp2px(5), dp2px(10),dp2px(5));
//        addView(mViewPager, lp);

        //top parent of indicators
        mRlBottomBarParent = new RoundRectLayout(context);
//        mRlBottomBarParent.setPadding(dp2px(10), dp2px(5), dp2px(10),dp2px(5));
//            mRlBottomBarParent.setBackgroundColor(Color.parseColor("#000000"));
//        mRlBottomBarParent.setBackgroundResource(R.drawable.benchmarkui_banner_relative);
        addView(mRlBottomBarParent, lp);

        View view = new View(context);
        view.setAlpha(0.5f);
        view.setBackgroundResource(R.drawable.benchmarkui_banner_relative);

        mRlBottomBarParent.addView(mViewPager, viewPagerLayoutParams);
        mRlBottomBarParent.addView(view, viewPagerLayoutParams);

        //container of indicators and title
        mLlBottomBar = new LinearLayout(context);
        LayoutParams lp2 = new LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mLlBottomBar.setOrientation(LinearLayout.VERTICAL);
        mRlBottomBarParent.addView(mLlBottomBar, lp2);

        mLlBottomBar.setBackgroundColor(barColor);
        mLlBottomBar.setPadding((int) barPaddingLeft, (int) barPaddingTop, (int) barPaddingRight, (int) barPaddingBottom);
        mLlBottomBar.setClipChildren(false);
        mLlBottomBar.setClipToPadding(false);

        //container of indicators
        mLlIndicatorContainer = new LinearLayout(context);
        mLlIndicatorContainer.setGravity(Gravity.CENTER);
        mLlIndicatorContainer.setHorizontalGravity(Gravity.CENTER);
        mLlIndicatorContainer.setVisibility(isIndicatorShow ? VISIBLE : INVISIBLE);
        mLlIndicatorContainer.setClipChildren(false);
        mLlIndicatorContainer.setClipToPadding(false);

        // title
        mTvTitle = new TextView(context);
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0F));
        mTvTitle.setSingleLine(true);
        mTvTitle.setTextColor(textColor);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize + 3);
        mTvTitle.setVisibility(isTitleShow ? VISIBLE : INVISIBLE);

        mTvSubTitle = new TextView(context);
        mTvSubTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0F));
        mTvSubTitle.setMaxLines(2);
        mTvSubTitle.setTextColor(textColor);
        mTvSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTvTitle.setVisibility(isTitleShow ? VISIBLE : INVISIBLE);

        if (indicatorGravity == Gravity.CENTER) {
//                mLlBottomBar.setGravity(Gravity.CENTER);
            mLlBottomBar.addView(mTvTitle);
            mLlBottomBar.addView(mTvSubTitle);
            mLlIndicatorContainer.setGravity(Gravity.CENTER);
            mLlIndicatorContainer.setPadding(0, 0, 0, dp2px(3));
            mLlBottomBar.addView(mLlIndicatorContainer);

            mTvTitle.setPadding(0, 0, 0, dp2px(5));
            mTvTitle.setEllipsize(TextUtils.TruncateAt.END);
            mTvTitle.setGravity(Gravity.LEFT);
            mTvTitle.getPaint().setFakeBoldText(true);

            mTvSubTitle.setPadding(0, 0, 0, dp2px(20));
            mTvSubTitle.setEllipsize(TextUtils.TruncateAt.END);
            mTvSubTitle.setGravity(Gravity.LEFT);
            mTvSubTitle.getPaint().setFakeBoldText(true);
        } else {
            if (indicatorGravity == Gravity.RIGHT) {
                mLlBottomBar.setGravity(Gravity.CENTER_VERTICAL);
                mLlBottomBar.addView(mTvTitle);
                mLlBottomBar.addView(mLlIndicatorContainer);

                mTvTitle.setPadding(0, 0, dp2px(7), 0);
                mTvTitle.setEllipsize(TextUtils.TruncateAt.END);
                mTvTitle.setGravity(Gravity.LEFT);
            } else if (indicatorGravity == Gravity.LEFT) {
                mLlBottomBar.setGravity(Gravity.CENTER_VERTICAL);
                mLlBottomBar.addView(mLlIndicatorContainer);
                mLlBottomBar.addView(mTvTitle);

                mTvTitle.setPadding(dp2px(7), 0, 0, 0);
                mTvTitle.setEllipsize(TextUtils.TruncateAt.END);
                mTvTitle.setGravity(Gravity.RIGHT);
            }
        }
    }

    /**
     * 创建ViewPager的Item布局
     */
    public abstract View onCreateItemView(int position);

    /**
     * 创建显示器
     */
    public abstract View onCreateIndicator();

    /**
     * 设置当前显示器的状态,选中或者未选中
     */
    public abstract void setCurrentIndicator(int position);

    /**
     * 覆写这个方法设置标题
     */
    public void onTitleSelect(TextView tv, TextView tv2, int position) {
    }

    /**
     * 设置数据源
     */
    public T setSource(List<E> list) {
        this.mDatas = list;
        mIsDataChanged = true;
        return (T) this;
    }

    public E getItem(int position) {
        return size() > 0 ? mDatas.get(position) : null;
    }

    public int size() {
        return mDatas != null ? mDatas.size() : 0;
    }


    /**
     * 滚动延时,默认5秒
     */
    public T setDelay(long delay) {
        this.mDelay = delay;
        return (T) this;
    }

    /**
     * 滚动间隔,默认5秒
     */
    public T setPeriod(long period) {
        this.mPeriod = period;
        return (T) this;
    }

    /**
     * 设置是否支持自动滚动,默认true.仅对LoopViewPager有效
     */
    public T setAutoScrollEnable(boolean isAutoScrollEnable) {
        this.mIsAutoScrollEnable = isAutoScrollEnable;
        return (T) this;
    }

    /**
     * 设置页面切换动画
     */
    public T setTransformerClass(Class<? extends ViewPager.PageTransformer> transformerClass) {
        this.mTransformerClass = transformerClass;
        return (T) this;
    }

    /**
     * 设置底部背景条颜色,默认透明
     */
    public T setBarColor(int barColor) {
        mLlBottomBar.setBackgroundColor(barColor);
        return (T) this;
    }

    /**
     * 设置最后一条item是否显示背景条,默认true
     */
    public T setBarShowWhenLast(boolean isBarShowWhenLast) {
        this.mIsBarShowWhenLast = isBarShowWhenLast;
        return (T) this;
    }

    /**
     * 设置底部背景条padding,单位dp
     */
    public T barPadding(float left, float top, float right, float bottom) {
        mLlBottomBar.setPadding(dp2px(left), dp2px(top), dp2px(right), dp2px(bottom));
        return (T) this;
    }

    /**
     * 设置标题文字颜色,默认"#ffffff"
     */
    public T setTextColor(int textColor) {
        mTvTitle.setTextColor(textColor);
        return (T) this;
    }

    /**
     * 设置标题文字大小,单位sp,默认14sp
     */
    public T setTextSize(float textSize) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return (T) this;
    }

    /**
     * 设置是否显示标题,默认true
     */
    public T setTitleShow(boolean isTitleShow) {
        mTvTitle.setVisibility(isTitleShow ? VISIBLE : INVISIBLE);
        return (T) this;
    }

    /**
     * 设置是否显示显示器,默认true
     */
    public T setIndicatorShow(boolean isIndicatorShow) {
        mLlIndicatorContainer.setVisibility(isIndicatorShow ? VISIBLE : INVISIBLE);
        return (T) this;
    }

    /**
     * 滚动到下一个item
     */
    private void scrollToNextItem(int position) {
        if (isValid()) {
            if (mIsDataChanged) {
                updateViewPager();
            }
            position++;
            mViewPager.setCurrentItem(position);
        }
    }

    /**
     * 更新ViewPager
     */
    private void updateViewPager() {
        if (isLoopViewPager()) {
            ((LoopViewPager) mViewPager).getPageAdapterWrapper().notifyDataSetChanged();
        } else {
            mViewPager.getAdapter().notifyDataSetChanged();
        }
    }

    /**
     * 设置viewpager
     */
    private void setViewPager() {
        InnerBannerAdapter mInnerAdapter = new InnerBannerAdapter();
        mViewPager.setAdapter(mInnerAdapter);
        mViewPager.setOffscreenPageLimit(mDatas.size());

        try {
            if (mTransformerClass != null) {
                mViewPager.setPageTransformer(true, mTransformerClass.newInstance());
                if (isLoopViewPager()) {
                    mScrollSpeed = 550;
                    setScrollSpeed();
                }
            } else {
                if (isLoopViewPager()) {
                    mScrollSpeed = 450;
                    setScrollSpeed();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mInternalPageListener != null) {
            mViewPager.removeOnPageChangeListener(mInternalPageListener);
        }
        mViewPager.addOnPageChangeListener(mInternalPageListener);
    }

    /**
     * 页面变换监听
     */
    private ViewPager.OnPageChangeListener mInternalPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPositon = position % mDatas.size();

            setCurrentIndicator(mCurrentPositon);
            onTitleSelect(mTvTitle, mTvSubTitle, mCurrentPositon);
            mLlBottomBar.setVisibility(mCurrentPositon == mDatas.size() - 1 && !mIsBarShowWhenLast ? GONE : VISIBLE);

            mLastPositon = mCurrentPositon;
            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mOnPageChangeListener != null) {
                mOnPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    /**
     * 开始滚动
     */
    public void startScroll() {
        if (mDatas == null) {
            throw new IllegalStateException("Data source is empty,you must setSource() before startScroll()");
        }

        if (mDatas.size() > 0 && mCurrentPositon > mDatas.size() - 1) {
            mCurrentPositon = 0;
        }

        onTitleSelect(mTvTitle, mTvSubTitle, mCurrentPositon);
        setViewPager();
        //create indicator
        View indicatorViews = onCreateIndicator();
        if (indicatorViews != null) {
            mLlIndicatorContainer.removeAllViews();
            mLlIndicatorContainer.addView(indicatorViews);
        }

        goOnScroll();
    }

    /**
     * 继续滚动(for LoopViewPager)
     */
    public void goOnScroll() {
        if (!isValid()) {
            return;
        }

        if (mIsAutoScrolling) {
            return;
        }
        if (isLoopViewPager() && mIsAutoScrollEnable) {
            pauseScroll();
            mExecutorService = Executors.newSingleThreadScheduledExecutor();
            mExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    mHandler.obtainMessage().sendToTarget();
                }
            }, mDelay, mPeriod, TimeUnit.SECONDS);
            mIsAutoScrolling = true;
            UILog.d(this.getClass().getSimpleName() + "--->goOnScroll()");
        } else {
            mIsAutoScrolling = false;
        }
    }

    /**
     * 停止滚动(for LoopViewPager)
     */
    public void pauseScroll() {
        stopScroll();
        UILog.d(this.getClass().getSimpleName() + "--->pauseScroll()");

        mIsAutoScrolling = false;
    }

    private void stopScroll() {
        if (mExecutorService != null) {
            mExecutorService.shutdown();
            mExecutorService = null;
        }
    }

    /**
     * 获取ViewPager对象
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                pauseScroll();
                break;
            case MotionEvent.ACTION_UP:
                goOnScroll();
                break;
            case MotionEvent.ACTION_CANCEL:
                goOnScroll();
                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    protected void onWindowVisibilityChanged(int visibility) {
//        super.onWindowVisibilityChanged(visibility);
//        if (mIsSmart) {
//            if (visibility != VISIBLE) {
//                pauseScroll();
//            } else {
//                goOnScroll();
//            }
//        }
//    }

    private class InnerBannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDatas != null ? mDatas.size() : 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View inflate = onCreateItemView(position);
            inflate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickL != null) {
                        mOnItemClickL.onItemClick(position);
                    }
                }
            });
            container.addView(inflate);

            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    /**
     * 设置滚动速率
     */
    private void setScrollSpeed() {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
            FixedSpeedScroller myScroller = new FixedSpeedScroller(mContext, interpolator, mScrollSpeed);
            mScroller.set(mViewPager, myScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int dp2px(float dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    private float sp2px(float sp) {
        final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    protected boolean isLoopViewPager() {
        return mViewPager instanceof LoopViewPager;
    }

    protected boolean isValid() {
        if (mViewPager == null) {
            UILog.e("ViewPager is not exist!");
            return false;
        }

        if (mDatas == null || mDatas.size() <= 0) {
            UILog.e("DataList must be not empty!");
            return false;
        }

        if (!mIsOnePageLoop && mDatas.size() == 1) {
            return false;
        }

        return true;
    }

    //listener
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public BaseCustomBanner addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
        return this;
    }

    private BaseCustomBanner.OnItemClickL mOnItemClickL;

    public BaseCustomBanner setOnItemClickL(BaseCustomBanner.OnItemClickL onItemClickL) {
        this.mOnItemClickL = onItemClickL;
        return this;
    }

    public interface OnItemClickL {
        void onItemClick(int position);
    }

    /**
     * 设置当页面只有一条时，是否轮播
     *
     * @param isOnePageLoop
     * @return
     */
    public BaseCustomBanner setIsOnePageLoop(boolean isOnePageLoop) {
        mIsOnePageLoop = isOnePageLoop;
        return this;
    }

    /**
     * 资源回收
     */
    public void recycle() {
        pauseScroll();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}

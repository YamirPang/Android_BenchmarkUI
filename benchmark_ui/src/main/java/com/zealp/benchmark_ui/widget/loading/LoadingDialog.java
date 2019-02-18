package com.zealp.benchmark_ui.widget.loading;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleRes;
import android.widget.TextView;
import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.common.BaseDialog;

/**
 * Author: ZealP
 * Created On: 2019/2/12
 */
public class LoadingDialog extends BaseDialog implements ILoadingCallback {

    private ARCLoadingView mLoadingView;

    private TextView mTvTipMessage;

    private LoadingCancelCallback mLoadingCancelCallback;

    public LoadingDialog(Context context) {
        super(context, R.layout.benchmarkui_loading_dialog);
        initView(getString(R.string.tip_loading_message));
    }

    public LoadingDialog(Context context, String tipMessage) {
        super(context, R.layout.benchmarkui_loading_dialog);
        initView(tipMessage);
    }

    public LoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId, R.layout.benchmarkui_loading_dialog);
        initView(getString(R.string.tip_loading_message));
    }

    public LoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId, R.layout.benchmarkui_loading_dialog);
        initView(tipMessage);
    }

    private void initView(String tipMessage) {
        mLoadingView = findViewById(R.id.arc_loading_view);
        mTvTipMessage = findViewById(R.id.tv_tip_message);

        updateMsg(tipMessage);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 更新提示信息
     *
     * @param msg
     * @return
     */
    @Override
    public void updateMsg(String msg) {
        if (mTvTipMessage != null) {
            mTvTipMessage.setText(msg);
        }
    }

    /**
     * 更新提示信息
     *
     * @param msgId string资源
     * @return
     */
    @Override
    public void updateMsg(int msgId) {
        updateMsg(getString(msgId));
    }

    /**
     * 设置loading的图标
     *
     * @param icon
     * @return
     */
    public LoadingDialog setLoadingIcon(Drawable icon) {
        if (mLoadingView != null) {
            mLoadingView.setLoadingIcon(icon);
        }
        return this;
    }

    /**
     * 设置loading的图标
     *
     * @param iconResId
     * @return
     */
    public LoadingDialog setLoadingIcon(int iconResId) {
        return setLoadingIcon(getDrawable(iconResId));
    }

    /**
     * 设置图标的缩小比例
     *
     * @param iconScale
     * @return
     */
    public LoadingDialog setIconScale(float iconScale) {
        if (mLoadingView != null) {
            mLoadingView.setIconScale(iconScale);
        }
        return this;
    }

    /**
     * 设置loading旋转的速度
     *
     * @param speed
     * @return
     */
    public LoadingDialog setLoadingSpeed(int speed) {
        if (mLoadingView != null) {
            mLoadingView.setSpeedOfDegree(speed);
        }
        return this;
    }

    @Override
    public void show() {
        super.show();
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    @Override
    public void dismiss() {
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
        super.dismiss();
    }

    @Override
    public void recycle() {
        if (mLoadingView != null) {
            mLoadingView.recycle();
        }
    }

    @Override
    public boolean isLoading() {
        return isShowing();
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (mLoadingCancelCallback != null) {
                        mLoadingCancelCallback.onLoadingCancel();
                    }
                }
            });
        }
    }

    @Override
    public void setLoadingCancelListener(LoadingCancelCallback listener) {
        mLoadingCancelCallback = listener;
    }
}

package com.zealp.benchmark_ui.widget.loading;

import android.content.Context;
import android.content.DialogInterface;
import com.zealp.benchmark_ui.R;
import com.zealp.benchmark_ui.common.BaseDialog;
import android.support.annotation.StyleRes;

/**
 * Author: ZealP
 * Created On: 2019/2/12
 */
public class ChrysanthemumLoadingDialog extends BaseDialog implements IChrysanthemumLoadingCallback {

    private ChrysanthemumLoadingView mLoadingView;

    private LoadingCancelCallback mLoadingCancelCallback;

    public ChrysanthemumLoadingDialog(Context context) {
        super(context, R.layout.benchmarkui_chrysanthemum_loading_dialog);
        initView();
    }

    public ChrysanthemumLoadingDialog(Context context, String tipMessage) {
        super(context, R.layout.benchmarkui_chrysanthemum_loading_dialog);
        initView();
    }

    public ChrysanthemumLoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId, R.layout.benchmarkui_chrysanthemum_loading_dialog);
        initView();
    }

    public ChrysanthemumLoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId, R.layout.benchmarkui_chrysanthemum_loading_dialog);
        initView();
    }

    private void initView() {
        mLoadingView = findViewById(R.id.arc_loading_view);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
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
        if(mLoadingView != null)
            mLoadingView.onDetachedFromWindow();
    }

    @Override
    public boolean isLoading() {
        return isShowing();
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            setOnCancelListener(new OnCancelListener() {
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

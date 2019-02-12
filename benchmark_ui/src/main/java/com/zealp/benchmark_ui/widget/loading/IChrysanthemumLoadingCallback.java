package com.zealp.benchmark_ui.widget.loading;

/**
 * Author: ZealP
 * Created On: 2019/2/12
 */
public interface IChrysanthemumLoadingCallback {

    /**
     * 显示加载
     */
    void show();

    /**
     * 隐藏加载
     */
    void dismiss();

    /**
     * 资源释放
     */
    void recycle();

    /**
     * 是否在加载
     *
     * @return
     */
    boolean isLoading();

    /**
     * 设置是否可取消
     *
     * @param cancelable
     */
    void setCancelable(boolean cancelable);

    /**
     * 设置取消的回掉监听
     *
     * @param listener
     */
    void setLoadingCancelListener(LoadingCancelCallback listener);
}

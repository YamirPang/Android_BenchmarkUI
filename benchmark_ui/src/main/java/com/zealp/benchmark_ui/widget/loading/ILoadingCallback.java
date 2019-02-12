package com.zealp.benchmark_ui.widget.loading;

/**
 * Author: Dev_@ZealP
 * Created On: 2019/2/12
 * Email: zealpeng@163.com
 * Description:
 */
public interface ILoadingCallback {

    /**
     * 更新提示信息
     *
     * @param msg
     * @return
     */
    void updateMsg(String msg);

    /**
     * 更新提示信息
     *
     * @param msgId
     * @return
     */
    void updateMsg(int msgId);

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

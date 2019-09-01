package com.zealp.benchmark_ui.common;

/**
 * 集合条目监听
 */
public interface OnListItemListener<T> {

    /**
     * 点击
     * @param position
     * @param model
     * @param tag
     */
    void onItemClick(int position, T model, int tag);

    /**
     * 长按
     * @param position
     * @param model
     * @param tag
     */
    void onItemLongClick(int position, T model, int tag);
}

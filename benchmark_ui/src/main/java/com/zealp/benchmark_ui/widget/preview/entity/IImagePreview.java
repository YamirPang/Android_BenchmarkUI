package com.zealp.benchmark_ui.widget.preview.entity;

import android.graphics.Rect;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Author: ZealP
 * Created On: 2019/2/25
 */
public interface IImagePreview extends Parcelable {

    /**
     * @return 图片地址
     */
    String getUrl();

    /**
     * @return 记录坐标
     */
    Rect getBounds();


    /**
     * @return 获取视频链接
     */
    @Nullable
    String getVideoUrl();
}

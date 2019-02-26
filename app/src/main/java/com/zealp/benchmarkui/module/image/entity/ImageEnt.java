package com.zealp.benchmarkui.module.image.entity;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.zealp.benchmark_ui.widget.preview.entity.IImagePreview;

/**
 * Author: ZealP
 * Created On: 2019/2/26
 */
public class ImageEnt implements IImagePreview {

    private String mUrl;  //图片地址
    private Rect mBounds; // 记录坐标
    private String mVideoUrl;

    public ImageEnt(String url) {
        mUrl = url;
    }

    public ImageEnt(String videoUrl, String url) {
        mUrl = url;
        mVideoUrl = videoUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmBounds(Rect mBounds) {
        this.mBounds = mBounds;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public Rect getBounds() {
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return mVideoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
        dest.writeParcelable(mBounds, flags);
        dest.writeString(mVideoUrl);
    }

    protected ImageEnt(Parcel in) {
        mUrl = in.readString();
        mBounds = in.readParcelable(Rect.class.getClassLoader());
        mVideoUrl = in.readString();
    }

    public static final Parcelable.Creator<ImageEnt> CREATOR = new Parcelable.Creator<ImageEnt>() {
        @Override
        public ImageEnt createFromParcel(Parcel source) {
            return new ImageEnt(source);
        }

        @Override
        public ImageEnt[] newArray(int size) {
            return new ImageEnt[size];
        }
    };
}

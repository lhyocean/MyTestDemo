package com.beijing.ocean.multmediademo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2016/11/12.
 */
public class GoodBean implements Parcelable {
    private String goodImg;
    private String goodDes;

    protected GoodBean(Parcel in) {
        goodImg = in.readString();
        goodDes = in.readString();
    }

    public GoodBean() {
    }

    public static final Creator<GoodBean> CREATOR = new Creator<GoodBean>() {
        @Override
        public GoodBean createFromParcel(Parcel in) {
            return new GoodBean(in);
        }

        @Override
        public GoodBean[] newArray(int size) {
            return new GoodBean[size];
        }
    };

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getGoodDes() {
        return goodDes;
    }

    public void setGoodDes(String goodDes) {
        this.goodDes = goodDes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(goodImg);
        dest.writeString(goodDes);
    }
}

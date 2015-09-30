package com.example.like.gallerydemo;

/**
 * Created by like on 2015/9/29.
 */
public class Bean {
    private int imgResId;
    private int strResId;

    public Bean(int imgResId, int strResId) {
        this.imgResId = imgResId;
        this.strResId = strResId;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getStrResId() {
        return strResId;
    }

    public void setStrResId(int strResId) {
        this.strResId = strResId;
    }
}

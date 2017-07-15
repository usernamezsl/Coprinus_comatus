package com.sunnada.coprinus_comatus.mvp.model.entity;

/**
 * 作者: 张少林 on 2017/6/11 0011.
 * 邮箱:1083065285@qq.com
 */

public class FunctionEntity {
    private String title;
    private int imgId;

    public FunctionEntity(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

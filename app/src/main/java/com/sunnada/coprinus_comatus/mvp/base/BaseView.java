package com.sunnada.coprinus_comatus.mvp.base;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public interface BaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 跳转activity
     */
    void launchActivity();
    /**
     * 杀死自己
     */
    void killMyself();

}

package com.sunnada.coprinus_comatus.mvp.model.entity;

import com.sunnada.coprinus_comatus.mvp.model.api.Api;

/**
 * 作者: 张少林 on 2017/6/10 0010.
 * 邮箱:1083065285@qq.com
 */

public class Result<T> {
    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == Api.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }
}

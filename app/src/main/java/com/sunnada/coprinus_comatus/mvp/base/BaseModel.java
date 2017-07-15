package com.sunnada.coprinus_comatus.mvp.base;

import com.sunnada.coprinus_comatus.http.BaseServiceManager;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public class BaseModel<S extends BaseServiceManager> implements IModel {
    protected S mServiceManager;//服务管理类,用于网络请求

    public BaseModel(S serviceManager) {
        mServiceManager = serviceManager;
    }

    @Override
    public void onDestory() {
        if (mServiceManager != null) {
            mServiceManager = null;
        }
    }
}

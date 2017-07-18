package com.sunnada.coprinus_comatus.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.base.BaseModel;
import com.sunnada.coprinus_comatus.mvp.contract.SplashContract;
import com.sunnada.coprinus_comatus.mvp.model.api.service.ServiceManager;

import javax.inject.Inject;

/**
 * 作者: 张少林 on 2017/7/18 0018.
 * 邮箱:1083065285@qq.com
 */
@ActivityScope
public class SplashModel extends BaseModel<ServiceManager>  implements SplashContract.Model{

    private Gson mGson;
    private Application mApplication;

    @Inject
    public SplashModel(ServiceManager serviceManager, Gson gson, Application application) {
        super(serviceManager);
        mGson = gson;
        mApplication = application;
    }

    @Override
    public void onDestory() {
        super.onDestory();
        this.mGson = null;
        this.mApplication = null;
    }
}

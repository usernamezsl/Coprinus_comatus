package com.sunnada.coprinus_comatus.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.base.BaseModel;
import com.sunnada.coprinus_comatus.mvp.contract.LoginContract;
import com.sunnada.coprinus_comatus.mvp.model.api.service.ServiceManager;
import com.sunnada.coprinus_comatus.mvp.model.entity.Result;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@ActivityScope
public class LoginModel extends BaseModel<ServiceManager> implements LoginContract.Model{
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(ServiceManager serviceManager, Gson gson, Application application) {
        super(serviceManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestory() {
        super.onDestory();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Result<User>> login(String username, String password) {
        return mServiceManager.getCommonService()
                .login(username,password);

    }
}

package com.sunnada.coprinus_comatus.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.sunnada.coprinus_comatus.mvp.base.BaseModel;
import com.sunnada.coprinus_comatus.mvp.contract.ResgisterContract;
import com.sunnada.coprinus_comatus.mvp.model.api.service.ServiceManager;
import com.sunnada.coprinus_comatus.mvp.model.entity.Result;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public class ResgisterModel extends BaseModel<ServiceManager> implements ResgisterContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ResgisterModel(ServiceManager serviceManager, Gson gson, Application application) {
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

    @Override
    public Observable<Result<User>> resgister(String username, String password) {
        Observable<Result<User>> resultObservable = mServiceManager.getCommonService()
                .resgister(username, password);
        return resultObservable;
    }

    @Override
    public Observable<Result<User>> login(String username, String password) {
        return mServiceManager.getCommonService().login(username, password);
    }

}

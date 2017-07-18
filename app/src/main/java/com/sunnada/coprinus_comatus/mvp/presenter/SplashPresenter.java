package com.sunnada.coprinus_comatus.mvp.presenter;

import android.app.Application;

import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.base.BasePresenter;
import com.sunnada.coprinus_comatus.mvp.contract.SplashContract;

import javax.inject.Inject;

/**
 * 作者: 张少林 on 2017/7/18 0018.
 * 邮箱:1083065285@qq.com
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private Application mApplication;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView, Application application) {
        super(model, rootView);
        mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApplication = null;
    }

}

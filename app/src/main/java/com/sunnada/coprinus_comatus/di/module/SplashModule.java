package com.sunnada.coprinus_comatus.di.module;

import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.contract.SplashContract;
import com.sunnada.coprinus_comatus.mvp.model.SplashModel;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: 张少林 on 2017/7/18 0018.
 * 邮箱:1083065285@qq.com
 */
@Module
public class SplashModule {
    private SplashContract.View mView;

    /**
     * 构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SplashModule(SplashContract.View view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    SplashContract.View provideSplashView() {
        return this.mView;
    }

    @Provides
    @ActivityScope
    SplashContract.Model provideSplashModel(SplashModel model) {
        return model;
    }
}

package com.sunnada.coprinus_comatus.di.module;

import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.contract.ResgisterContract;
import com.sunnada.coprinus_comatus.mvp.model.ResgisterModel;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Module
public class ResgisterModule {

    private ResgisterContract.View mView;

    public ResgisterModule(ResgisterContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    ResgisterContract.View provideResgisterView() {
        return this.mView;
    }

    @ActivityScope
    @Provides
    ResgisterContract.Model provideResgisterModel(ResgisterModel model) {
        return model;
    }
}

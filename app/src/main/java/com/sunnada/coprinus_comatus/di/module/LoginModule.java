package com.sunnada.coprinus_comatus.di.module;

import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.contract.LoginContract;
import com.sunnada.coprinus_comatus.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Module
public class LoginModule {
    private LoginContract.View mView;

    /**
     * 构建LoginModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginModule(LoginContract.View view) {
        this.mView = view;
    }

    @Provides
    @ActivityScope
    LoginContract.View provideLoginView() {
        return this.mView;
    }

    @Provides
    @ActivityScope
    LoginContract.Model provideLoginModel(LoginModel model) {
        return model;
    }
}

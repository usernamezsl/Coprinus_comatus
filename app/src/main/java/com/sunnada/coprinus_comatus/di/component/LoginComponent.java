package com.sunnada.coprinus_comatus.di.component;

import com.sunnada.coprinus_comatus.base.AppComponent;
import com.sunnada.coprinus_comatus.di.module.LoginModule;
import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.ui.activity.LoginActivity;

import dagger.Component;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}

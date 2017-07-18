package com.sunnada.coprinus_comatus.di.component;

import com.sunnada.coprinus_comatus.base.AppComponent;
import com.sunnada.coprinus_comatus.di.module.SplashModule;
import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.ui.activity.SplashActivity;

import dagger.Component;

/**
 * 作者: 张少林 on 2017/7/18 0018.
 * 邮箱:1083065285@qq.com
 */

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}

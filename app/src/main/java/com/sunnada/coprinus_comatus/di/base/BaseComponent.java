package com.sunnada.coprinus_comatus.di.base;

import com.sunnada.coprinus_comatus.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Singleton
@Component(modules = {AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication baseApplication);
}

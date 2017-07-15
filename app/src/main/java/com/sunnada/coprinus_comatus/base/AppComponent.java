package com.sunnada.coprinus_comatus.base;

import android.app.Application;

import com.google.gson.Gson;
import com.sunnada.coprinus_comatus.di.base.AppModule;
import com.sunnada.coprinus_comatus.di.base.ClientModule;
import com.sunnada.coprinus_comatus.di.base.GlobeConfigModule;
import com.sunnada.coprinus_comatus.di.module.ServiceModule;
import com.sunnada.coprinus_comatus.mvp.model.api.service.ServiceManager;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class,
        ServiceModule.class, GlobeConfigModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi
    ServiceManager serviceManager();


    OkHttpClient okHttpClient();

    //gson
    Gson gson();

    //用于管理所有activity
    AppManager appManager();
}

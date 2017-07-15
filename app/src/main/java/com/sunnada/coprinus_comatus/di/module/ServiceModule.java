package com.sunnada.coprinus_comatus.di.module;

import com.sunnada.coprinus_comatus.mvp.model.api.service.CommonService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Module
public class ServiceModule {

    @Provides
    @Singleton
    CommonService provideCommonService(Retrofit retrofit){
        return retrofit.create(CommonService.class);
    }

}

package com.sunnada.coprinus_comatus.di.base;

import com.sunnada.coprinus_comatus.base.AppManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Module
public class ClientModule {
    private static final int TIME_OUT = 10;

    private AppManager mAppManager;


    public ClientModule(AppManager appManager) {
        this.mAppManager = appManager;
    }

    /**
     * @param builder
     * @param client
     * @param httpUrl
     * @return 提供retrofit
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl) {
        return builder
                .baseUrl(httpUrl)//域名
                .client(client)//设置okhttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .build();
    }

    /**
     * 提供OkhttpClient
     *
     * @param builder
     * @return
     */
    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        OkHttpClient.Builder builder1 = builder
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);
        return builder1.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    /**
     * 提供管理所有activity的管理类
     *
     * @return
     */
    @Singleton
    @Provides
    AppManager provideAppManager() {
        return mAppManager;
    }
}

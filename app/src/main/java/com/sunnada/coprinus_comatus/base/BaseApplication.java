package com.sunnada.coprinus_comatus.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.sunnada.coprinus_comatus.di.base.AppModule;
import com.sunnada.coprinus_comatus.di.base.ClientModule;
import com.sunnada.coprinus_comatus.di.base.DaggerBaseComponent;
import com.sunnada.coprinus_comatus.di.base.GlobeConfigModule;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public abstract class BaseApplication extends Application {
    private static BaseApplication mApplication;
    private ClientModule mClientModule;
    private AppModule mAppModule;
    private GlobeConfigModule mGlobeConfigModule;
    private static final String TAG = "BaseApplication";

    @Inject
    protected Gson mGson;
    @Inject
    protected AppManager mAppManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mAppModule = new AppModule(this);//提供application
        DaggerBaseComponent
                .builder()
                .appModule(mAppModule)
                .build()
                .inject(this);
        this.mClientModule = new ClientModule(mAppManager);
        this.mGlobeConfigModule = checkNotNull(getGlobeConfigModule(), "lobeConfigModule is required");
        Utils.init(this);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mClientModule != null)
            this.mClientModule = null;
        if (mAppModule != null)
            this.mAppModule = null;
        if (mApplication != null)
            this.mApplication = null;
    }

    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     *
     * @return
     */
    protected abstract GlobeConfigModule getGlobeConfigModule();

    /**
     * 返回上下文
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return mApplication;
    }

    public AppManager getAppManager() {
        return mAppManager;
    }

}

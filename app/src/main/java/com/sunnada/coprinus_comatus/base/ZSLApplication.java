package com.sunnada.coprinus_comatus.base;

import com.facebook.stetho.Stetho;
import com.sunnada.coprinus_comatus.di.base.GlobeConfigModule;
import com.sunnada.coprinus_comatus.di.module.ServiceModule;
import com.sunnada.coprinus_comatus.mvp.model.api.Api;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public class ZSLApplication extends BaseApplication {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .globeConfigModule(getGlobeConfigModule())//全局配置
                .serviceModule(new ServiceModule())
                .build();
        //运行App, 打开Chrome输入chrome://inspect/#devices（别忘了用数据线把手机和电脑连起来哦）
        Stetho.initializeWithDefaults(this);//初始化stetho利用chrome查看sqlite
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppComponent != null)
            this.mAppComponent = null;

    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     *
     * @return
     */
    @Override
    protected GlobeConfigModule getGlobeConfigModule() {
        return GlobeConfigModule
                .buidler()
                .baseurl(Api.APP_DOMAIN)
                .build();
    }
}

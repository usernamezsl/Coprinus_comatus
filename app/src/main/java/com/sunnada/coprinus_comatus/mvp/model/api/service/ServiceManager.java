package com.sunnada.coprinus_comatus.mvp.model.api.service;

import com.sunnada.coprinus_comatus.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@Singleton
public class ServiceManager implements BaseServiceManager {
    private CommonService mCommonService;

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     */
    @Inject
    public ServiceManager(CommonService commonService) {
        this.mCommonService = commonService;
    }

    public CommonService getCommonService() {
        return mCommonService;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestory() {

    }
}

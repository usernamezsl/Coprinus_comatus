package com.sunnada.coprinus_comatus.base;

import com.sunnada.coprinus_comatus.mvp.base.Presenter;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public abstract class ZSLActivity<P extends Presenter> extends BaseActivity<P> {
    protected ZSLApplication mZSLApplication;

    @Override
    protected void ComponentInject() {
        mZSLApplication = (ZSLApplication) getApplication();
        setupActivityComponent(mZSLApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mZSLApplication = null;
    }
}

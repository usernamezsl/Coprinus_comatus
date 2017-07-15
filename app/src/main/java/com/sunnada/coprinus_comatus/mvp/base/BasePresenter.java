package com.sunnada.coprinus_comatus.mvp.base;


import org.simple.eventbus.EventBus;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public class BasePresenter<M extends IModel, V extends BaseView> implements Presenter {

    private static final String TAG = "BasePresenter";

    protected M mModel;
    protected V mRootView;

    public BasePresenter(M model, V rootView) {
        mModel = model;
        mRootView = rootView;
        onStart();
    }

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册eventbus
    }

    @Override
    public void onDestroy() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        if (mModel != null) {
            mModel.onDestory();
            this.mModel = null;
        }
        this.mRootView = null;
    }


    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return true;
    }
}

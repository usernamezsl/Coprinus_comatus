package com.sunnada.coprinus_comatus.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.sunnada.coprinus_comatus.di.scope.ActivityScope;
import com.sunnada.coprinus_comatus.mvp.base.BasePresenter;
import com.sunnada.coprinus_comatus.mvp.contract.LoginContract;
import com.sunnada.coprinus_comatus.mvp.model.api.HttpObserver;
import com.sunnada.coprinus_comatus.mvp.model.api.HttpResultFunc;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView, Application application) {
        super(model, rootView);
        mApplication = application;
    }

    /**
     * 登录处理
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        if (!TextUtils.isEmpty(username.trim()) && !TextUtils.isEmpty(password.trim())) {
            loginForResult(username, password);
        } else {
            ToastUtils.showLong("账号密码不能为空！");
        }
    }

    /**
     * 登录 网络处理
     *
     * @param username
     * @param password
     */
    private void loginForResult(String username, String password) {
        mModel.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new HttpResultFunc<User>())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<User>() {
                    @Override
                    protected void _onSuccess(User user) {
                        mRootView.saveUserInfo(user);
                        mRootView.hideLoading();
                        mRootView.launchActivity();
                    }

                    @Override
                    protected void _onError() {
                        mRootView.hideLoading();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApplication = null;
    }

}

package com.sunnada.coprinus_comatus.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.sunnada.coprinus_comatus.mvp.base.BasePresenter;
import com.sunnada.coprinus_comatus.mvp.contract.ResgisterContract;
import com.sunnada.coprinus_comatus.mvp.model.api.HttpObserver;
import com.sunnada.coprinus_comatus.mvp.model.api.HttpResultFunc;
import com.sunnada.coprinus_comatus.mvp.model.entity.Result;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public class ResgisterPresenter extends BasePresenter<ResgisterContract.Model, ResgisterContract.View> {
    private Application mApplication;

    @Inject
    public ResgisterPresenter(ResgisterContract.Model model, ResgisterContract.View rootView, Application application) {
        super(model, rootView);
        mApplication = application;
    }

    /**
     * 注册条件判断
     *
     * @param username
     * @param password
     * @param passwordAgain
     */
    public void resgister(String username, String password, String passwordAgain) {
        if (!TextUtils.isEmpty(username.trim()) &&
                !TextUtils.isEmpty(password.trim()) &&
                !TextUtils.isEmpty(passwordAgain.trim())) {
            if (password.equals(passwordAgain)) {
                resgisterForResult(username, password);
            } else {
                ToastUtils.showLong("请确保密码一致~");
            }
        } else {
            ToastUtils.showLong("请填写完整信息");
        }
    }

    /**
     * 注册 网络处理，支持注册成功 自动登录
     *
     * @param username
     * @param password
     */
    private void resgisterForResult(final String username, final String password) {
        mModel.resgister(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new HttpResultFunc<User>())
                .observeOn(Schedulers.io())
                .flatMap(new Function<User, ObservableSource<Result<User>>>() {
                    @Override
                    public ObservableSource<Result<User>> apply(@NonNull User user) throws Exception {
                        return mModel.login(user.getUsername(), user.getPassword());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .flatMap(new HttpResultFunc<User>())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<User>() {
                    @Override
                    protected void _onSuccess(User user) {
                        //成功回调，处理结果
                        mRootView.hideLoading();
                        mRootView.launchActivity();
                    }

                    @Override
                    protected void _onError() {
                        //失败，回调，处理结果
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

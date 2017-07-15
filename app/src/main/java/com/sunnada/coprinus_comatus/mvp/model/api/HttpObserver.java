package com.sunnada.coprinus_comatus.mvp.model.api;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 张少林 on 2017/6/11 0011.
 * 邮箱:1083065285@qq.com
 */

public abstract class HttpObserver<R> implements Observer<R> {
    private static final String TAG = "HttpObserver";
    private Disposable mDisposable;

    /**
     * 建立链接的时候调用并生成Disposable对象
     *
     * @param d 链接状态对象
     */
    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
//        getDisposable(d);
    }

    /**
     * 请求结果回调回来的时候调用,会调用多次
     *
     * @param r 没网提示
     */
    @Override
    public void onNext(R r) {
        if (!NetworkUtils.isConnected()) {
            if (mDisposable != null && !mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
//            ToastUtils.showLong("请检查网络连接后重试!");
        }
        _onSuccess(r);
    }

    protected abstract void _onSuccess(R r);

    /**
     * 出现异常的时候会走这里,我们统一放在 _onError();处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        _onError();

        if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            onNetworkException(e);
        } else {
            onUnknownException(e);
        }
    }

    /**
     * 请求结束之后的回调,无论成功还是失败,此处一般无逻辑代码,经常用来写ProgressBar的dismiss
     */
    protected abstract void _onError();
    /**
     * 向子类暴露 Disposable
     */
//    public abstract void getDisposable(Disposable disposable);

    /**
     * 不管成功与失败,这里都会走一次,所以加onFinished();方法
     */
    @Override
    public void onComplete() {
        _onError();
    }
    private void onNetworkException(Throwable e) {
        e.printStackTrace();
        ToastUtils.showLong("服务器连接异常！");
    }

    private void onUnknownException(Throwable e) {
        e.printStackTrace();
    }
}

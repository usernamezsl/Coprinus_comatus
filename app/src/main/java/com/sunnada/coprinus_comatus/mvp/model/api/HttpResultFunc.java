package com.sunnada.coprinus_comatus.mvp.model.api;

import com.blankj.utilcode.util.ToastUtils;
import com.sunnada.coprinus_comatus.mvp.model.entity.Result;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 作者: 张少林 on 2017/6/11 0011.
 * 邮箱:1083065285@qq.com
 */

public class HttpResultFunc<T> implements Function<Result<T>, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Result<T> tResult) throws Exception {
        if (!tResult.isSuccess()) {
            if (tResult.getMsg() != null) {
                switch (tResult.getCode()) {
                    case Api.USER_EXIT_CODE:
                        ToastUtils.showLong("该用户已存在！");
                        throw new RuntimeException("该用户已存在!");
                    case Api.USER_NOT_EXIT_CODE:
                        ToastUtils.showLong("用户不存在，请先注册！");
                        break;
                    case Api.USER_PASSWORD_ERROR_CODE:
                        ToastUtils.showLong("密码错误，请重新输入");
                    default:
                        break;

                }
            }
        }
        return Observable.just(tResult.getData());
    }
}

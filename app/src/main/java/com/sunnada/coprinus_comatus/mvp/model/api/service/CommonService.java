package com.sunnada.coprinus_comatus.mvp.model.api.service;

import com.sunnada.coprinus_comatus.mvp.model.entity.Result;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者: 张少林 on 2017/6/9 0010.
 * 邮箱:1083065285@qq.com
 * 存放通用的一些API
 */

public interface CommonService {

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @POST("users/register")
    Observable<Result<User>> resgister(@Query("username") String username, @Query("password") String password);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("users/login")
    Observable<Result<User>> login(@Query("username") String username, @Query("password") String password);

}

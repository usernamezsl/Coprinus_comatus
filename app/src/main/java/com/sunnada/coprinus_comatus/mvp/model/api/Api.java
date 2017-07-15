package com.sunnada.coprinus_comatus.mvp.model.api;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public interface Api {
    //    String APP_DOMAIN = "https://api.github.com";
    String APP_DOMAIN = "http://192.168.23.1:8888/";//真机测试，局域网ip
//    String APP_DOMAIN = "http://172.16.151.2:8080/";//夜神模拟器访问ip
    String RequestSuccess = "0";
    String message = "成功";
    public static final int SUCCESS_CODE = 0;
    public static final int USER_EXIT_CODE = 100;
    public static final int USER_NOT_EXIT_CODE = 200;
    public static final int USER_PASSWORD_ERROR_CODE = 300;
}

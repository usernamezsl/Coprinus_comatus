package com.sunnada.coprinus_comatus.mvp.contract;

import com.sunnada.coprinus_comatus.mvp.base.BaseView;
import com.sunnada.coprinus_comatus.mvp.base.IModel;
import com.sunnada.coprinus_comatus.mvp.model.entity.Result;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;

import io.reactivex.Observable;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 */

public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        /**
         * 设置用户信息
         *
         */
        void setUserInfo();

        /**
         * 保存用户信息
         *
         * @param user
         */
        void saveUserInfo(User user);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        /**
         * 登录
         *
         * @param username
         * @param password
         * @return
         */
        Observable<Result<User>> login(String username, String password);
    }
}

package com.sunnada.coprinus_comatus.di.base;

import android.text.TextUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

/**
 * 作者: 张少林 on 2017/6/9 0009.
 * 邮箱:1083065285@qq.com
 * 建造者模式  添加全局配置信息，可以后期拓展属性
 */
@Module
public class GlobeConfigModule {
    private HttpUrl mHttpUrl;

    public GlobeConfigModule(Buidler buidler) {
        this.mHttpUrl = buidler.apiUrl;
    }

    public static Buidler buidler() {
        return new Buidler();
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mHttpUrl;
    }

    public static final class Buidler {
        private HttpUrl apiUrl = HttpUrl.parse("http://192.168.23.1:8080/");

        public Buidler() {
        }

        public Buidler baseurl(String baseurl) {//基础url
            if (TextUtils.isEmpty(baseurl)) {
                throw new IllegalArgumentException("baseurl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public GlobeConfigModule build() {
            if (apiUrl == null) {
                try {
                    throw new Exception("baseurl is required");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new GlobeConfigModule(this);
        }
    }

}

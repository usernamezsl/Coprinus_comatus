package com.sunnada.coprinus_comatus.util.imageload;

import android.content.Context;

/**
 * 作者: 张少林 on 2017/7/18 0018.
 * 邮箱:1083065285@qq.com
 */

public interface IImageLoaderstrategy {
    //展示图片
    void showImage(ImageLoaderOptions options);
    //清楚缓存
    void cleanMemory(Context context);
    //全局初始化
    void init(Context context);
}

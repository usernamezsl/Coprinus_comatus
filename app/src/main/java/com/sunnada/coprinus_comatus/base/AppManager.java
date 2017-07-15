package com.sunnada.coprinus_comatus.base;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * 作者: 张少林 on 2017/6/11 0011.
 * 邮箱:1083065285@qq.com
 */

public class AppManager {

    public static final String APPMANAGER_MESSAGE = "appmanager_message";
    public static final int START_ACTIVITY = 0;
    public static final int SHOW_SNACKBAR = 1;
    public static final int KILL_ALL = 2;
    public static final int APP_EXIT = 3;

    //管理所有activity
    public List<AppCompatActivity> mActivityList;
    //当前在前台的activity
    private AppCompatActivity mCurrentActivity;
    private Application mApplication;

    @Inject
    public AppManager(Application application) {
        this.mApplication = application;
        EventBus.getDefault().register(this);
    }
    /**
     * 通过eventbus post事件,远程遥控执行对应方法
     */
    @Subscriber(tag = APPMANAGER_MESSAGE, mode = ThreadMode.MAIN)
    public void onReceive(Message message) {
        switch (message.what) {
            case START_ACTIVITY:
                if (message.obj == null)
                    break;
//                dispatchStart(message);
                break;
            case SHOW_SNACKBAR:
                if (message.obj == null)
                    break;
//                showSnackbar((String) message.obj, message.arg1 == 0 ? false : true);
                break;
            case KILL_ALL:
                killAll();
                break;
            case APP_EXIT:
                AppExit();
                break;
        }
    }

//    private void dispatchStart(Message message) {
//        if (message.obj instanceof Intent)
//            startActivity((Intent) message.obj);
//        else if (message.obj instanceof Class)
//            startActivity((Class) message.obj);
//        return;
//    }

    /**
     * 让在前台的activity,打开下一个activity
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (getCurrentActivity() == null) {
            //如果没有前台的activity就使用new_task模式启动activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getCurrentActivity().startActivity(intent);
    }
    /**
     * 获得当前在前台的activity
     *
     * @return
     */
    public AppCompatActivity getCurrentActivity() {
        return mCurrentActivity;
    }
    /**
     * 返回一个存储所有未销毁的activity的集合
     *
     * @return
     */
    public List<AppCompatActivity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }
    /**
     * 添加Activity到集合
     */
    public void addActivity(AppCompatActivity activity) {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        synchronized (AppManager.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
            }
        }
    }
    /**
     * 删除集合里的指定activity
     *
     * @param activity
     */
    public void removeActivity(AppCompatActivity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }
    /**
     * 删除集合里的指定位置的activity
     *
     * @param location
     */
    public AppCompatActivity removeActivity(int location) {
        if (mActivityList == null) {

            return null;
        }
        synchronized (AppManager.class) {
            if (location > 0 && location < mActivityList.size()) {
                return mActivityList.remove(location);
            }
        }
        return null;
    }
    /**
     * 关闭指定activity
     *
     * @param activityClass
     */
    public void killActivity(Class<?> activityClass) {
        if (mActivityList == null) {

            return;
        }
        for (AppCompatActivity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                activity.finish();
            }
        }
    }
    /**
     * 指定的activity实例是否存活
     *
     * @param activity
     * @return
     */
    public boolean activityInstanceIsLive(AppCompatActivity activity) {
        if (mActivityList == null) {
            return false;
        }
        return mActivityList.contains(activity);
    }
    /**
     * 指定的activity class是否存活(一个activity可能有多个实例)
     *
     * @param activityClass
     * @return
     */
    public boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityList == null) {
            return false;
        }
        for (AppCompatActivity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }

        return false;
    }
    /**
     * 关闭所有activity
     */
    public void killAll() {
//        while (getActivityList().size() != 0) { //此方法只能兼容LinkedList
//            getActivityList().remove(0).finish();
//        }

        Iterator<AppCompatActivity> iterator = getActivityList().iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
            iterator.remove();
        }

    }
    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            killAll();
            if (mActivityList != null)
                mActivityList = null;
            ActivityManager activityMgr =
                    (ActivityManager) mApplication.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(mApplication.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

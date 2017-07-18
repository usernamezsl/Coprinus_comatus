package com.sunnada.coprinus_comatus.mvp.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunnada.coprinus_comatus.R;
import com.sunnada.coprinus_comatus.base.AppComponent;
import com.sunnada.coprinus_comatus.base.BaseApplication;
import com.sunnada.coprinus_comatus.base.ZSLActivity;
import com.sunnada.coprinus_comatus.di.component.DaggerSplashComponent;
import com.sunnada.coprinus_comatus.di.module.SplashModule;
import com.sunnada.coprinus_comatus.mvp.contract.SplashContract;
import com.sunnada.coprinus_comatus.mvp.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends ZSLActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.img_sp)
    ImageView mImgSp;
    @BindView(R.id.btn_sp_jump)
    Button mBtnSpJump;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //添加到管理栈中
        BaseApplication.getInstance()
                .getAppManager()
                .addActivity(this);
        countDownTimer();
        startClock();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity() {
        ActivityUtils.startActivity(SplashActivity.this, LoginActivity.class);
    }

    @Override
    public void killMyself() {

    }

    /**
     * 实现倒计时功能,不需要理会那些线程交互他都给你处理好了，你只管在回调中处理时间设置跳转逻辑就好了
     * 但是有一个不足的地方就它的第一秒的倒计时有时候会不可见，所以我们将倒计时总时间设置为 4s 。
     */
    public void countDownTimer() {
        mCountDownTimer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBtnSpJump.setText("跳过(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                mBtnSpJump.setText("跳过(" + 0 + "s");
                launchActivity();//跳转主页
            }
        };
    }

    /**
     * 最后需要在有闪屏页面的情况下，进入开启倒计时：
     */
    private void startClock() {
        mBtnSpJump.setVisibility(View.VISIBLE);
        mCountDownTimer.start();
    }

    @OnClick({R.id.img_sp, R.id.btn_sp_jump})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_sp:
                //跳转到广告web页面
                break;
            case R.id.btn_sp_jump:
                launchActivity();
                break;
        }
    }
}

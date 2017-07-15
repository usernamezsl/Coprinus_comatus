package com.sunnada.coprinus_comatus.mvp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.sunnada.coprinus_comatus.MainActivity;
import com.sunnada.coprinus_comatus.R;
import com.sunnada.coprinus_comatus.base.AppComponent;
import com.sunnada.coprinus_comatus.base.BaseApplication;
import com.sunnada.coprinus_comatus.base.ZSLActivity;
import com.sunnada.coprinus_comatus.di.component.DaggerResgisterComponent;
import com.sunnada.coprinus_comatus.di.module.ResgisterModule;
import com.sunnada.coprinus_comatus.mvp.contract.ResgisterContract;
import com.sunnada.coprinus_comatus.mvp.presenter.ResgisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ResgisterActivity extends ZSLActivity<ResgisterPresenter> implements ResgisterContract.View {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edt_user_name)
    EditText mEdtUserName;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    @BindView(R.id.edt_password_again)
    EditText mEdtPasswordAgain;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.btn_reset)
    Button mBtnReset;

    @Override
    protected void initData() {
        //添加到管理栈中
        BaseApplication.getInstance()
                .getAppManager()
                .addActivity(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_resgister;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerResgisterComponent
                .builder()
                .appComponent(appComponent)
                .resgisterModule(new ResgisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {
        LoadingDialog.make(this)
                .setMessage("自动登录中.....")
                .show();
    }

    @Override
    public void hideLoading() {
        LoadingDialog.cancel();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity() {
        ActivityUtils.startActivity(ResgisterActivity.this, MainActivity.class);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @OnClick({R.id.btn_register, R.id.btn_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String username = mEdtUserName.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();
                String passwordAgain = mEdtPasswordAgain.getText().toString().trim();
                mPresenter.resgister(username, password, passwordAgain);
                break;
            case R.id.btn_reset:
                mEdtUserName.setText("");
                mEdtPassword.setText("");
                mEdtPasswordAgain.setText("");
                break;
        }
    }
}

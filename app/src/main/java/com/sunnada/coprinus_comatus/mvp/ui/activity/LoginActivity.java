package com.sunnada.coprinus_comatus.mvp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.sunnada.coprinus_comatus.MainActivity;
import com.sunnada.coprinus_comatus.R;
import com.sunnada.coprinus_comatus.base.AppComponent;
import com.sunnada.coprinus_comatus.base.BaseApplication;
import com.sunnada.coprinus_comatus.base.ZSLActivity;
import com.sunnada.coprinus_comatus.di.component.DaggerLoginComponent;
import com.sunnada.coprinus_comatus.di.module.LoginModule;
import com.sunnada.coprinus_comatus.http.LoginPostService;
import com.sunnada.coprinus_comatus.mvp.contract.LoginContract;
import com.sunnada.coprinus_comatus.mvp.model.entity.User;
import com.sunnada.coprinus_comatus.mvp.presenter.LoginPresenter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends ZSLActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.edt_user_name)
    EditText mEdtUserName;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.cb_remember)
    CheckBox mCbRemember;
    private SPUtils mSpUtils;

    Handler mHandler;
    static int LOGIN_FAILED = 0;
    static int LOGIN_SUCCEEDED = 1;
    Dialog dialog;

    @Override
    protected void initData() {
        //添加到管理栈中
        BaseApplication.getInstance()
                .getAppManager()
                .addActivity(this);
        mSpUtils = SPUtils.getInstance("user_info");
        setUserInfo();
        //Handle,Msg返回成功信息，跳转到其他Activity
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dialog.dismiss();
                if (msg.what == 111) {  // 处理发送线程传回的消息
                    if (msg.obj.toString().equals("SUCCEEDED")) {
                        //跳转
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "账号和密码不匹配", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {
        LoadingDialog.make(this)
                .setMessage("登录中,请稍后...")
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
        ActivityUtils.startActivity(LoginActivity.this, MainActivity.class);
    }

    @Override
    public void killMyself() {

    }


    @OnClick({R.id.profile_image, R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                ToastUtils.showLong("设置头像");
                break;
            case R.id.btn_login:
                String username = mEdtUserName.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();
//                mPresenter.login(username, password);
//                ActivityUtils.startActivity(LoginActivity.this, MainActivity.class);
                if (isConnectingToInternet()) { //检查网络
                    if (username.equals(""))
                        Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    else {
                        //启动登录Thread
                        dialog = new Dialog(LoginActivity.this);
                        dialog.setTitle("正在登录，请稍后...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new LoginPostThread(username,
                                password).start();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "网络未连接", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                ActivityUtils.startActivity(LoginActivity.this, ResgisterActivity.class);
                break;
        }
    }

    //登录Thread调用LoginPostService，返回Msg
    public class LoginPostThread extends Thread {
        public String username, password;

        public LoginPostThread(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void run() {
            // Sevice传回int
            int responseInt = 0;
            if (!username.equals("")) {
                // 要发送的数据
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                // 发送数据，获取对象
                responseInt = LoginPostService.send(params);
                Log.i("tag", "LoginActivity: responseInt = " + responseInt);
                // 准备发送消息
                Message msg = mHandler.obtainMessage();
                // 设置消息默认值
                msg.what = 111;
                // 服务器返回信息的判断和处理
                if (responseInt == LOGIN_FAILED) {
                    msg.obj = "FAILED";
                } else if (responseInt == LOGIN_SUCCEEDED) {
                    msg.obj = "SUCCEEDED";
                }
                mHandler.sendMessage(msg);
            }
        }
    }

    /**
     * 设置用户信息
     */
    @Override
    public void setUserInfo() {
        boolean isRemember = mSpUtils.getBoolean("remember_password", false);
        if (isRemember) {
            //将账号密码设置到文本框中
            String username = mSpUtils.getString("username");
            String password = mSpUtils.getString("password");
            mEdtUserName.setText(username);
            mEdtPassword.setText(password);
            mCbRemember.setChecked(true);
        }
    }

    /**
     * 保存用户信息到本地
     *
     * @param user
     */
    @Override
    public void saveUserInfo(User user) {
        if (mCbRemember.isChecked()) {
            mSpUtils.put("remember_password", true);
            mSpUtils.put("username", user.getUsername());
            mSpUtils.put("password", user.getPassword());
        } else {
            return;
        }
    }

    // 检测网络状态
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

}

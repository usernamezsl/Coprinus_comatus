package com.sunnada.coprinus_comatus.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyleduo.switchbutton.SwitchButton;
import com.sunnada.coprinus_comatus.R;
import com.sunnada.coprinus_comatus.base.BaseApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentActivity extends AppCompatActivity {

    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.switch_btn)
    SwitchButton mSwitchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        ButterKnife.bind(this);
        //添加到管理栈中
        BaseApplication.getInstance()
                .getAppManager()
                .addActivity(this);
        mCenterTitle.setText("设备管理");
        mSwitchBtn.setChecked(false);

        mSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSwitchBtn.setBackColorRes(R.color.greenDark);
                    ToastUtils.showLong("开启风扇");
                } else {
                    mSwitchBtn.setBackColorRes(R.color.gray);
                    ToastUtils.showLong("关闭风扇");
                }
            }
        });
    }
}

package com.sunnada.coprinus_comatus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunnada.coprinus_comatus.base.BaseApplication;
import com.sunnada.coprinus_comatus.base.ZSLApplication;
import com.sunnada.coprinus_comatus.mvp.model.entity.FunctionEntity;
import com.sunnada.coprinus_comatus.mvp.ui.activity.EquipmentActivity;
import com.sunnada.coprinus_comatus.mvp.ui.adapter.AllFunctionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    private AllFunctionAdapter mAdapter;
    private List<FunctionEntity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //添加到管理栈中
        BaseApplication.getInstance()
                .getAppManager()
                .addActivity(this);
        mCenterTitle.setText("功能模块");
        initAdapter();
    }

    /**
     * 初始化 渲染adapter
     */
    private void initAdapter() {
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setHasFixedSize(true);
        mList = getData();
        mAdapter = new AllFunctionAdapter(R.layout.item_function_main, mList);
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<FunctionEntity> mData = adapter.getData();
                FunctionEntity functionEntity = mData.get(position);
                switch (functionEntity.getTitle()) {
                    case "查看温湿度":
                        ToastUtils.showLong(functionEntity.getTitle());
                        break;
                    case "查看光照强度":
                        ToastUtils.showLong(functionEntity.getTitle());
                        break;
                    case "预警值设置":
                        ToastUtils.showLong(functionEntity.getTitle());
                        break;
                    case "设备管理":
                        ActivityUtils.startActivity(MainActivity.this, EquipmentActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 数据构造
     *
     * @return
     */
    private List<FunctionEntity> getData() {
        List<FunctionEntity> mData = new ArrayList<>();
        mData.add(new FunctionEntity("查看温湿度", R.drawable.ic_image1_new));
        mData.add(new FunctionEntity("查看光照强度", R.drawable.ic_image2_new));
        mData.add(new FunctionEntity("预警值设置", R.drawable.ic_image3_new));
        mData.add(new FunctionEntity("设备管理", R.drawable.ic_image4_new));
        return mData;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtils.showLong("主人，你真的要离开我了吗");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-
                ((ZSLApplication) BaseApplication.getInstance())
                        .getAppComponent()
                        .appManager()
                        .AppExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

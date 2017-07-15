package com.sunnada.coprinus_comatus.mvp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunnada.coprinus_comatus.R;
import com.sunnada.coprinus_comatus.mvp.model.entity.FunctionEntity;

import java.util.List;

/**
 * 作者: 张少林 on 2017/6/11 0011.
 * 邮箱:1083065285@qq.com
 */

public class AllFunctionAdapter extends BaseQuickAdapter<FunctionEntity, BaseViewHolder> {
    public AllFunctionAdapter(@LayoutRes int layoutResId, @Nullable List<FunctionEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FunctionEntity item) {
        ImageView imageView = helper.getView(R.id.iv_check_driver);
        helper.setText(R.id.tv_check_driver, item.getTitle());
        Glide.with(mContext)
                .load(item.getImgId())
                .override(200,200)
                .into(imageView);
    }
}

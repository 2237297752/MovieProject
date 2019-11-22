package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.SelectTimeCinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectTimeCinemaAdapter extends BaseQuickAdapter<SelectTimeCinemaBean.ResultBean, BaseViewHolder> {
    public SelectTimeCinemaAdapter(int layoutResId, @Nullable List<SelectTimeCinemaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectTimeCinemaBean.ResultBean item) {
        ImageView img_selectcinematime = helper.getView(R.id.img_selectcinematime);
        Glide.with(mContext).load(item.getLogo()).into(img_selectcinematime);
        helper.setText(R.id.name_selectcinematime,item.getName());
        helper.setText(R.id.adress_selectcinematime,item.getAddress());
    }
}

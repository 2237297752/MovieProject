package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.model.bean.DistrictLocationCinemaBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DistrictLocationCinemaAdapter extends BaseQuickAdapter<DistrictLocationCinemaBean.ResultBean, BaseViewHolder> {
    public DistrictLocationCinemaAdapter(int layoutResId, @Nullable List<DistrictLocationCinemaBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DistrictLocationCinemaBean.ResultBean item) {
        helper.setText(R.id.distrilocationcinema_itemadapter,item.getName());
    }
}

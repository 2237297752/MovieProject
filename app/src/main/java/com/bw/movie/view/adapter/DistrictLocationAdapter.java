package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.model.bean.DistrictLocationBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DistrictLocationAdapter extends BaseQuickAdapter<DistrictLocationBean.ResultBean, BaseViewHolder> {
    public DistrictLocationAdapter(int layoutResId, @Nullable List<DistrictLocationBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DistrictLocationBean.ResultBean item) {
        helper.setText(R.id.distrilocation_itemadapter,item.getRegionName());
    }
}

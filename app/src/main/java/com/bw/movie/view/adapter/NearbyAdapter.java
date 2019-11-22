package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.NearbyBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class NearbyAdapter extends BaseQuickAdapter<NearbyBean.ResultBean, BaseViewHolder> {
    public NearbyAdapter(int layoutResId, @Nullable List<NearbyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyBean.ResultBean item) {
        ImageView img_nearby = helper.getView(R.id.img_nearby);
        Glide.with(mContext).load(item.getLogo()).into(img_nearby);
        helper.setText(R.id.name_nearby,item.getName());
        helper.setText(R.id.adress_nearby,item.getAddress());
        helper.setText(R.id.kilm_nearby,item.getDistance()+""+"km");
    }
}

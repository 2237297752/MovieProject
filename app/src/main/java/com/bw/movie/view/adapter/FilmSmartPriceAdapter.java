package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.SelectSmartPriceBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FilmSmartPriceAdapter extends BaseQuickAdapter<SelectSmartPriceBean.ResultBean, BaseViewHolder> {
    public FilmSmartPriceAdapter(int layoutResId, @Nullable List<SelectSmartPriceBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectSmartPriceBean.ResultBean item) {
        ImageView img_smartprice = helper.getView(R.id.img_smartprice);
        Glide.with(mContext).load(item.getLogo()).into(img_smartprice);
        helper.setText(R.id.name_smartprice,item.getName());
        helper.setText(R.id.adress_smartprice,item.getAddress());
        helper.setText(R.id.price_smartprice,item.getPrice()+""+"元");
    }
}

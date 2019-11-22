package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.RecommendBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<RecommendBean.ResultBean, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, @Nullable List<RecommendBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean.ResultBean item) {
        ImageView img_recommend = helper.getView(R.id.img_recommend);
        Glide.with(mContext).load(item.getLogo()).into(img_recommend);
        helper.setText(R.id.name_recommend,item.getName());
        helper.setText(R.id.adress_recommend,item.getAddress());
    }
}

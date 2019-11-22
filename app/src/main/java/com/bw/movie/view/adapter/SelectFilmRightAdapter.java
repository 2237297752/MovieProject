package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.SelectFilmRightBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectFilmRightAdapter extends BaseQuickAdapter<SelectFilmRightBean.ResultBean, BaseViewHolder> {
    public SelectFilmRightAdapter(int layoutResId, @Nullable List<SelectFilmRightBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectFilmRightBean.ResultBean item) {
        ImageView img_selectdetailright = helper.getView(R.id.img_selectdetailright);
        Glide.with(mContext).load(item.getLogo()).into(img_selectdetailright);
        helper.setText(R.id.name_selectdetailright,item.getName());
        helper.setText(R.id.adress_selectdetailright,item.getAddress());
    }
}

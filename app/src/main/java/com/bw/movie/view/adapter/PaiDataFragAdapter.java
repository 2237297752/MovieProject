package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.PaiDataBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaiDataFragAdapter extends BaseQuickAdapter<PaiDataBean.ResultBean, BaseViewHolder> {
    public PaiDataFragAdapter(int layoutResId, @Nullable List<PaiDataBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaiDataBean.ResultBean item) {
        ImageView img_paidatafrag = helper.getView(R.id.img_paidatafrag);
        Glide.with(mContext).load(item.getImageUrl()).into(img_paidatafrag);
        helper.setText(R.id.name_paidatafrag,item.getName());
        helper.setText(R.id.dirctor_paidatafrag,"导演："+item.getDirector());
        helper.setText(R.id.actor_paidatafrag,"主演："+item.getStarring());
        helper.setText(R.id.score_paidatafrag,"评分："+item.getScore()+""+"分");
    }
}

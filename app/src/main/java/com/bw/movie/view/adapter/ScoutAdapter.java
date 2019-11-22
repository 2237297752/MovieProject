package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.ScoutBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ScoutAdapter extends BaseQuickAdapter<ScoutBean.ResultBean, BaseViewHolder> {
    public ScoutAdapter(int layoutResId, @Nullable List<ScoutBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScoutBean.ResultBean item) {
        ImageView img_scout = helper.getView(R.id.img_scout);
        Glide.with(mContext).load(item.getImageUrl()).into(img_scout);
        helper.setText(R.id.name_scout,item.getName());
        helper.setText(R.id.drotr_scout,item.getDirector());
        helper.setText(R.id.actor_scout,item.getStarring());
        helper.setText(R.id.scror_scout,item.getScore()+""+"分");
        Button pay_scout = helper.getView(R.id.pay_scout);
        scoutAdapterBack.getscoutAdapterBack(pay_scout);
    }

    public interface scoutAdapterBack{
        void getscoutAdapterBack(Button pay_scout);
    }
    scoutAdapterBack scoutAdapterBack;
    public void setScoutAdapterBack(scoutAdapterBack scoutAdapterBack){
        this.scoutAdapterBack=scoutAdapterBack;
    }
}

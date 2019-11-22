package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaSchedBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectSchedAdapter extends BaseQuickAdapter<CinemaSchedBean.ResultBean, BaseViewHolder> {
    public SelectSchedAdapter(int layoutResId, @Nullable List<CinemaSchedBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CinemaSchedBean.ResultBean item) {
        helper.setText(R.id.movehollname,item.getScreeningHall());
        helper.setText(R.id.moveonetimestart,item.getBeginTime());
        helper.setText(R.id.moveonetimeclose,item.getEndTime());
        int schallId = item.getHallId();
        String screeningHall = item.getScreeningHall();
        SharedPreferences tsp = mContext.getSharedPreferences("selectseat", Context.MODE_PRIVATE);
        tsp.edit().putInt("schallId",schallId).commit();
        tsp.edit().putString("screeningHall",screeningHall).commit();
    }
}

package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.model.bean.WillBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WillAdapter extends BaseQuickAdapter<WillBean.ResultBean, BaseViewHolder> {
    public WillAdapter(int layoutResId, @Nullable List<WillBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WillBean.ResultBean item) {

        SimpleDraweeView imgsimp_will = helper.getView(R.id.imgsimp_will);
        imgsimp_will.setImageURI(item.getImageUrl());
        helper.setText(R.id.name_will,item.getName());
        helper.setText(R.id.person_will,item.getWantSeeNum()+""+"人想看");

        Date date = new Date(item.getReleaseTime());
        String dateStr = new SimpleDateFormat("MM月dd日").format(date);
        helper.setText(R.id.time_will,dateStr+"上映");

        Button yuyue_will = helper.getView(R.id.yuyue_will);
        onWillYuYueCallBack.getOnWillYuYueCallBack(yuyue_will,item.getMovieId());
    }
    public interface OnWillYuYueCallBack{
        void getOnWillYuYueCallBack(Button yuyue_will, int movieId);
    }
    OnWillYuYueCallBack onWillYuYueCallBack;

    public void setOnWillYuYueCallBack(OnWillYuYueCallBack onWillYuYueCallBack) {
        this.onWillYuYueCallBack = onWillYuYueCallBack;
    }
}

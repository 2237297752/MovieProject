package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinemaDetailSpeaakBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CinemaDetailSpeakAdapter extends BaseQuickAdapter<CinemaDetailSpeaakBean.ResultBean, BaseViewHolder> {
    public CinemaDetailSpeakAdapter(int layoutResId, @Nullable List<CinemaDetailSpeaakBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CinemaDetailSpeaakBean.ResultBean item) {
        SimpleDraweeView cinemadetailspeak = helper.getView(R.id.img_cinemadetailspeak);
        cinemadetailspeak.setImageURI(item.getCommentHeadPic());
        helper.setText(R.id.name_cinemadetailspeak, item.getCommentUserName());

        helper.setText(R.id.zancount_cinemadetailspeak, item.getGreatNum() + "");

        Date date = new Date(item.getCommentTime());
        String dateStr = new SimpleDateFormat("MM-dd hh:mm").format(date);
        helper.setText(R.id.data_cinemadetailspeak, dateStr);

        helper.setText(R.id.cinmecontent_cinemadetailspeak, item.getCommentContent());
        helper.setText(R.id.replynum_cinemadetailspeak, "等" + item.getGreatNum() + "" + "人觉得很赞");

        RecyclerView imgrecycle_cinemadetailspeak = helper.getView(R.id.imgrecycle_cinemadetailspeak);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        imgrecycle_cinemadetailspeak.setLayoutManager(manager);
        FilmChilderCineCismAdapter filmChilderCineCismAdapter = new FilmChilderCineCismAdapter(R.layout.item_childerfilmcinecismdapter, item.getGreatHeadPic());
        imgrecycle_cinemadetailspeak.setAdapter(filmChilderCineCismAdapter);
    }
}

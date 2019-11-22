package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.bw.movie.R;
import com.bw.movie.model.bean.CinecismBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilmCinecismAdapter extends BaseQuickAdapter<CinecismBean.ResultBean, BaseViewHolder> {
    public FilmCinecismAdapter(int layoutResId, @Nullable List<CinecismBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CinecismBean.ResultBean item) {
        SimpleDraweeView img_filmcinecism = helper.getView(R.id.img_filmcinecism);
        img_filmcinecism.setImageURI(item.getCommentHeadPic());
        helper.setText(R.id.name_filmcinecism, item.getCommentUserName());
        helper.setText(R.id.scroe_filmcinecism, item.getScore() + "" + "分");

        helper.setText(R.id.zancount_filmcinecism, item.getGreatNum() + "");

        Date date = new Date(item.getCommentTime());
        String dateStr = new SimpleDateFormat("MM-dd hh:mm").format(date);
        helper.setText(R.id.data_filmcinecism, dateStr);

        helper.setText(R.id.cinmecontent_filmcinecism, item.getCommentContent());
        helper.setText(R.id.replynum_filmcinecism, "等" + item.getReplyNum() + "" + "人进行了回复");

        RecyclerView imgrecycle_filmcinecism = helper.getView(R.id.imgrecycle_filmcinecism);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        imgrecycle_filmcinecism.setLayoutManager(manager);
        FilmChilderCineCismAdapter filmChilderCineCismAdapter = new FilmChilderCineCismAdapter(R.layout.item_childerfilmcinecismdapter, item.getReplyHeadPic());
        imgrecycle_filmcinecism.setAdapter(filmChilderCineCismAdapter);
    }
}

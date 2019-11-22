package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.model.bean.PlayingBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CinemaPlayingAdapter extends BaseQuickAdapter<PlayingBean.ResultBean, BaseViewHolder> {
    public CinemaPlayingAdapter(int layoutResId, @Nullable List<PlayingBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayingBean.ResultBean item) {
        SimpleDraweeView imgsimp_playing = helper.getView(R.id.imgsimp_playing);
        imgsimp_playing.setImageURI(item.getImageUrl());
        helper.setText(R.id.name_playing,item.getName());
        helper.setText(R.id.scrore_playing,item.getScore()+""+"分") ;

        Button btn_payplaying = helper.getView(R.id.pay_playing);
        playingInter.getplayingInter(btn_payplaying);
    }
    public interface playingInter{
        void getplayingInter(Button btn_payplaying);
    }
    playingInter playingInter;

    public void setPlayingInter(CinemaPlayingAdapter.playingInter playingInter) {
        this.playingInter = playingInter;
    }
}

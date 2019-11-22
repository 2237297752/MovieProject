package com.bw.movie.view.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.FilmDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class FilmHeraldVideoAdapter extends BaseQuickAdapter<FilmDetailBean.ResultBean.ShortFilmListBean, BaseViewHolder> {
    public FilmHeraldVideoAdapter(int layoutResId, @Nullable List<FilmDetailBean.ResultBean.ShortFilmListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilmDetailBean.ResultBean.ShortFilmListBean item) {
        JCVideoPlayer item_jcvideo = helper.getView(R.id.item_jcvideo);
        item_jcvideo.setUp(item.getVideoUrl(),null);
        Glide.with(mContext).load(item.getImageUrl()).into(item_jcvideo.ivThumb);
    }
}

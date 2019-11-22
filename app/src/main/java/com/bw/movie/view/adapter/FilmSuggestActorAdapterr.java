package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.FilmDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FilmSuggestActorAdapterr extends BaseQuickAdapter<FilmDetailBean.ResultBean.MovieActorBean, BaseViewHolder> {
    public FilmSuggestActorAdapterr(int layoutResId, @Nullable List<FilmDetailBean.ResultBean.MovieActorBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilmDetailBean.ResultBean.MovieActorBean item) {
        ImageView img_itemsuggestactor = helper.getView(R.id.img_itemsuggestactor);
        Glide.with(mContext).load(item.getPhoto()).into(img_itemsuggestactor);
        helper.setText(R.id.name_itemsuggestactor,item.getName());
    }
}

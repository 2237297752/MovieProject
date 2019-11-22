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

public class FilmSuggestDirectAdapter extends BaseQuickAdapter<FilmDetailBean.ResultBean.MovieDirectorBean, BaseViewHolder> {
    public FilmSuggestDirectAdapter(int layoutResId, @Nullable List<FilmDetailBean.ResultBean.MovieDirectorBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilmDetailBean.ResultBean.MovieDirectorBean item) {
        ImageView img_itemsuggestdirect = helper.getView(R.id.img_itemsuggestdirect);
        Glide.with(mContext).load(item.getPhoto()).into(img_itemsuggestdirect);
        helper.setText(R.id.name_itemsuggestdirect,item.getName());
    }
}

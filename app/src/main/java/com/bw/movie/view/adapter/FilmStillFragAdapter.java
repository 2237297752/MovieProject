package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class FilmStillFragAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FilmStillFragAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        SimpleDraweeView item_filmstillimg = helper.getView(R.id.item_filmstillimg);
        item_filmstillimg.setImageURI(item);
    }
}

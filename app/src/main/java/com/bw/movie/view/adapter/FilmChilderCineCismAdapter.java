package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FilmChilderCineCismAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FilmChilderCineCismAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView item_childercinecismimg = helper.getView(R.id.item_childercinecismimg);
        Glide.with(mContext).load(R.drawable.ic_launcher_background).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(item_childercinecismimg);
    }
}

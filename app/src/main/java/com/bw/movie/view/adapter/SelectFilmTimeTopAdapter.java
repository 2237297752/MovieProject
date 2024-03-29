package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectFilmTimeTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SelectFilmTimeTopAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.timetop_filmdetail,item);
    }
}

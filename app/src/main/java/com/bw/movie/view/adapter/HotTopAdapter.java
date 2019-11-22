package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.model.bean.HotBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;
import java.util.List;

public class HotTopAdapter extends BaseQuickAdapter<HotBean.ResultBean, BaseViewHolder> {


    public HotTopAdapter(int layoutResId, @Nullable List<HotBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotBean.ResultBean item) {
        SimpleDraweeView imgsimp_hotbottom = helper.getView(R.id.imgsimp_hotbottom);
        imgsimp_hotbottom.setImageURI(item.getImageUrl());
        helper.setText(R.id.name_hotbottom,item.getName());
        helper.setText(R.id.dirctor_tabplayigmore,item.getDirector());
        helper.setText(R.id.actor_tabplayigmore,item.getStarring());
        helper.setText(R.id.scrore_hotbottom,item.getScore()+""+"分");

        Button btn_payhotbottom = helper.getView(R.id.pay_hotbottom);
        myPayhotBottom.getmyPayhotBottom(btn_payhotbottom);
    }

    public interface myPayhotBottom{
        void getmyPayhotBottom(Button btn_payhotbottom);
    }
    myPayhotBottom myPayhotBottom;
    public void setMyPayhotBottom(myPayhotBottom myPayhotBottom){
        this.myPayhotBottom=myPayhotBottom;
    }
}

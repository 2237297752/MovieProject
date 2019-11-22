package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.bean.ChooseSeatBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class XuanZuoAdapter extends BaseQuickAdapter<List<ChooseSeatBean.ResultBean>, BaseViewHolder> {
    public XuanZuoAdapter(int layoutResId, @Nullable List<List<ChooseSeatBean.ResultBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final List<ChooseSeatBean.ResultBean> item) {
        RecyclerView xuan_item = helper.getView(R.id.xuan_item);
        xuan_item.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        IntterSeatAdapter intterSeatAdapter = new IntterSeatAdapter(R.layout.item_inttersettr, item);
        xuan_item.setAdapter(intterSeatAdapter);
        intterSeatAdapter.setCallBack(new IntterSeatAdapter.CallBack() {
            @Override
            public void getBack(String s) {
                callBack.getBack(s);
            }
        });

    }
    public interface CallBack{
        void getBack(String s);
    }
    CallBack callBack;
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }
}

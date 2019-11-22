package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bw.movie.R;
import com.bw.movie.model.bean.ChooseSeatBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class IntterSeatAdapter extends BaseQuickAdapter<ChooseSeatBean.ResultBean, BaseViewHolder> {
    public IntterSeatAdapter(int layoutResId, @Nullable List<ChooseSeatBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChooseSeatBean.ResultBean item) {
        final CheckBox cb = helper.getView(R.id.cb);
        int status = item.getStatus();
        String row = item.getRow();
        String seat = item.getSeat();
        String scaseat = row + "-" + seat;
        SharedPreferences sp = mContext.getSharedPreferences("selectseat", Context.MODE_PRIVATE);
        sp.edit().putString("scaseat",scaseat).commit();
        if (status == 1) {
            cb.setChecked(false);
        } else if (status == 2) {
            cb.setChecked(true);
            cb.setBackgroundColor(R.drawable.myy_shape);
        }
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb.isChecked()) {
                    item.setStatus(3);
                    callBack.getBack(item.getRow() + "排" + item.getSeat() + "座");
                } else {
                    item.setStatus(1);
                    callBack.getBack("取消选座");
                }
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

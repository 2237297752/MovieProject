package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.ScoutContract;
import com.bw.movie.model.ScoutModel;

public class ScoutPresenter<V extends ScoutContract.ScoutView> extends BasePresenter<V> {


    private final ScoutModel model;

    public ScoutPresenter() {
        //创建m层
        model = new ScoutModel();
    }
    public void requestScoutInfo(String keyword, int count, int page){
        model.requestScoutData(keyword,count,page,new ScoutContract.ScoutModel.CallScoutBack() {
            @Override
            public void getScoutResult(String scoutString) {
                getView().getScoutView(scoutString);
            }
        });
    }
}

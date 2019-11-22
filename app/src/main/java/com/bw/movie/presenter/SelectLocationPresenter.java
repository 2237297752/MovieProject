package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.SelectLocationContract;
import com.bw.movie.model.SelectLocationModel;

public class SelectLocationPresenter<V extends SelectLocationContract.SelectLocationView> extends BasePresenter<V> {


    private final SelectLocationModel model;

    public SelectLocationPresenter() {
        //创建m层
        model = new SelectLocationModel();
    }

    public void requestSelectTopInfo(int movieId){
        model.requestSelectTopData(movieId,new SelectLocationContract.SelectLocationModel.CallSelectTopBack() {
            @Override
            public void getSelectTopResult(String topString) {
                getView().getSelectTopView(topString);
            }
        });
    }

    public void requestSelectLeftInfo(){
        model.requestSelectLeftData(new SelectLocationContract.SelectLocationModel.CallSelectLeftBack() {
            @Override
            public void getSelectLeftResult(String locationString) {
                getView().getSelectLeftView(locationString);
            }
        });
    }

    public void requestSelectRightInfo(int regionId, int movieId, int count, int page){
        model.requestSelectRightData(regionId,movieId,count,page,new SelectLocationContract.SelectLocationModel.CallSelectRightBack() {
            @Override
            public void getSelectRightResult(String locationreight) {
                getView().getSelectRightView(locationreight);
            }
        });
    }

    public void requestSelectTimeInfo(){
        model.requestSelectTimeData(new SelectLocationContract.SelectLocationModel.CallSelectTimeBack() {
            @Override
            public void getSelectTimeResult(String timeString) {
                getView().getSelectTimeView(timeString);
            }
        });
    }

    public void requestSelectTimeCinemaInfo(int movieId, String data, int count, int page){
        model.requestSelectTimeCinemaData(movieId,data,count,page,new SelectLocationContract.SelectLocationModel.CallSelectTimeCinemaBack() {
            @Override
            public void getSelectTimeCinemaResult(String timeCinemaString) {
                getView().getSelectTimeCinemaView(timeCinemaString);
            }
        });
    }

    public void requestSelectPriceInfo(int movieId, int count, int page){
        model.requestSelectPriceData(movieId,count,page,new SelectLocationContract.SelectLocationModel.CallSelectPriceBack() {
            @Override
            public void getSelectPriceResult(String priceString) {
                getView().getSelectPriceView(priceString);
            }
        });
    }
}

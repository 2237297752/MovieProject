package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.model.CinemaModel;

public class CinemaPresenter<V extends CinemaContract.CinemaView> extends BasePresenter<V> {

    private final CinemaModel model;

    public CinemaPresenter() {
        model = new CinemaModel();
    }
    public void requestBannerInfo(){
        model.requestBannerData(new CinemaContract.CinemaModel.CallBannerBack() {
            @Override
            public void getBannerResult(String bannerString) {
                getView().getBannerView(bannerString);
            }
        });
    }
    public void requestPlayingInfo(int count,int page){
        model.requestPlayingData(count, page, new CinemaContract.CinemaModel.CallPlayingBack() {
            @Override
            public void getPlayingResult(String playingString) {
                getView().getPlayingView(playingString);
            }
        });
    }
    public void requestWillInfo(int count,int page,int userId,String sessionId){
        model.requestWillData(count, page, userId, sessionId, new CinemaContract.CinemaModel.CallWillBack() {
            @Override
            public void getWillResult(String willString) {
                getView().getWillView(willString);
            }
        });
    }
    public void requestHotInfo(int count,int page){
        model.requestHotData(count, page, new CinemaContract.CinemaModel.CallHotBack() {
            @Override
            public void getHotResult(String hotString) {
                getView().getHotView(hotString);
            }
        });
    }
    public void requestBtnyuyueInfo(int userId,String sessionId,int movieId){
        model.requestBtnyuyueData(userId, sessionId, movieId, new CinemaContract.CinemaModel.CallBtnyuyueBack() {
            @Override
            public void getBtnyuyueResult(String yuyueFilmString) {
                getView().getBtnyuyueView(yuyueFilmString);
            }
        });
    }
}

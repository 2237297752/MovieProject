package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.FilmContract;
import com.bw.movie.model.FilmModel;

public class FilmPresenter<V extends FilmContract.FilmView> extends BasePresenter<V> {


    private final FilmModel model;

    public FilmPresenter() {
        //创建m层
        model = new FilmModel();
    }

    public void requestBannerInfo() {
        model.requestBannerData(new FilmContract.FilmModel.CallBannerBack() {
            @Override
            public void getBannerResult(String bannerString) {
                getView().getBannerView(bannerString);
            }
        });
    }
    public void requestPlayingInfo(int count, int page) {
        model.requestPlayingData(count,page,new FilmContract.FilmModel.CallPlayingBack() {
            @Override
            public void getPlayingResult(String playingString) {
                getView().getPlayingView(playingString);
            }
        });
    }
    public void requestWillInfo(int count, int page, int userId, String sessionId) {
        model.requestWillData(count,page,userId,sessionId,new FilmContract.FilmModel.CallWillBack() {
            @Override
            public void getWillResult(String willString) {
                getView().getWillView(willString);
            }
        });
    }
    public void requestHotInfo(int count, int page) {
        model.requestHotData(count,page,new FilmContract.FilmModel.CallHotBack() {
            @Override
            public void getHotResult(String hotString) {
                getView().getHotView(hotString);
            }
        });
    }

    public void requestBtnyuyueInfo(int userId, String sessionId, int movieId) {
        model.requestBtnyuyueData(userId,sessionId,movieId,new FilmContract.FilmModel.CallBtnyuyueBack() {
            @Override
            public void getBtnyuyueResult(String yuyueFilmString) {
                getView().getBtnyuyueView(yuyueFilmString);
            }
        });
    }

}

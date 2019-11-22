package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.MovieDetailContract;
import com.bw.movie.model.MovieDetailModel;

public class MovieDetailPresenter<V extends MovieDetailContract.MovieDetailView> extends BasePresenter<V> {


    private final MovieDetailModel model;

    public MovieDetailPresenter() {
        //创建m层
        model = new MovieDetailModel();
    }
    public void requestCinemaDetailTopInfo(int userId, String sessionId, int cinemaId){
        model.requestCinemaDetailTopData(userId,sessionId,cinemaId,new MovieDetailContract.MovieDetailModel.CallCinemaDetailTopBack() {
            @Override
            public void getCinemaDetailTopResult(String cinemaDetailString) {
                getView().getCinemaDetailTopView(cinemaDetailString);
            }
        });
    }

    public void requestCinemaDetailSpeakInfo(int userId, String sessionId, int cinemaId, int count, int page){
        model.requestCinemaDetailSpeakData(userId,sessionId,cinemaId,count,page,new MovieDetailContract.MovieDetailModel.CallCinemaDetailSpeakBack() {
            @Override
            public void getCinemaDetailSpeakResult(String cinemadetailSpeakString) {
                getView().getCinemaDetailSpeakView(cinemadetailSpeakString);
            }
        });
    }

    public void requestCinemaDetailGuanzhuInfo(int userId, String sessionId, int cinemaId){
        model.requestCinemaDetailGuanzhuData(userId,sessionId,cinemaId,new MovieDetailContract.MovieDetailModel.CallCinemaDetailGuanzhuBack() {
            @Override
            public void getCinemaDetailGuanzhuResult(String gunazhuString) {
                getView().getCinemaDetailGuanzhuView(gunazhuString);
            }
        });
    }

    public void requestCinemaDetailWeiGuanzhuInfo(int userId, String sessionId, int cinemaId){
        model.requestCinemaDetailWeiGuanzhuData(userId,sessionId,cinemaId,new MovieDetailContract.MovieDetailModel.CallCinemaDetailWeiGuanzhuBack() {
            @Override
            public void getCinemaDetailWeiGuanzhuResult(String weiString) {
                getView().getCinemaDetailWeiGuanzhuView(weiString);
            }
        });
    }

    public void requestFilmPaiDataInfo(int cinemaId, int count, int page){
        model.requestFilmPaiDataData(cinemaId,count,page,new MovieDetailContract.MovieDetailModel.CallFilmPaiDataBack() {
            @Override
            public void getFilmPaiDataResult(String paiDataString) {
                getView().getFilmPaiDataView(paiDataString);
            }
        });
    }
}

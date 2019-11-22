package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.MovieModel;

public class MoviePresenter<V extends MovieContract.MovieView> extends BasePresenter<V> {


    private final MovieModel model;

    public MoviePresenter() {
        //创建m层
        model = new MovieModel();
    }

    public void requestRecommendInfo(int count, int page) {
        model.requestRecommendData(count, page, new MovieContract.MovieModel.CallRecommendBack() {
            @Override
            public void getRecommendResult(String recommendString) {
                getView().getRecommendView(recommendString);
            }
        });
    }

    public void requestNearbyInfo(int userId, String sessionId, int page, int count) {
        model.requestNearbyData(userId,sessionId,page,count,new MovieContract.MovieModel.CallNearbyBack() {
            @Override
            public void getNearbyResult(String nearbyString) {
                getView().getNearbyView(nearbyString);
            }
        });
    }

    public void requestDistrictInfo() {
        model.requestDistrictData(new MovieContract.MovieModel.CallDistrictBack() {
            @Override
            public void getDistrictResult(String locationString) {
                getView().getDistrictView(locationString);
            }
        });
    }

    public void requestDistrictCinemaInfo(int regionId) {
        model.requestDistrictCinemaData(regionId,new MovieContract.MovieModel.CallDistrictCinemaBack() {
            @Override
            public void getDistrictCinemaResult(String locationCinemaString) {
                getView().getDistrictCinemaView(locationCinemaString);
            }
        });
    }
}

package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.FilmDetailHomeContract;
import com.bw.movie.model.FilmDetailHomeModel;

public class FilmDetailHomePresenter<V extends FilmDetailHomeContract.FilmDetailHomeView> extends BasePresenter<V> {


    private final FilmDetailHomeModel model;

    public FilmDetailHomePresenter() {
        //创建m层
        model = new FilmDetailHomeModel();
    }
    public void requestFilmDetailHomeInfo(int movieId){
        model.requestFilmDetailHomeData(movieId,new FilmDetailHomeContract.FilmDetailHomeModel.CallFilmDetailHomeBack() {
            @Override
            public void getFilmDetailHomeResult(String filmdetailString) {
                getView().getFilmDetailHomeView(filmdetailString);
            }
        });
    }
    //关注
    public void requestForFilmDetailfollowHomeInfo(int userId, String sessionId, int movieId){
        model.requestForFilmDetailfollowHomeData(userId,sessionId,movieId,new FilmDetailHomeContract.FilmDetailHomeModel.CallForFilmDetailfollowHomeBack() {
            @Override
            public void getForFilmDetailfollowHomeResult(String forFilmFllowString) {
                getView().getForFilmDetailfollowHomeView(forFilmFllowString);
            }
        });
    }
    //取消关注
    public void requestForFilmDetailunfollowHomeInfo(int userId, String sessionId, int movieId){
        model.requestForFilmDetailunfollowHomeData(userId,sessionId,movieId,new FilmDetailHomeContract.FilmDetailHomeModel.CallForFilmDetailunfollowHomeBack() {
            @Override
            public void getForFilmDetailunfollowHomeResult(String forFilmUnFollow) {
                getView().getForFilmDetailunfollowHomeView(forFilmUnFollow);
            }
        });
    }
}

package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.FilmDetailModel;

public class FilmDetailPresenter<V extends FilmDeatilContract.FilmDetailView> extends BasePresenter<V> {

    private final FilmDetailModel model;

    public FilmDetailPresenter() {
        //获取m层
        model = new FilmDetailModel();
    }

    public void requestSuggestInfo(int movieId){
        model.requestSuggestData(movieId,new FilmDeatilContract.FilmDetailModel.CallsuggestBack() {
            @Override
            public void getsuggestResult(String filmdetailString) {
                getView().getSuggestData(filmdetailString);
            }
        });
    }

    public void requestHeraldInfo(int movieId){
        model.requestHeraldData(movieId,new FilmDeatilContract.FilmDetailModel.CallheraldBack() {
            @Override
            public void getheraldResult(String videoString) {
                getView().getHeraldData(videoString);
            }
        });
    }

    public void requestStillInfo(int movieId){
        model.requestStillData(movieId,new FilmDeatilContract.FilmDetailModel.CallstillBack() {
            @Override
            public void getstillResult(String stillString) {
                getView().getStillData(stillString);
            }
        });
    }

    public void requestCineCimsInfo(int movieId, int count, int page){
        model.requestCineCimsData(movieId,count,page,new FilmDeatilContract.FilmDetailModel.CallCineCimsBack() {
            @Override
            public void getCineCimsResult(String cinecismString) {
                getView().getCineCimsData(cinecismString);
            }
        });
    }
}

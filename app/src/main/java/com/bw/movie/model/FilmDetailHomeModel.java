package com.bw.movie.model;

import com.bw.movie.contract.FilmDetailHomeContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class FilmDetailHomeModel implements FilmDetailHomeContract.FilmDetailHomeModel {

    @Override
    public void requestFilmDetailHomeData(int movieId, CallFilmDetailHomeBack callFilmDetailHomeBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String filmdetailString = body.string();
                        callFilmDetailHomeBack.getFilmDetailHomeResult(filmdetailString);
                    }
                });
    }

    @Override
    public void requestForFilmDetailfollowHomeData(int userId, String sessionId, int movieId, CallForFilmDetailfollowHomeBack callForFilmDetailfollowHomeBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainForFilmFllowInfo(userId,sessionId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String forFilmFllowString = body.string();
                        callForFilmDetailfollowHomeBack.getForFilmDetailfollowHomeResult(forFilmFllowString);
                    }
                });
    }

    @Override
    public void requestForFilmDetailunfollowHomeData(int userId, String sessionId, int movieId, CallForFilmDetailunfollowHomeBack callForFilmDetailunfollowHomeBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainForFilmUnFllowInfo(userId,sessionId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String forFilmUnFollow = body.string();
                        callForFilmDetailunfollowHomeBack.getForFilmDetailunfollowHomeResult(forFilmUnFollow);
                    }
                });
    }
}

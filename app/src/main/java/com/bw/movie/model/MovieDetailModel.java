package com.bw.movie.model;

import com.bw.movie.contract.MovieDetailContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MovieDetailModel implements MovieDetailContract.MovieDetailModel{

    @Override
    public void requestCinemaDetailTopData(int userId, String sessionId, int cinemaId, final CallCinemaDetailTopBack callCinemaDetailTopBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainCinemaDetailInfo(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String cinemaDetailString = body.string();
                        callCinemaDetailTopBack.getCinemaDetailTopResult(cinemaDetailString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestCinemaDetailSpeakData(int userId, String sessionId, int cinemaId, int count, int page, final CallCinemaDetailSpeakBack callCinemaDetailSpeakBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainCinemaDetailSpeakInfo(userId,sessionId,cinemaId,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String cinemadetailSpeakString = body.string();
                        callCinemaDetailSpeakBack.getCinemaDetailSpeakResult(cinemadetailSpeakString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestCinemaDetailGuanzhuData(int userId, String sessionId, int cinemaId, final CallCinemaDetailGuanzhuBack callCinemaDetailGuanzhuBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainGuanzhuCinemaInfo(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String gunazhuString = body.string();
                        callCinemaDetailGuanzhuBack.getCinemaDetailGuanzhuResult(gunazhuString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestCinemaDetailWeiGuanzhuData(int userId, String sessionId, int cinemaId, final CallCinemaDetailWeiGuanzhuBack callCinemaDetailWeiGuanzhuBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainQuxiaoGuanzhuCinemaInfo(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String weiString = body.string();
                        callCinemaDetailWeiGuanzhuBack.getCinemaDetailWeiGuanzhuResult(weiString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestFilmPaiDataData(int cinemaId, int count, int page, final CallFilmPaiDataBack callFilmPaiDataBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmPaiDataInfo(cinemaId,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String paiDataString = body.string();
                        callFilmPaiDataBack.getFilmPaiDataResult(paiDataString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}

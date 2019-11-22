package com.bw.movie.model;

import com.bw.movie.contract.SelectLocationContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SelectLocationModel implements SelectLocationContract.SelectLocationModel{

    @Override
    public void requestSelectTopData(int movieId, CallSelectTopBack callSelectTopBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String topString = body.string();
                        callSelectTopBack.getSelectTopResult(topString);
                    }
                });
    }

    @Override
    public void requestSelectLeftData(CallSelectLeftBack callSelectLeftBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainLocationInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String locationString = body.string();
                        callSelectLeftBack.getSelectLeftResult(locationString);
                    }
                });
    }

    @Override
    public void requestSelectRightData(int regionId, int movieId, int count, int page, CallSelectRightBack callSelectRightBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmLocaDetailCinemaInfo(movieId,regionId,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String locationreight = body.string();
                        callSelectRightBack.getSelectRightResult(locationreight);
                    }
                });
    }

    @Override
    public void requestSelectTimeData(CallSelectTimeBack callSelectTimeBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmDetailTimeInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String timeString = body.string();
                        callSelectTimeBack.getSelectTimeResult(timeString);
                    }
                });
    }

    @Override
    public void requestSelectTimeCinemaData(int movieId, String data, int count, int page, CallSelectTimeCinemaBack callSelectTimeCinemaBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmDetailTimeCinemaInfo(movieId,data,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String timeCinemaString = body.string();
                        callSelectTimeCinemaBack.getSelectTimeCinemaResult(timeCinemaString);
                    }
                });
    }

    @Override
    public void requestSelectPriceData(int movieId, int count, int page, CallSelectPriceBack callSelectPriceBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmDetailSmallPriceInfo(movieId,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String priceString = body.string();
                        callSelectPriceBack.getSelectPriceResult(priceString);
                    }
                });
    }

}

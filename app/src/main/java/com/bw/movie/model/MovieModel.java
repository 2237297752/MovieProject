package com.bw.movie.model;

import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MovieModel implements MovieContract.MovieModel{
    @Override
    public void requestRecommendData(int count, int page, final CallRecommendBack callRecommendBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainRecommendInfo(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String recommendString = body.string();
                        callRecommendBack.getRecommendResult(recommendString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestNearbyData(int userId, String sessionId, int page, int count, final CallNearbyBack callNearbyBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainNearbyInfo(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String nearbyString = body.string();
                        callNearbyBack.getNearbyResult(nearbyString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestDistrictData(final CallDistrictBack callDistrictBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainLocationInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String locationString = body.string();
                        callDistrictBack.getDistrictResult(locationString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestDistrictCinemaData(int regionId, final CallDistrictCinemaBack callDistrictCinemaBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainLocationCinemaInfo(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String locationCinemaString = body.string();
                        callDistrictCinemaBack.getDistrictCinemaResult(locationCinemaString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}

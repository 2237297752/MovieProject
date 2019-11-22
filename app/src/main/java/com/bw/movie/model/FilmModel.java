package com.bw.movie.model;

import com.bw.movie.contract.FilmContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FilmModel implements FilmContract.FilmModel{

    @Override
    public void requestBannerData(final CallBannerBack callBannerBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainBannerInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String bannerString = body.string();
                        callBannerBack.getBannerResult(bannerString);
                    }
                });
    }

    @Override
    public void requestPlayingData(int count, int page, final CallPlayingBack callPlayingBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainPlayingInfo(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String playingString = body.string();
                        callPlayingBack.getPlayingResult(playingString);
                    }
                });
    }

    @Override
    public void requestWillData(int count, int page, int userId, String sessionId, CallWillBack callWillBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainWillInfo(count,page,userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String willString = body.string();
                        callWillBack.getWillResult(willString);
                    }
                });
    }

    @Override
    public void requestHotData(int count, int page, CallHotBack callHotBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainHotInfo(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String hotString = body.string();
                        callHotBack.getHotResult(hotString);
                    }
                });
    }

    @Override
    public void requestBtnyuyueData(int userId, String sessionId, int movieId, CallBtnyuyueBack callBtnyuyueBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainYuYueFilmInfo(userId,sessionId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String yuyueFilmString = body.string();
                        callBtnyuyueBack.getBtnyuyueResult(yuyueFilmString);
                    }
                });
    }
}

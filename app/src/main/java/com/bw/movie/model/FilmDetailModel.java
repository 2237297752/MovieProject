package com.bw.movie.model;

import android.support.v7.widget.GridLayoutManager;

import com.bw.movie.R;
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FilmDetailModel implements FilmDeatilContract.FilmDetailModel {

    @Override
    public void requestSuggestData(int movieId, CallsuggestBack callsuggestBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String filmdetailString = body.string();
                        callsuggestBack.getsuggestResult(filmdetailString);
                    }
                });
    }

    @Override
    public void requestHeraldData(int movieId, CallheraldBack callheraldBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String videoString = body.string();
                        callheraldBack.getheraldResult(videoString);
                    }
                });

    }

    @Override
    public void requestStillData(int movieId, CallstillBack callstillBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String stillString = body.string();
                        callstillBack.getstillResult(stillString);
                    }
                });
    }

    @Override
    public void requestCineCimsData(int movieId, int count, int page, CallCineCimsBack callCineCimsBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainCinecismInfo(movieId, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String cinecismString = body.string();
                        callCineCimsBack.getCineCimsResult(cinecismString);
                    }
                });
    }
}

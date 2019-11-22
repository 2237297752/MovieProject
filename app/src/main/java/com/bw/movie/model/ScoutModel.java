package com.bw.movie.model;

import android.support.v7.widget.GridLayoutManager;

import com.bw.movie.R;
import com.bw.movie.contract.ScoutContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ScoutModel implements ScoutContract.ScoutModel{

    @Override
    public void requestScoutData(String keyword, int count, int page, CallScoutBack callScoutBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainScoutInfo(keyword,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String scoutString = body.string();
                        callScoutBack.getScoutResult(scoutString);
                    }
                });
    }
}


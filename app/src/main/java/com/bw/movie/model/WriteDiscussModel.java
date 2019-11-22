package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.contract.WriteDiscussContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class WriteDiscussModel implements WriteDiscussContract.WriteDiscussModel {

    @Override
    public void requestWriteFilmData(int userId, String sessionId, int movieId, String write_film, double douscore, CallWriteFilmBack callWriteFilmBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainAddWriteFilmInfo(userId,sessionId,movieId,write_film,douscore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String writeFilmString = body.string();
                        Log.i("writaaa", "写评论："+writeFilmString);
                        callWriteFilmBack.getWriteFilmResult(writeFilmString);
                    }
                });
    }
}

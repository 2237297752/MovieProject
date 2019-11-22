package com.bw.movie.model;

import com.bw.movie.contract.PersonDetailContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public class PersonDetailModel implements PersonDetailContract.PersonDetailModel {
    @Override
    public void requestPersonDetailTouXiangData(int userId, String sessionId, List<MultipartBody.Part> image, CallPersonDetailTouXiangBack callPersonDetailTouXiangBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainPersonDetailTouXiangInfo(userId,sessionId,image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String touXiangString = body.string();
                        callPersonDetailTouXiangBack.getPersonDetailTouXiangResult(touXiangString);
                    }
                });
    }
}

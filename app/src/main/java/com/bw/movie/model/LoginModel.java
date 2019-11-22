package com.bw.movie.model;

import com.bw.movie.contract.LoginContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginModel implements LoginContract.LoginModel {
    @Override
    public void requestLoginData(String useEmail, String usePwd, final CallLoginBack callLoginBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainLoginInfo(useEmail,usePwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String loginString = responseBody.string();
                        callLoginBack.getLoginResult(loginString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}

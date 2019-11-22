package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.contract.RegisterContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RegisterModel implements RegisterContract.RegistModel {
    @Override
    public void requestRegistData(String nickNameRegist, String pwdRegist, String captch, String codeRegist, final CallRegistBack callRegistBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainRegistInfo(nickNameRegist,pwdRegist,captch,codeRegist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String registString = responseBody.string();
                        callRegistBack.getRegistResult(registString);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestCaptchData(String captch, final CallCaptchBack callCaptchBack) {
        Log.i("aaaaa","requestCaptchData"+captch);
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.sendOutEmailCode(captch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String stringCode = responseBody.string();
                        callCaptchBack.getCaptchResult(stringCode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}

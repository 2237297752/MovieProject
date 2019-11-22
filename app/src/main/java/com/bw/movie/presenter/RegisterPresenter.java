package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.model.RegisterModel;

public class RegisterPresenter<V extends RegisterContract.RegistView> extends BasePresenter<V> {

    private final RegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }
    public void requestRegistInfo(String nickNameRegist,String pwdRegist,String captch,String codeRegist){
        model.requestRegistData(nickNameRegist, pwdRegist, captch, codeRegist, new RegisterContract.RegistModel.CallRegistBack() {
            @Override
            public void getRegistResult(String registString) {
                getView().getCaptchaView(registString);
            }
        });
    }
    public void requestCaptchInfo(String captch){
        model.requestCaptchData(captch, new RegisterContract.RegistModel.CallCaptchBack() {
            @Override
            public void getCaptchResult(String captchString) {
                getView().getCaptchaView(captchString);
            }
        });
    }
}

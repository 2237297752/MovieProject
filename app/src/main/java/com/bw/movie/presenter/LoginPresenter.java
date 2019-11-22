package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.LoginContract;
import com.bw.movie.model.LoginModel;

public class LoginPresenter<V extends LoginContract.LoginView> extends BasePresenter<V> {


    private final LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }
    public void requestLoginInfo(String useEmail,String usePwd){
        model.requestLoginData(useEmail, usePwd, new LoginContract.LoginModel.CallLoginBack() {
            @Override
            public void getLoginResult(String loginString) {
                getView().getLoginView(loginString);
            }
        });
    }
}

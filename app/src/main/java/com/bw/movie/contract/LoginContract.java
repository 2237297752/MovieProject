package com.bw.movie.contract;

public interface LoginContract {
    interface LoginView{
        void getLoginView(String loginString);
    }
    interface LoginModel{
        void requestLoginData(String useEmail,String userPwd,CallLoginBack callLoginBack);
        interface CallLoginBack{
            void getLoginResult(String loginString);
        }
    }

}

package com.bw.movie.contract;

public interface RegisterContract {
    interface RegistView{
        void getRegisView(String registString);
        void getCaptchaView(String captchString);
    }
    interface RegistModel{
        void requestRegistData(String nickNameRegist,String pwdRegist,String captch,String codeRegist,CallRegistBack callRegistBack);
        interface CallRegistBack{
            void getRegistResult(String registString);
        }
        void requestCaptchData(String captch,CallCaptchBack callCaptchBack);
        interface CallCaptchBack{
            void getCaptchResult(String captchString);
        }
    }
}

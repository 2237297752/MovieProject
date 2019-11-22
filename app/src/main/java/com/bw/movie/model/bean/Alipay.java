package com.bw.movie.model.bean;

public class Alipay {

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018080760951276&biz_content=%7B%22out_trade_no%22%3A%2220190905200356052%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.12%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiTest%2FaliPay%2FmovieNotification&sign=N%2BULehe4wmnwvz%2FUR5umMWExVbmSkEsF1hPfiVfaVtXprYIeUh48wBejezWQCLZtaBPTb3CXS9UQNX2PBoXKL3LY91mHz0YV7DieMXiArIxBY4Mo113rFsXvpFMb%2F7JoeHCGnt2WMT%2FUI9Vc4yDFT2P9wUp%2FIn%2FazS4MKRTkQiW%2BE%2B811XsTBiuorhRv3LMoGMXLljHEbvJA24OQKpaLDM5TqJLSjdqqO4vYicKtJ%2BZlFQRZPrjNo%2BCvky2bneUgXmzYM1DfveBzHbZX3TALs%2BDhBjC3WzcAwEa7fwAV%2FvUvOLWOo4Ge%2FqXsr1ZuIZWoyTx3lBK4OdeLIPD6VAQlCA%3D%3D&sign_type=RSA2&timestamp=2019-09-05+20%3A05%3A41&version=1.0
     * message : ok
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

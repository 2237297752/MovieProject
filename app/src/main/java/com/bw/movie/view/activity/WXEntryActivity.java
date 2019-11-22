package com.bw.movie.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.model.bean.WeChatBean;
import com.bw.movie.utils.RetrofitUtils;
import com.bw.movie.view.App;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        App.api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int errCode = baseResp.errCode;
        if(errCode==0){
            String code = ((SendAuth.Resp) baseResp).code;
            RetrofitUtils.getInstance().createService(ApiService.class)
                    .weChatInfo(code)
                    .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            String weChatData = responseBody.string();
                            Gson gson = new Gson();
                            WeChatBean weChatBean = gson.fromJson(weChatData, WeChatBean.class);
                            if(weChatBean.getStatus().equals("0000")){
                                Toast.makeText(WXEntryActivity.this, weChatBean.getMessage(), Toast.LENGTH_SHORT).show();
                                SharedPreferences user = getSharedPreferences("config", MODE_PRIVATE);
                                SharedPreferences.Editor edit01 = user.edit();
                                String headPic = weChatBean.getResult().getUserInfo().getHeadPic();
                                String nickName = weChatBean.getResult().getUserInfo().getNickName();
                                int userId = weChatBean.getResult().getUserId();
                                String sessionId = weChatBean.getResult().getSessionId();
                                int sex = weChatBean.getResult().getUserInfo().getSex();
                                long lastLoginTime = weChatBean.getResult().getUserInfo().getLastLoginTime();
                                edit01.putString("nickName", nickName).commit();
                                edit01.putString("headPic", headPic).commit();
                                edit01.putInt("userId", userId).commit();
                                edit01.putInt("sex", sex).commit();
                                edit01.putString("sessionId", sessionId).commit();
                                edit01.putString("lastLoginTime", String.valueOf(lastLoginTime)).commit();
                                startActivity(new Intent(WXEntryActivity.this, HomeActivity.class));
                                finish();
                            }else if ("1001".equals(weChatBean.getStatus())){
                                Toast.makeText(WXEntryActivity.this, weChatBean.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
        }
    }
}

package com.bw.movie.view;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class App extends Application {

    private static final String APP_ID = "wxb3852e6a6b7d9516";
    public static IWXAPI api;
    public static App sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Fresco.initialize(this);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                api.registerApp(APP_ID);
            }
        },new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }
    public static App getsAppContext(){
        return sContext;
    }
}

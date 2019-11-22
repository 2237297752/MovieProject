package com.bw.movie.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.service.DownloadIntentService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.updata_image_faihui)
    ImageView updataImageFaihui;
    @BindView(R.id.update_btn_updatenow)
    Button updateBtnUpdatenow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.updata_image_faihui, R.id.update_btn_updatenow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updata_image_faihui:
                finish();
                break;
            case R.id.update_btn_updatenow:
                if (isServiceRunning(DownloadIntentService.class.getName())) {
                    Toast.makeText(UpdateActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
                    return;
                }
                //String downloadUrl = http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk;
                String downloadUrl = "/media/movie.apk";
                Intent intent = new Intent(UpdateActivity.this, DownloadIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putString("download_url", downloadUrl);
                bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
                intent.putExtras(bundle);
                startService(intent);
                break;
        }
    }
    /**
     * 用来判断服务是否运行.
     *
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    private boolean isServiceRunning(String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}

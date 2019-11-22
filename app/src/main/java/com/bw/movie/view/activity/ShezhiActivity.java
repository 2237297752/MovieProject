package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.utils.CleanDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShezhiActivity extends AppCompatActivity {
    private static final int DOWNLOADAPK_ID = 10;
    @BindView(R.id.mysettings_fanhui)
    ImageView mysettingsFanhui;
    @BindView(R.id.mysetting_clear)
    TextView mysettingClear;
    @BindView(R.id.mysetting_number)
    TextView mysettingNumber;
    @BindView(R.id.mysetting_gengxin)
    LinearLayout mysettingGengxin;
    @BindView(R.id.mysetting_chongzhipassword)
    LinearLayout mysettingChongzhipassword;
    @BindView(R.id.btn_mysettingtuichu)
    Button btnMysettingtuichu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mysettings_fanhui, R.id.mysetting_clear, R.id.mysetting_gengxin, R.id.mysetting_chongzhipassword, R.id.btn_mysettingtuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mysettings_fanhui:
                finish();
                break;
            case R.id.mysetting_clear:
                CleanDataUtils.clearAllCache(ShezhiActivity.this);
                mysettingNumber.setText(0 + "M");
                break;
            case R.id.mysetting_gengxin:
                startActivity(new Intent(ShezhiActivity.this, UpdateActivity.class));
                break;
            case R.id.mysetting_chongzhipassword:
                break;
            case R.id.btn_mysettingtuichu:
                startActivity(new Intent(ShezhiActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

}

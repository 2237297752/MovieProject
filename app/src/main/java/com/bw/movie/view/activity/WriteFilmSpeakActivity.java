package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.WriteDiscussContract;
import com.bw.movie.model.bean.AddWriteFilmBean;
import com.bw.movie.presenter.WriteDiscussPresenter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFilmSpeakActivity extends BaseActivity<WriteDiscussContract.WriteDiscussView, WriteDiscussPresenter<WriteDiscussContract.WriteDiscussView>> implements WriteDiscussContract.WriteDiscussView {

    @BindView(R.id.writefilm_fanhui)
    ImageView writefilmFanhui;
    @BindView(R.id.name_writefilm)
    TextView nameWritefilm;
    @BindView(R.id.write_ratfenfilm)
    RatingBar writeRatfenfilm;
    @BindView(R.id.write_yourfilmpinglun)
    EditText writeYourfilmpinglun;
    @BindView(R.id.btn_writetijaiofilm)
    Button btnWritetijaiofilm;

    @Override
    public void getWriteFilmView(String writeFilmString) {
        Gson gson = new Gson();
        AddWriteFilmBean addWriteFilmBean = gson.fromJson(writeFilmString, AddWriteFilmBean.class);
        if ("0000".equals(addWriteFilmBean.getStatus())){
            Toast.makeText(this, addWriteFilmBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "评论失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        SharedPreferences nsp = getSharedPreferences("config", MODE_PRIVATE);
        String writename = nsp.getString("writename", "");
        nameWritefilm.setText(writename);
    }

    @Override
    protected WriteDiscussPresenter<WriteDiscussContract.WriteDiscussView> createPresenter() {
        return new WriteDiscussPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_writefilmspeak;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.writefilm_fanhui, R.id.btn_writetijaiofilm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.writefilm_fanhui:
                finish();
                break;
            case R.id.btn_writetijaiofilm:
                SharedPreferences wmsp = getSharedPreferences("config", MODE_PRIVATE);
                int userId = wmsp.getInt("userId", 0);
                String sessionId = wmsp.getString("sessionId", "");
                int movieId = wmsp.getInt("movieId", 0);
                String write_film = writeYourfilmpinglun.getText().toString();
                String score = wmsp.getString("score", "");
                Double douscore = Double.valueOf(score);
                mPresenter.requestWriteFilmInfo(userId,sessionId,movieId,write_film,douscore);
                break;
        }
    }
}

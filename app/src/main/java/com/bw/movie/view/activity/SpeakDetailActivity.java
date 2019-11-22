package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.model.bean.CinecismBean;
import com.bw.movie.utils.RetrofitUtils;
import com.bw.movie.view.adapter.FilmCinecismAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
//查看评论页
public class SpeakDetailActivity extends AppCompatActivity {

    @BindView(R.id.speakdetail_fanhui)
    ImageView speakdetailFanhui;
    @BindView(R.id.recycle_speakdetail)
    RecyclerView recycleSpeakdetail;
    @BindView(R.id.speak_write)
    TextView speakWrite;
    private int count = 50;
    private int page = 1;
    private FilmCinecismAdapter filmCinecismAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakdetail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainCinecismInfo(movieId, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String speakString = body.string();
                        Gson gson = new Gson();
                        CinecismBean cinecismBean = gson.fromJson(speakString, CinecismBean.class);
                        ArrayList<CinecismBean.ResultBean> cinecismBeanResult = (ArrayList<CinecismBean.ResultBean>) cinecismBean.getResult();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SpeakDetailActivity.this, LinearLayoutManager.VERTICAL, false);
                        recycleSpeakdetail.setLayoutManager(linearLayoutManager);
                        filmCinecismAdapter = new FilmCinecismAdapter(R.layout.item_filmcinecismadapter, cinecismBeanResult);
                        recycleSpeakdetail.setAdapter(filmCinecismAdapter);
                    }
                });
    }

    @OnClick({R.id.speakdetail_fanhui, R.id.speak_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.speakdetail_fanhui:
                finish();
                break;
            case R.id.speak_write:
//                startActivity(new Intent(SpeakDetailActivity.this, WriteFilmSpeakActivity.class));
                break;
        }
    }
}

package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.FilmDetailHomeContract;

import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.model.bean.ForFilmFollwBean;
import com.bw.movie.model.bean.ForFilmUnFllowBean;
import com.bw.movie.presenter.FilmDetailHomePresenter;
import com.bw.movie.view.adapter.TabEntryAdapter;
import com.bw.movie.view.fragment.FilmcinecismFragment;
import com.bw.movie.view.fragment.FilmheraldFragment;
import com.bw.movie.view.fragment.FilmstillFragment;
import com.bw.movie.view.fragment.FilmsuggestFragment;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmDeatilActivity extends BaseActivity<FilmDetailHomeContract.FilmDetailHomeView, FilmDetailHomePresenter<FilmDetailHomeContract.FilmDetailHomeView>> implements FilmDetailHomeContract.FilmDetailHomeView {

    @BindView(R.id.back_filmdetail)
    ImageView backFilmdetail;
    @BindView(R.id.img_filmdetail)
    ImageView imgFilmdetail;
    @BindView(R.id.score_filmdetail)
    TextView scoreFilmdetail;
    @BindView(R.id.wether_filmdetail)
    TextView wetherFilmdetail;
    @BindView(R.id.name_filmdetail)
    TextView nameFilmdetail;
    @BindView(R.id.type_filmdetail)
    TextView typeFilmdetail;
    @BindView(R.id.time_filmdetail)
    TextView timeFilmdetail;
    @BindView(R.id.data_filmdetail)
    TextView dataFilmdetail;
    @BindView(R.id.location_filmdetail)
    TextView locationFilmdetail;
    @BindView(R.id.filmdetailtab_layout)
    TabLayout filmdetailtabLayout;
    @BindView(R.id.filmdetailview_pager)
    ViewPager filmdetailviewPager;
    @BindView(R.id.filmdetail_write)
    Button filmdetailWrite;
    @BindView(R.id.filmdetail_xuan)
    Button filmdetailXuan;
    @BindView(R.id.dt_xin)
    CheckBox dtXin;
    @BindView(R.id.dt_guanzhu)
    TextView dtGuanzhu;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> fragmes = new ArrayList<>();
    private FilmDetailBean.ResultBean filmDetailBeanResult;
    private String dateStr;

    @Override
    public void getFilmDetailHomeView(String filmdetailString) {
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(filmdetailString, FilmDetailBean.class);
        filmDetailBeanResult = filmDetailBean.getResult();
        Glide.with(FilmDeatilActivity.this).load(filmDetailBeanResult.getImageUrl()).into(imgFilmdetail);
        nameFilmdetail.setText(filmDetailBeanResult.getName());
        typeFilmdetail.setText(filmDetailBeanResult.getMovieType());
        timeFilmdetail.setText(filmDetailBeanResult.getDuration());
        locationFilmdetail.setText(filmDetailBeanResult.getPlaceOrigin() + "上映");
        scoreFilmdetail.setText(filmDetailBeanResult.getScore() + "" + "分");
        wetherFilmdetail.setText(filmDetailBeanResult.getWhetherFollow() + "" + "万条");

        Date date = new Date(filmDetailBeanResult.getReleaseTime());
        dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        dataFilmdetail.setText(dateStr);
    }

    @Override
    public void getForFilmDetailfollowHomeView(String forFilmFllowString) {
        Gson gson = new Gson();
        ForFilmFollwBean forFilmFollwBean = gson.fromJson(forFilmFllowString, ForFilmFollwBean.class);
        if ("0000".equals(forFilmFollwBean.getStatus())) {
            dtGuanzhu.setText(forFilmFollwBean.getMessage());
        }
    }

    @Override
    public void getForFilmDetailunfollowHomeView(String forFilmUnFollow) {
        Gson gson = new Gson();
        ForFilmUnFllowBean forFilmUnFllowBean = gson.fromJson(forFilmUnFollow, ForFilmUnFllowBean.class);
        if ("0000".equals(forFilmUnFllowBean.getStatus())) {
            dtGuanzhu.setText(forFilmUnFllowBean.getMessage());
        }
    }

    @Override
    protected void initData() {
        list.add("介绍");
        list.add("预告");
        list.add("剧照");
        list.add("影评");
        fragmes.add(new FilmsuggestFragment());
        fragmes.add(new FilmheraldFragment());
        fragmes.add(new FilmstillFragment());
        fragmes.add(new FilmcinecismFragment());
        TabEntryAdapter tabEntryAdapter = new TabEntryAdapter(getSupportFragmentManager(), list, fragmes);
        filmdetailviewPager.setAdapter(tabEntryAdapter);
        filmdetailtabLayout.setupWithViewPager(filmdetailviewPager);

        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        mPresenter.requestFilmDetailHomeInfo(movieId);
    }

    @Override
    protected FilmDetailHomePresenter<FilmDetailHomeContract.FilmDetailHomeView> createPresenter() {
        return new FilmDetailHomePresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_filmdeatil;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dt_xin, R.id.filmdetail_write, R.id.filmdetail_xuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dt_xin:
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                int userId = sp.getInt("userId", 0);
                String sessionId = sp.getString("sessionId", "");
                int movieId = sp.getInt("movieId", 0);
                if (dtXin.isChecked()){
                    dtXin.setChecked(true);
                    mPresenter.requestForFilmDetailfollowHomeInfo(userId, sessionId, movieId);
                }else {
                    dtXin.setChecked(false);
                    mPresenter.requestForFilmDetailunfollowHomeInfo(userId, sessionId, movieId);
                }
                break;
            case R.id.filmdetail_write:
                startActivity(new Intent(FilmDeatilActivity.this, WriteFilmSpeakActivity.class));
                break;
            case R.id.filmdetail_xuan:
                startActivity(new Intent(FilmDeatilActivity.this, SelectFilmSeatActivity.class));
                break;
        }
    }
}

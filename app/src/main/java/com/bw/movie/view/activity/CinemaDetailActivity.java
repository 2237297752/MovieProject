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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.CinemaDetailContract;
import com.bw.movie.model.bean.CinemaDetailBean;
import com.bw.movie.model.bean.CinemaGuanZhuBean;
import com.bw.movie.model.bean.WeiGuanzhuBean;
import com.bw.movie.presenter.CinemaDetailPresenter;
import com.bw.movie.view.adapter.TabEntryAdapter;
import com.bw.movie.view.fragment.CinemaDetailFrag;
import com.bw.movie.view.fragment.CinemaDetailSpeakFrag;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaDetailActivity extends BaseActivity<CinemaDetailContract.CinemaDetailView, CinemaDetailPresenter<CinemaDetailContract.CinemaDetailView>> implements CinemaDetailContract.CinemaDetailView {

    @BindView(R.id.cinemadetail_fanhui)
    ImageView cinemadetailFanhui;
    @BindView(R.id.cinemadetail_locationname)
    TextView cinemadetailLocationname;
    @BindView(R.id.dtcinema_xin)
    CheckBox dtcinemaXin;
    @BindView(R.id.tohere_cinemadetail)
    LinearLayout tohereCinemadetail;
    @BindView(R.id.tablayout_cinemadetail)
    TabLayout tablayoutCinemadetail;
    @BindView(R.id.viewpager_cinemadetail)
    ViewPager viewpagerCinemadetail;
    @BindView(R.id.btn_cinemadetaildata)
    Button btnCinemadetaildata;
    @BindView(R.id.cinemadetail_yanjin)
    TextView cinemadetailYanjin;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> fragmes = new ArrayList<>();
    private int cinemaId;
    private int userId;
    private String sessionId;

    @Override
    public void getCinemaDetailTopView(String cinemaDetailString) {
        Gson gson = new Gson();
        CinemaDetailBean cinemaDetailBean = gson.fromJson(cinemaDetailString, CinemaDetailBean.class);
        CinemaDetailBean.ResultBean cinemaDetailBeanResult = cinemaDetailBean.getResult();
        cinemadetailLocationname.setText(cinemaDetailBeanResult.getName());
        cinemadetailYanjin.setText(cinemaDetailBeanResult.getLabel());
        cinemadetailYanjin.setText(cinemaDetailBeanResult.getLabel());
    }

    @Override
    public void getCinemaDetailSpeakView(String cinemadetailSpeakString) {

    }

    @Override
    public void getCinemaDetailGuanzhuView(String gunazhuString) {
        Gson gson = new Gson();
        CinemaGuanZhuBean cinemaGuanZhuBean = gson.fromJson(gunazhuString, CinemaGuanZhuBean.class);
        if ("0000".equals(cinemaGuanZhuBean.getStatus())){
            Toast.makeText(this, cinemaGuanZhuBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCinemaDetailWeiGuanzhuView(String weiString) {
        Gson gson = new Gson();
        WeiGuanzhuBean weiGuanzhuBean = gson.fromJson(weiString, WeiGuanzhuBean.class);
        if ("0000".equals(weiGuanzhuBean.getStatus())){
            Toast.makeText(this, weiGuanzhuBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getFilmPaiDataView(String paiDataString) {

    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("config", 0);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", "");
        Intent intent = getIntent();
        cinemaId = intent.getIntExtra("cinemaId", 0);
        mPresenter.requestCinemaDetailTopInfo(userId, sessionId, cinemaId);

        list.add("影院详情");
        list.add("影院评价");
        fragmes.add(new CinemaDetailFrag());
        fragmes.add(new CinemaDetailSpeakFrag());
        TabEntryAdapter tabEntryAdapter = new TabEntryAdapter(getSupportFragmentManager(), list, fragmes);
        viewpagerCinemadetail.setAdapter(tabEntryAdapter);
        tablayoutCinemadetail.setupWithViewPager(viewpagerCinemadetail);
    }

    @Override
    protected CinemaDetailPresenter<CinemaDetailContract.CinemaDetailView> createPresenter() {
        return new CinemaDetailPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cinemadetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cinemadetail_fanhui, R.id.dtcinema_xin, R.id.tohere_cinemadetail, R.id.btn_cinemadetaildata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cinemadetail_fanhui:
                finish();
                break;
            case R.id.dtcinema_xin:
                if (dtcinemaXin.isChecked()){
                    dtcinemaXin.setChecked(true);
                    mPresenter.requestCinemaDetailGuanzhuInfo(userId,sessionId,cinemaId);
                }else {
                    dtcinemaXin.setChecked(false);
                    mPresenter.requestCinemaDetailWeiGuanzhuInfo(userId,sessionId,cinemaId);
                }
                break;
            case R.id.tohere_cinemadetail:
                break;
            case R.id.btn_cinemadetaildata:
                Intent intent = new Intent(CinemaDetailActivity.this, PaiDataActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
                break;
        }
    }
}

package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.SelectLocationContract;
import com.bw.movie.model.bean.FilmDetailTimeBean;
import com.bw.movie.presenter.SelectLocationPresenter;
import com.bw.movie.view.adapter.TabEntryAdapter;
import com.bw.movie.view.fragment.PaiDataFrag;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaiDataActivity extends BaseActivity<SelectLocationContract.SelectLocationView, SelectLocationPresenter<SelectLocationContract.SelectLocationView>> implements SelectLocationContract.SelectLocationView {

    @BindView(R.id.paidata_fanhui)
    ImageView paidataFanhui;
    @BindView(R.id.tablayout_paidata)
    TabLayout tablayoutPaidata;
    @BindView(R.id.viewpager_paidata)
    ViewPager viewpagerPaidata;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> fragmes = new ArrayList<>();

    @Override
    public void getSelectTopView(String topString) {

    }

    @Override
    public void getSelectLeftView(String locationString) {

    }

    @Override
    public void getSelectRightView(String locationreight) {

    }

    @Override
    public void getSelectTimeView(String timeString) {
        Gson gson = new Gson();
        FilmDetailTimeBean filmDetailTimeBean = gson.fromJson(timeString, FilmDetailTimeBean.class);
        List<String> timeBeanResult = filmDetailTimeBean.getResult();
        for (int i = 0; i < 5; i++) {
            list.add(timeBeanResult.get(i));
            fragmes.add(new PaiDataFrag());
            TabEntryAdapter tabEntryAdapter = new TabEntryAdapter(getSupportFragmentManager(), list, fragmes);
            viewpagerPaidata.setAdapter(tabEntryAdapter);
            tablayoutPaidata.setupWithViewPager(viewpagerPaidata);
        }
    }

    @Override
    public void getSelectTimeCinemaView(String timeCinemaString) {

    }

    @Override
    public void getSelectPriceView(String priceString) {

    }

    @Override
    protected void initData() {
        mPresenter.requestSelectTimeInfo();
    }

    @Override
    protected SelectLocationPresenter<SelectLocationContract.SelectLocationView> createPresenter() {
        return new SelectLocationPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_paidata;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.paidata_fanhui)
    public void onViewClicked() {
        finish();
    }
}

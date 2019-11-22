package com.bw.movie.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.MovieDetailContract;
import com.bw.movie.model.bean.CinemaDetailSpeaakBean;
import com.bw.movie.presenter.MovieDetailPresenter;
import com.bw.movie.view.adapter.CinemaDetailSpeakAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CinemaDetailSpeakFrag extends BaseFragment<MovieDetailContract.MovieDetailView, MovieDetailPresenter<MovieDetailContract.MovieDetailView>> implements MovieDetailContract.MovieDetailView {


    @BindView(R.id.recycle_cinemadetailspeakfrag)
    RecyclerView recycleCinemadetailspeakfrag;
    Unbinder unbinder;
    private int count=30;
    private int page=1;
    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", 0);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        Intent intent = getActivity().getIntent();
        int cinemaId = intent.getIntExtra("cinemaId", 0);
        fPresenter.requestCinemaDetailSpeakInfo(userId, sessionId, cinemaId, count, page);
    }

    @Override
    protected MovieDetailPresenter<MovieDetailContract.MovieDetailView> createFragmentPresenter() {
        return new MovieDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.cinemadetailspeak_frag;
    }

    @Override
    public void getCinemaDetailTopView(String cinemaDetailString) {

    }

    @Override
    public void getCinemaDetailSpeakView(String cinemadetailSpeakString) {
        Gson gson = new Gson();
        CinemaDetailSpeaakBean cinemaDetailSpeaakBean = gson.fromJson(cinemadetailSpeakString, CinemaDetailSpeaakBean.class);
        ArrayList<CinemaDetailSpeaakBean.ResultBean> cinemaDetailSpeaakBeanResult = (ArrayList<CinemaDetailSpeaakBean.ResultBean>) cinemaDetailSpeaakBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleCinemadetailspeakfrag.setLayoutManager(manager);
        CinemaDetailSpeakAdapter cinemaDetailSpeakAdapter = new CinemaDetailSpeakAdapter(R.layout.item_cinemadetailspeakadapter, cinemaDetailSpeaakBeanResult);
        recycleCinemadetailspeakfrag.setAdapter(cinemaDetailSpeakAdapter);
    }

    @Override
    public void getCinemaDetailGuanzhuView(String gunazhuString) {

    }

    @Override
    public void getCinemaDetailWeiGuanzhuView(String weiString) {

    }

    @Override
    public void getFilmPaiDataView(String paiDataString) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

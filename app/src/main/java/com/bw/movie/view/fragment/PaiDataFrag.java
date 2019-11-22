package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.CinemaDetailContract;
import com.bw.movie.model.bean.PaiDataBean;
import com.bw.movie.presenter.CinemaDetailPresenter;
import com.bw.movie.view.adapter.PaiDataFragAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PaiDataFrag extends BaseFragment<CinemaDetailContract.CinemaDetailView, CinemaDetailPresenter<CinemaDetailContract.CinemaDetailView>> implements CinemaDetailContract.CinemaDetailView {
    @BindView(R.id.recycle_paidatafrag)
    RecyclerView recyclePaidatafrag;
    Unbinder unbinder;
    private int count=20;
    private int page=1;

    @Override
    public void getCinemaDetailTopView(String cinemaDetailString) {

    }

    @Override
    public void getCinemaDetailSpeakView(String cinemadetailSpeakString) {

    }

    @Override
    public void getCinemaDetailGuanzhuView(String gunazhuString) {

    }

    @Override
    public void getCinemaDetailWeiGuanzhuView(String weiString) {

    }

    @Override
    public void getFilmPaiDataView(String paiDataString) {
        Gson gson = new Gson();
        PaiDataBean paiDataBean = gson.fromJson(paiDataString, PaiDataBean.class);
        ArrayList<PaiDataBean.ResultBean> paiDataBeanResult = (ArrayList<PaiDataBean.ResultBean>) paiDataBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclePaidatafrag.setLayoutManager(linearLayoutManager);
        PaiDataFragAdapter paiDataFragAdapter = new PaiDataFragAdapter(R.layout.item_paidatafragadapter, paiDataBeanResult);
        recyclePaidatafrag.setAdapter(paiDataFragAdapter);
    }

    @Override
    protected void initView() {
        Intent intent = getActivity().getIntent();
        int cinemaId = intent.getIntExtra("cinemaId", 0);
        fPresenter.requestFilmPaiDataInfo(cinemaId,count,page);
    }

    @Override
    protected CinemaDetailPresenter<CinemaDetailContract.CinemaDetailView> createFragmentPresenter() {
        return new CinemaDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.paidatafrag_layout;
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

package com.bw.movie.view.fragment;

import android.content.Context;
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
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.bean.NearbyBean;
import com.bw.movie.presenter.CinemaPresenter;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.view.activity.MovieDetailActivity;
import com.bw.movie.view.adapter.NearbyAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieNearbyFrag extends BaseFragment<MovieContract.MovieView, MoviePresenter<MovieContract.MovieView>> implements MovieContract.MovieView {

    @BindView(R.id.recycle_nearbyfrag)
    RecyclerView recycleNearbyfrag;
    Unbinder unbinder;
    private int page=1;
    private int count=10;
    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("cinfig", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        fPresenter.requestNearbyInfo(userId, sessionId, page, count);
    }

    @Override
    protected MoviePresenter<MovieContract.MovieView> createFragmentPresenter() {
        return new MoviePresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.nearby_frag;
    }

    @Override
    public void getRecommendView(String recommendString) {

    }

    @Override
    public void getNearbyView(String nearbyString) {
        recycleNearbyfrag.setHasFixedSize(true);
        recycleNearbyfrag.setNestedScrollingEnabled(false);
        recycleNearbyfrag.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recycleNearbyfrag.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        NearbyBean nearbyBean = gson.fromJson(nearbyString, NearbyBean.class);
        ArrayList<NearbyBean.ResultBean> nearbyBeanResult = (ArrayList<NearbyBean.ResultBean>) nearbyBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleNearbyfrag.setLayoutManager(linearLayoutManager);
        NearbyAdapter nearbyAdapter = new NearbyAdapter(R.layout.item_nearbyadapter, nearbyBeanResult);
        recycleNearbyfrag.setAdapter(nearbyAdapter);
        nearbyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = nearbyBeanResult.get(position).getId();
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("cinemaId", cinemaId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getDistrictView(String locationString) {

    }

    @Override
    public void getDistrictCinemaView(String locationCinemaString) {

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

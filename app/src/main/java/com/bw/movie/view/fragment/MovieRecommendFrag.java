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
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.bean.RecommendBean;
import com.bw.movie.presenter.CinemaPresenter;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.view.activity.MovieDetailActivity;
import com.bw.movie.view.adapter.RecommendAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieRecommendFrag extends BaseFragment<MovieContract.MovieView, MoviePresenter<MovieContract.MovieView>> implements MovieContract.MovieView {
    @BindView(R.id.recycle_recommend)
    RecyclerView recycleRecommend;
    Unbinder unbinder;
    private int count = 10;
    private int page = 1;

    @Override
    protected void initView() {
        fPresenter.requestRecommendInfo(count, page);
    }

    @Override
    protected MoviePresenter<MovieContract.MovieView> createFragmentPresenter() {
        return new MoviePresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.movie_recommendfrag;
    }

    @Override
    public void getRecommendView(String recommendString) {
        recycleRecommend.setHasFixedSize(true);
        recycleRecommend.setNestedScrollingEnabled(false);
        recycleRecommend.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycleRecommend.setRecycledViewPool(recycledViewPool);

        Gson gson = new Gson();
        RecommendBean recommendBean = gson.fromJson(recommendString, RecommendBean.class);
        ArrayList<RecommendBean.ResultBean> recommendBeanResult = (ArrayList<RecommendBean.ResultBean>) recommendBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleRecommend.setLayoutManager(manager);
        RecommendAdapter recommendAdapter = new RecommendAdapter(R.layout.item_recommend, recommendBeanResult);
        recycleRecommend.setAdapter(recommendAdapter);
        recommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = recommendBeanResult.get(position).getId();
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
            }
        });

    }

    @Override
    public void getNearbyView(String nearbyString) {

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

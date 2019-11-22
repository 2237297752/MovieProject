package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.MovieContract;
import com.bw.movie.model.bean.DistrictLocationBean;
import com.bw.movie.model.bean.DistrictLocationCinemaBean;
import com.bw.movie.presenter.MoviePresenter;
import com.bw.movie.view.adapter.DistrictLocationAdapter;
import com.bw.movie.view.adapter.DistrictLocationCinemaAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieDistrictFrag extends BaseFragment<MovieContract.MovieView, MoviePresenter<MovieContract.MovieView>> implements MovieContract.MovieView {

    @BindView(R.id.recycle_left)
    RecyclerView recycleLeft;
    @BindView(R.id.recycle_right)
    RecyclerView recycleRight;
    Unbinder unbinder;

    @Override
    protected void initView() {
        fPresenter.requestDistrictInfo();
    }

    @Override
    protected MoviePresenter<MovieContract.MovieView> createFragmentPresenter() {
        return new MoviePresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.district_frag;
    }

    @Override
    public void getRecommendView(String recommendString) {

    }

    @Override
    public void getNearbyView(String nearbyString) {

    }

    @Override
    public void getDistrictView(String locationString) {
        recycleLeft.setHasFixedSize(true);
        recycleLeft.setNestedScrollingEnabled(false);
        recycleLeft.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recycleLeft.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        DistrictLocationBean districtLocationBean = gson.fromJson(locationString, DistrictLocationBean.class);
        ArrayList<DistrictLocationBean.ResultBean> districtLocationBeanResult = (ArrayList<DistrictLocationBean.ResultBean>) districtLocationBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleLeft.setLayoutManager(linearLayoutManager);
        DistrictLocationAdapter districtLocationAdapter = new DistrictLocationAdapter(R.layout.item_districtlocationadapter, districtLocationBeanResult);
        recycleLeft.setAdapter(districtLocationAdapter);
        districtLocationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int regionId = districtLocationBeanResult.get(position).getRegionId();
                fPresenter.requestDistrictCinemaInfo(regionId);
            }
        });
    }

    @Override
    public void getDistrictCinemaView(String locationCinemaString) {
        recycleRight.setHasFixedSize(true);
        recycleRight.setNestedScrollingEnabled(false);
        recycleRight.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recycleRight.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        DistrictLocationCinemaBean districtLocationCinemaBean = gson.fromJson(locationCinemaString, DistrictLocationCinemaBean.class);
        ArrayList<DistrictLocationCinemaBean.ResultBean> locationCinemaBeanResult = (ArrayList<DistrictLocationCinemaBean.ResultBean>) districtLocationCinemaBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleRight.setLayoutManager(manager);
        DistrictLocationCinemaAdapter districtLocationCinemaAdapter = new DistrictLocationCinemaAdapter(R.layout.item_districtlocationcinemaadapter, locationCinemaBeanResult);
        recycleRight.setAdapter(districtLocationCinemaAdapter);
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

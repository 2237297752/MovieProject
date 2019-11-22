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
import android.widget.Button;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.FilmContract;
import com.bw.movie.model.bean.WillBean;
import com.bw.movie.model.bean.YuYueFilmBean;
import com.bw.movie.presenter.FilmPresenter;
import com.bw.movie.view.activity.FilmDeatilActivity;
import com.bw.movie.view.adapter.WillAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WillFrag extends BaseFragment<FilmContract.FilmView, FilmPresenter<FilmContract.FilmView>> implements FilmContract.FilmView {

    @BindView(R.id.tab_willrecycle)
    RecyclerView tabWillrecycle;
    Unbinder unbinder;
    private int page=1;
    private int count=20;

    @Override
    public void getBannerView(String bannerString) {

    }

    @Override
    public void getPlayingView(String playingString) {

    }

    @Override
    public void getWillView(String willString) {
        tabWillrecycle.setHasFixedSize(true);
        tabWillrecycle.setNestedScrollingEnabled(false);
        tabWillrecycle.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        tabWillrecycle.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        WillBean willBean = gson.fromJson(willString, WillBean.class);
        ArrayList<WillBean.ResultBean> willBeanResult = (ArrayList<WillBean.ResultBean>) willBean.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tabWillrecycle.setLayoutManager(layoutManager);

        WillAdapter willAdapter = new WillAdapter(R.layout.item_will, willBeanResult);
        tabWillrecycle.setAdapter(willAdapter);
        willAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = willBeanResult.get(position).getMovieId();
                String writename = willBeanResult.get(position).getName();
                double score = willBeanResult.get(position).getScore();
                SharedPreferences spwill = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                spwill.edit().putInt("movieId", movieId).commit();
                spwill.edit().putString("writename",writename).commit();
                spwill.edit().putString("score",String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), FilmDeatilActivity.class));
            }
        });
        willAdapter.setOnWillYuYueCallBack(new WillAdapter.OnWillYuYueCallBack() {
            @Override
            public void getOnWillYuYueCallBack(Button yuyue_will, int movieId) {
                yuyue_will.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        yuyue_will.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
                        yuyue_will.setText("已预约");
                        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                        int userId = sp.getInt("userId", 0);
                        String sessionId = sp.getString("sessionId", "");
                        fPresenter.requestBtnyuyueInfo(userId,sessionId,movieId);
                    }
                });
            }
        });
    }

    @Override
    public void getHotView(String hotString) {

    }

    @Override
    public void getBtnyuyueView(String yuyueFilmString) {
        Toast.makeText(getActivity(), yuyueFilmString.toString(), Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        YuYueFilmBean yuYueFilmBean = gson.fromJson(yuyueFilmString, YuYueFilmBean.class);
        if ("1001".equals(yuYueFilmBean.getStatus())){
            Toast.makeText(getActivity(), yuYueFilmBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        fPresenter.requestWillInfo(count, page, userId, sessionId);
    }

    @Override
    protected FilmPresenter<FilmContract.FilmView> createFragmentPresenter() {
        return new FilmPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.will_layout;
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

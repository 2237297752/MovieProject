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
import com.bw.movie.model.bean.PlayingBean;
import com.bw.movie.model.bean.YuYueFilmBean;
import com.bw.movie.presenter.FilmPresenter;
import com.bw.movie.view.activity.FilmDeatilActivity;
import com.bw.movie.view.activity.SelectFilmSeatActivity;
import com.bw.movie.view.adapter.PlayingAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlayingFrag extends BaseFragment<FilmContract.FilmView, FilmPresenter<FilmContract.FilmView>> implements FilmContract.FilmView {

    @BindView(R.id.tab_playingrecycle)
    RecyclerView tabPlayingrecycle;
    Unbinder unbinder;
    private int page=1;
    private int count=20;

    @Override
    public void getBannerView(String bannerString) {

    }

    @Override
    public void getPlayingView(String playingString) {
        tabPlayingrecycle.setHasFixedSize(true);
        tabPlayingrecycle.setNestedScrollingEnabled(false);
        tabPlayingrecycle.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        tabPlayingrecycle.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        PlayingBean playingBean = gson.fromJson(playingString, PlayingBean.class);
        ArrayList<PlayingBean.ResultBean> playingBeanResult = (ArrayList<PlayingBean.ResultBean>) playingBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tabPlayingrecycle.setLayoutManager(linearLayoutManager);
        PlayingAdapter playingAdapter = new PlayingAdapter(R.layout.item_tabplayingmore, playingBeanResult);
        tabPlayingrecycle.setAdapter(playingAdapter);
        playingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = playingBeanResult.get(position).getMovieId();
                String writename = playingBeanResult.get(position).getName();
                double score = playingBeanResult.get(position).getScore();
                SharedPreferences spplaying = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                spplaying.edit().putInt("movieId", movieId).commit();
                spplaying.edit().putString("writename",writename).commit();
                spplaying.edit().putString("score",String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), FilmDeatilActivity.class));
            }
        });
        playingAdapter.setPlayingInter(new PlayingAdapter.playingInter() {
            @Override
            public void getplayingInter(Button btn_payplaying) {
                btn_payplaying.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), SelectFilmSeatActivity.class));
                    }
                });
            }
        });
    }

    @Override
    public void getWillView(String willString) {

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
        fPresenter.requestPlayingInfo(count, page);
    }

    @Override
    protected FilmPresenter<FilmContract.FilmView> createFragmentPresenter() {
        return new FilmPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.playing_layout;
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

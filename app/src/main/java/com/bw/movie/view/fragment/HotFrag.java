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
import com.bw.movie.model.bean.HotBean;
import com.bw.movie.model.bean.YuYueFilmBean;
import com.bw.movie.presenter.FilmPresenter;
import com.bw.movie.view.activity.FilmDeatilActivity;
import com.bw.movie.view.activity.SelectFilmSeatActivity;
import com.bw.movie.view.adapter.HotTopAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotFrag extends BaseFragment<FilmContract.FilmView, FilmPresenter<FilmContract.FilmView>> implements FilmContract.FilmView {

    @BindView(R.id.tab_hotrecycle)
    RecyclerView tabHotrecycle;
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

    }

    @Override
    public void getHotView(String hotString) {
        tabHotrecycle.setHasFixedSize(true);
        tabHotrecycle.setNestedScrollingEnabled(false);
        tabHotrecycle.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        tabHotrecycle.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        HotBean hotBean = gson.fromJson(hotString, HotBean.class);
        ArrayList<HotBean.ResultBean> hotBeanResult = (ArrayList<HotBean.ResultBean>) hotBean.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tabHotrecycle.setLayoutManager(layoutManager);

        HotTopAdapter hotTopAdapter = new HotTopAdapter(R.layout.item_tabhotmore, hotBeanResult);
        tabHotrecycle.setAdapter(hotTopAdapter);
        hotTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = hotBeanResult.get(position).getMovieId();
                String writename = hotBeanResult.get(position).getName();
                double score = hotBeanResult.get(position).getScore();
                SharedPreferences hotsp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                hotsp.edit().putInt("movieId", movieId).commit();
                hotsp.edit().putString("writename",writename).commit();
                hotsp.edit().putString("score",String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), FilmDeatilActivity.class));
            }
        });
        hotTopAdapter.setMyPayhotBottom(new HotTopAdapter.myPayhotBottom() {
            @Override
            public void getmyPayhotBottom(Button btn_payhotbottom) {
                btn_payhotbottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), SelectFilmSeatActivity.class));
                    }
                });
            }
        });
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
        fPresenter.requestHotInfo(count, page);
    }

    @Override
    protected FilmPresenter<FilmContract.FilmView> createFragmentPresenter() {
        return new FilmPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.hot_layout;
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

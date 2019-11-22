package com.bw.movie.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.presenter.FilmDetailPresenter;
import com.bw.movie.view.adapter.FilmHeraldVideoAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static android.content.Context.MODE_PRIVATE;


public class FilmheraldFragment extends BaseFragment<FilmDeatilContract.FilmDetailView, FilmDetailPresenter<FilmDeatilContract.FilmDetailView>> implements FilmDeatilContract.FilmDetailView {

    @BindView(R.id.filmherald_recycle)
    RecyclerView filmheraldRecycle;
    Unbinder unbinder;
    private FilmHeraldVideoAdapter filmHeraldVideoAdapter;

    @Override
    public void getSuggestData(String filmdetailString) {

    }

    @Override
    public void getHeraldData(String videoString) {
        filmheraldRecycle.setHasFixedSize(true);
        filmheraldRecycle.setNestedScrollingEnabled(false);
//        filmheraldRecycle.setItemViewCacheSize(300);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        filmheraldRecycle.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(videoString, FilmDetailBean.class);
        ArrayList<FilmDetailBean.ResultBean.ShortFilmListBean> shortFilmList = (ArrayList<FilmDetailBean.ResultBean.ShortFilmListBean>) filmDetailBean.getResult().getShortFilmList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        filmheraldRecycle.setLayoutManager(linearLayoutManager);
        filmHeraldVideoAdapter = new FilmHeraldVideoAdapter(R.layout.item_filmheraldadapter, shortFilmList);
        filmheraldRecycle.setAdapter(filmHeraldVideoAdapter);
    }

    @Override
    public void getStillData(String stillString) {

    }

    @Override
    public void getCineCimsData(String cinecismString) {

    }

    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        fPresenter.requestHeraldInfo(movieId);
    }

    @Override
    protected FilmDetailPresenter<FilmDeatilContract.FilmDetailView> createFragmentPresenter() {
        return new FilmDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.filmheraldfragment_layout;
    }

    //播放停止释放
    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
        getActivity().finish();
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

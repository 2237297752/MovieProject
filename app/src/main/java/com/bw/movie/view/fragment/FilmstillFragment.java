package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.presenter.FilmDetailPresenter;
import com.bw.movie.view.adapter.FilmStillFragAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilmstillFragment extends BaseFragment<FilmDeatilContract.FilmDetailView, FilmDetailPresenter<FilmDeatilContract.FilmDetailView>> implements FilmDeatilContract.FilmDetailView {

    @BindView(R.id.recycle_filmStillfrag)
    RecyclerView recycleFilmStillfrag;
    Unbinder unbinder;

    @Override
    public void getSuggestData(String filmdetailString) {

    }

    @Override
    public void getHeraldData(String videoString) {

    }

    @Override
    public void getStillData(String stillString) {
        recycleFilmStillfrag.setHasFixedSize(true);
        recycleFilmStillfrag.setNestedScrollingEnabled(false);
        recycleFilmStillfrag.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recycleFilmStillfrag.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(stillString, FilmDetailBean.class);
        List<String> posterList = filmDetailBean.getResult().getPosterList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recycleFilmStillfrag.setLayoutManager(gridLayoutManager);
        FilmStillFragAdapter filmStillFragAdapter = new FilmStillFragAdapter(R.layout.item_filmstillfrag, posterList);
        recycleFilmStillfrag.setAdapter(filmStillFragAdapter);
    }

    @Override
    public void getCineCimsData(String cinecismString) {

    }

    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        fPresenter.requestStillInfo(movieId);
    }

    @Override
    protected FilmDetailPresenter<FilmDeatilContract.FilmDetailView> createFragmentPresenter() {
        return new FilmDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.filmstillfragment_layout;
    }


}

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
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.bean.CinecismBean;
import com.bw.movie.presenter.FilmDetailPresenter;
import com.bw.movie.view.activity.SpeakDetailActivity;
import com.bw.movie.view.adapter.FilmCinecismAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FilmcinecismFragment extends BaseFragment<FilmDeatilContract.FilmDetailView, FilmDetailPresenter<FilmDeatilContract.FilmDetailView>> implements FilmDeatilContract.FilmDetailView {

    @BindView(R.id.recycle_filmcinecismfrag)
    RecyclerView recycleFilmcinecismfrag;
    private int count=10;
    private int page=1;
    private FilmCinecismAdapter filmCinecismAdapter;

    @Override
    public void getSuggestData(String filmdetailString) {

    }

    @Override
    public void getHeraldData(String videoString) {

    }

    @Override
    public void getStillData(String stillString) {

    }

    @Override
    public void getCineCimsData(String cinecismString) {
       if (null!=cinecismString){
           recycleFilmcinecismfrag.setHasFixedSize(true);
           recycleFilmcinecismfrag.setNestedScrollingEnabled(false);
           recycleFilmcinecismfrag.setItemViewCacheSize(200);
           RecyclerView.RecycledViewPool recycledViewPool = new
                   RecyclerView.RecycledViewPool();
           recycleFilmcinecismfrag.setRecycledViewPool(recycledViewPool);
           Gson gson = new Gson();
           CinecismBean cinecismBean = gson.fromJson(cinecismString, CinecismBean.class);
           ArrayList<CinecismBean.ResultBean> cinecismBeanResult = (ArrayList<CinecismBean.ResultBean>) cinecismBean.getResult();
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
           recycleFilmcinecismfrag.setLayoutManager(linearLayoutManager);
           filmCinecismAdapter = new FilmCinecismAdapter(R.layout.item_filmcinecismadapter, cinecismBeanResult);
           recycleFilmcinecismfrag.setAdapter(filmCinecismAdapter);
           filmCinecismAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                   startActivity(new Intent(getActivity(), SpeakDetailActivity.class));
               }
           });
       }
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        int movieId = sp.getInt("movieId", 0);
        fPresenter.requestCineCimsInfo(movieId,count,page);
    }

    @Override
    protected FilmDetailPresenter<FilmDeatilContract.FilmDetailView> createFragmentPresenter() {
        return new FilmDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.filmcinecismfragment_layout;
    }

}

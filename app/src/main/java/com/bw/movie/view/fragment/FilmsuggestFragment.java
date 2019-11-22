package com.bw.movie.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.FilmDeatilContract;
import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.presenter.FilmDetailPresenter;
import com.bw.movie.view.adapter.FilmSuggestActorAdapterr;
import com.bw.movie.view.adapter.FilmSuggestDirectAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class FilmsuggestFragment extends BaseFragment<FilmDeatilContract.FilmDetailView, FilmDetailPresenter<FilmDeatilContract.FilmDetailView>> implements FilmDeatilContract.FilmDetailView {

    @BindView(R.id.summary_filmsuggestfrag)
    TextView summaryFilmsuggestfrag;
    @BindView(R.id.director_filmsuggestfrag)
    RecyclerView directorFilmsuggestfrag;
    @BindView(R.id.actor_filmsuggestfrag)
    RecyclerView actorFilmsuggestfrag;
    Unbinder unbinder;

    @Override
    public void getSuggestData(String filmdetailString) {
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(filmdetailString, FilmDetailBean.class);
        FilmDetailBean.ResultBean filmDetailBeanResult = filmDetailBean.getResult();
        ArrayList<FilmDetailBean.ResultBean.MovieDirectorBean> movieDirector = (ArrayList<FilmDetailBean.ResultBean.MovieDirectorBean>) filmDetailBean.getResult().getMovieDirector();
        summaryFilmsuggestfrag.setText(filmDetailBeanResult.getSummary()+"");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        directorFilmsuggestfrag.setLayoutManager(manager);
        FilmSuggestDirectAdapter filmSuggestDirectAdapter = new FilmSuggestDirectAdapter(R.layout.item_filmsuggestdirectadapter, movieDirector);
        directorFilmsuggestfrag.setAdapter(filmSuggestDirectAdapter);
        String dirtcorname = movieDirector.get(0).getName();

        ArrayList<FilmDetailBean.ResultBean.MovieActorBean> movieActor = (ArrayList<FilmDetailBean.ResultBean.MovieActorBean>) filmDetailBean.getResult().getMovieActor();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        actorFilmsuggestfrag.setLayoutManager(gridLayoutManager);
        FilmSuggestActorAdapterr filmSuggestActorAdapterr = new FilmSuggestActorAdapterr(R.layout.item_filmsuggestactoradapter, movieActor);
        actorFilmsuggestfrag.setAdapter(filmSuggestActorAdapterr);

        SharedPreferences fsp = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        fsp.edit().putString("dirtcorname",dirtcorname).commit();
    }

    @Override
    public void getHeraldData(String videoString) {

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
        fPresenter.requestSuggestInfo(movieId);
    }

    @Override
    protected FilmDetailPresenter<FilmDeatilContract.FilmDetailView> createFragmentPresenter() {
        return new FilmDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.filmsuggestfragment_layout;
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

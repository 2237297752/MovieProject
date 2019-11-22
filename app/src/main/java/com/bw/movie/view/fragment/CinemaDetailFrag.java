package com.bw.movie.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.MovieDetailContract;
import com.bw.movie.model.bean.CinemaDetailBean;
import com.bw.movie.presenter.MovieDetailPresenter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CinemaDetailFrag extends BaseFragment<MovieDetailContract.MovieDetailView, MovieDetailPresenter<MovieDetailContract.MovieDetailView>> implements MovieDetailContract.MovieDetailView {


    @BindView(R.id.img_cinemalocation)
    ImageView imgCinemalocation;
    @BindView(R.id.name_cinemadetail)
    TextView nameCinemadetail;
    @BindView(R.id.img_cinemadianhua)
    ImageView imgCinemadianhua;
    @BindView(R.id.phone_cinemadetail)
    TextView phoneCinemadetail;
    @BindView(R.id.tvcinemadetail_location)
    TextView tvcinemadetailLocation;
    Unbinder unbinder;

    @Override
    protected void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", 0);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        Intent intent = getActivity().getIntent();
        int cinemaId = intent.getIntExtra("cinemaId", 0);
        fPresenter.requestCinemaDetailTopInfo(userId, sessionId, cinemaId);
    }

    @Override
    protected MovieDetailPresenter<MovieDetailContract.MovieDetailView> createFragmentPresenter() {
        return new MovieDetailPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.cinemadetail_frag;
    }

    @Override
    public void getCinemaDetailTopView(String cinemaDetailString) {
        Gson gson = new Gson();
        CinemaDetailBean cinemaDetailBean = gson.fromJson(cinemaDetailString, CinemaDetailBean.class);
        CinemaDetailBean.ResultBean cinemaDetailBeanResult = cinemaDetailBean.getResult();
        nameCinemadetail.setText(cinemaDetailBeanResult.getAddress());
        phoneCinemadetail.setText(cinemaDetailBeanResult.getPhone());
        tvcinemadetailLocation.setText(cinemaDetailBeanResult.getVehicleRoute());
        phoneCinemadetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+cinemaDetailBeanResult.getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getCinemaDetailSpeakView(String cinemadetailSpeakString) {

    }

    @Override
    public void getCinemaDetailGuanzhuView(String gunazhuString) {

    }

    @Override
    public void getCinemaDetailWeiGuanzhuView(String weiString) {

    }

    @Override
    public void getFilmPaiDataView(String paiDataString) {

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

    @OnClick({R.id.img_cinemalocation, R.id.img_cinemadianhua})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cinemalocation:
                break;
            case R.id.img_cinemadianhua:
                break;
        }
    }
}

package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.model.bean.BannerBean;
import com.bw.movie.model.bean.HotBean;
import com.bw.movie.model.bean.PlayingBean;
import com.bw.movie.model.bean.WillBean;
import com.bw.movie.model.bean.YuYueFilmBean;
import com.bw.movie.presenter.CinemaPresenter;
import com.bw.movie.view.activity.CinemaDetailActivity;
import com.bw.movie.view.activity.EntryActivity;
import com.bw.movie.view.activity.FilmDeatilActivity;
import com.bw.movie.view.activity.ScoutActivity;
import com.bw.movie.view.activity.SelectFilmSeatActivity;
import com.bw.movie.view.adapter.CinemaHotTopAdapter;
import com.bw.movie.view.adapter.CinemaPlayingAdapter;
import com.bw.movie.view.adapter.WillAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentCinema extends BaseFragment<CinemaContract.CinemaView, CinemaPresenter<CinemaContract.CinemaView>> implements CinemaContract.CinemaView {

    @BindView(R.id.cinema_img_location)
    ImageView cinemaImgLocation;
    @BindView(R.id.cinema_tv_location)
    TextView cinemaTvLocation;
    @BindView(R.id.cinema_img_serach)
    ImageView cinemaImgSerach;
    @BindView(R.id.cinema_xbanner)
    XBanner cinemaXbaner;
    @BindView(R.id.cinema_tv_more)
    TextView cinemaTvMore;
    @BindView(R.id.cinema_recycleView)
    RecyclerView cinemaRecycleView;
    @BindView(R.id.cinema_tv_morewill)
    TextView cinemaTvMorewill;
    @BindView(R.id.cinema_recycleView_will)
    RecyclerView cinemaRecycleWill;
    @BindView(R.id.cinema_tv_morehot)
    TextView cinemaTvMorehot;
    @BindView(R.id.cinema_recycleView_hot)
    RecyclerView cinemaRecycleHot;
    Unbinder unbinder;
    @BindView(R.id.imgsimp_hottop)
    SimpleDraweeView imgsimpHottop;
    @BindView(R.id.name_hottop)
    TextView nameHottop;
    @BindView(R.id.scror_hottop)
    TextView scrorHottop;
    @BindView(R.id.pay_hottop)
    Button payHottop;
    private int page = 1;
    private int count = 4;
    List<HotCity> hotCities = new ArrayList<>();
    private Intent intent;
    private int userId;
    private String sessionId;

    @Override
    protected void initView() {
        fPresenter.requestBannerInfo();
        fPresenter.requestPlayingInfo(count, page);
        fPresenter.requestHotInfo(count, page);
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", "");
        fPresenter.requestWillInfo(count, page, userId, sessionId);
    }

    @Override
    protected CinemaPresenter<CinemaContract.CinemaView> createFragmentPresenter() {
        return new CinemaPresenter<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    public void getBannerView(String bannerString) {
        Gson gson = new Gson();
        final BannerBean bannerBean = gson.fromJson(bannerString, BannerBean.class);
        List<BannerBean.ResultBean> result = bannerBean.getResult();
        cinemaXbaner.setBannerData(R.layout.banner_image, new AbstractList<SimpleBannerInfo>() {
            @Override
            public SimpleBannerInfo get(int index) {
                return null;
            }

            @Override
            public int size() {
                return result.size();
            }
        });
        cinemaXbaner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.banner_simple);
                String imageUrl = result.get(position).imageUrl;
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(imageUrl)
                        .setAutoPlayAnimations(true)
                        .build();
                simpleDraweeView.setController(controller);
            }
        });
        cinemaXbaner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getContext(), "点击了第" + position+1 + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getPlayingView(String playingString) {
        cinemaRecycleView.setHasFixedSize(true);
        cinemaRecycleView.setNestedScrollingEnabled(false);
        cinemaRecycleView.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        cinemaRecycleView.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        PlayingBean playingBean = gson.fromJson(playingString, PlayingBean.class);
        final ArrayList<PlayingBean.ResultBean> playingBeanResult = (ArrayList<PlayingBean.ResultBean>) playingBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        cinemaRecycleView.setLayoutManager(linearLayoutManager);
        CinemaPlayingAdapter cinemaPlayingAdapter = new CinemaPlayingAdapter(R.layout.item_playing, playingBeanResult);
        cinemaRecycleView.setAdapter(cinemaPlayingAdapter);

        cinemaPlayingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = playingBeanResult.get(position).getMovieId();
                String writename = playingBeanResult.get(position).getName();
                double score = playingBeanResult.get(position).getScore();
                SharedPreferences spplaying = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                spplaying.edit().putInt("movieId", movieId).commit();
                spplaying.edit().putString("writename", writename).commit();
                spplaying.edit().putString("score", String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), FilmDeatilActivity.class));
            }
        });
        cinemaPlayingAdapter.setPlayingInter(new CinemaPlayingAdapter.playingInter() {
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
        cinemaRecycleWill.setHasFixedSize(true);
        cinemaRecycleWill.setNestedScrollingEnabled(false);
        cinemaRecycleWill.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        cinemaRecycleWill.setRecycledViewPool(recycledViewPool);

        Gson gson = new Gson();
        WillBean willBean = gson.fromJson(willString, WillBean.class);
        final ArrayList<WillBean.ResultBean> willBeanResult = (ArrayList<WillBean.ResultBean>) willBean.getResult();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cinemaRecycleWill.setLayoutManager(layoutManager);
        WillAdapter willAdapter = new WillAdapter(R.layout.item_will, willBeanResult);
        cinemaRecycleWill.setAdapter(willAdapter);

        willAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = willBeanResult.get(position).getMovieId();
                String writename = willBeanResult.get(position).getName();
                double score = willBeanResult.get(position).getScore();
                SharedPreferences spwill = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                spwill.edit().putInt("movieId", movieId).commit();
                spwill.edit().putString("writename", writename).commit();
                spwill.edit().putString("score", String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), CinemaDetailActivity.class));
            }
        });
        willAdapter.setOnWillYuYueCallBack(new WillAdapter.OnWillYuYueCallBack() {
            @Override
            public void getOnWillYuYueCallBack(final Button yuyue_will, final int movieId) {
                yuyue_will.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fPresenter.requestBtnyuyueInfo(userId, sessionId, movieId);
                        yuyue_will.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
                        yuyue_will.setText("已预约");
                    }
                });
            }
        });

    }

    @Override
    public void getHotView(String hotString) {
        cinemaRecycleHot.setHasFixedSize(true);
        cinemaRecycleHot.setNestedScrollingEnabled(false);
        cinemaRecycleHot.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        cinemaRecycleHot.setRecycledViewPool(recycledViewPool);

        Gson gson = new Gson();
        HotBean hotBean = gson.fromJson(hotString, HotBean.class);
        final ArrayList<HotBean.ResultBean> hotBeanResult = (ArrayList<HotBean.ResultBean>) hotBean.getResult();
        HotBean.ResultBean resultBean = hotBeanResult.get(0);
        imgsimpHottop.setImageURI(resultBean.getImageUrl());
        nameHottop.setText(resultBean.getName());
        scrorHottop.setText(resultBean.getScore() + "" + "分");
        hotBeanResult.remove(0);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        cinemaRecycleHot.setLayoutManager(manager);
        CinemaHotTopAdapter cinemaHotTopAdapter = new CinemaHotTopAdapter(R.layout.item_hotbottom, hotBeanResult);
        cinemaRecycleHot.setAdapter(cinemaHotTopAdapter);

        cinemaHotTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int movieId = hotBeanResult.get(position).getMovieId();
                String writename = hotBeanResult.get(position).getName();
                double score = hotBeanResult.get(position).getScore();
                SharedPreferences hotsp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                hotsp.edit().putInt("movieId", movieId).commit();
                hotsp.edit().putString("writename", writename).commit();
                hotsp.edit().putString("score", String.valueOf(score)).commit();
                startActivity(new Intent(getActivity(), CinemaDetailActivity.class));
            }
        });
        cinemaHotTopAdapter.setMyPayhotBottom(new CinemaHotTopAdapter.myPayhotBottom() {
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
        if ("1001".equals(yuYueFilmBean.getStatus())) {
            Toast.makeText(getActivity(), yuYueFilmBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    @OnClick({R.id.cinema_img_location, R.id.cinema_img_serach, R.id.cinema_tv_more, R.id.cinema_tv_morewill, R.id.cinema_tv_morehot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cinema_img_location:
                geiDiTu();
                break;
            case R.id.cinema_img_serach:
                startActivity(new Intent(getActivity(), ScoutActivity.class));
                break;
            case R.id.cinema_tv_more:
                intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra("selectentry", 1);
                startActivity(intent);
                break;
            case R.id.cinema_tv_morewill:
                intent.putExtra("selectentry", 2);
                startActivity(intent);
                break;
            case R.id.cinema_tv_morehot:
                intent.putExtra("selectentry", 3);
                startActivity(intent);
                break;
        }
    }

    private void geiDiTu() {
        hotCities.add(new HotCity("北京", "北京", "101010100"));
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));

        CityPicker.from(this)
                .enableAnimation(true)
                .setAnimationStyle(0)
                .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))
//                .setHotCities(hotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        cinemaTvLocation.setText(data.getName());
                    }

                    @Override
                    public void onLocate() {

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), "取消选择", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}

package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bw.movie.R;
import com.bw.movie.view.adapter.TabEntryAdapter;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentMovie extends Fragment {

    @BindView(R.id.cinemaimg_location)
    ImageView cinemaimgLocation;
    @BindView(R.id.tvcinema_location)
    TextView tvcinemaLocation;
    @BindView(R.id.cinema_imgserach)
    ImageView cinemaImgserach;
    @BindView(R.id.cinematab_layout)
    TabLayout cinematabLayout;
    @BindView(R.id.cinemaview_pager)
    ViewPager cinemaviewPager;
    Unbinder unbinder;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    List<HotCity> hotCities = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add("推荐影院");
        list.add("附近影院");
        list.add("海淀区");
        fragments.add(new MovieRecommendFrag());
        fragments.add(new MovieNearbyFrag());
        fragments.add(new MovieDistrictFrag());
        TabEntryAdapter tabEntryAdapter = new TabEntryAdapter(getActivity().getSupportFragmentManager(), list, fragments);
        cinemaviewPager.setAdapter(tabEntryAdapter);
        cinematabLayout.setupWithViewPager(cinemaviewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cinemaimg_location, R.id.cinema_imgserach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cinemaimg_location:
                getDiTu();
                break;
            case R.id.cinema_imgserach:
                break;
        }
    }

    private void getDiTu() {
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));

        CityPicker.from(this)
                .enableAnimation(true)
                .setAnimationStyle(0)
                .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))
                .setHotCities(hotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        tvcinemaLocation.setText(data.getName());
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

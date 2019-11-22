package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.fragment.FragmentCinema;
import com.bw.movie.view.fragment.FragmentMovie;
import com.bw.movie.view.fragment.FragmentMy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_tv_cinema)
    TextView homeTvCinema;
    @BindView(R.id.home_rb_cinema)
    RadioButton homeRbCinema;
    @BindView(R.id.home_tv_movie)
    TextView homeTvMovie;
    @BindView(R.id.home_rb_movie)
    RadioButton homeRbMovie;
    @BindView(R.id.home_tv_my)
    TextView homeTvMy;
    @BindView(R.id.home_rb_my)
    RadioButton homeRbMy;
    @BindView(R.id.home_radioGroup)
    RadioGroup homeRadioGroup;
    @BindView(R.id.home_fl)
    FrameLayout homeFl;
    private FragmentCinema fragmentCinema;
    private FragmentMovie fragmentMovie;
    private FragmentMy fragmentMy;
    private Unbinder unbinder;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
        fragmentCinema = new FragmentCinema();
        fragmentMovie = new FragmentMovie();
        fragmentMy = new FragmentMy();
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.home_fl, fragmentCinema)
                .add(R.id.home_fl, fragmentMovie)
                .add(R.id.home_fl, fragmentMy)
                .show(fragmentCinema)
                .hide(fragmentMovie)
                .hide(fragmentMy)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.home_rb_cinema, R.id.home_rb_movie, R.id.home_rb_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_rb_cinema:
                homeTvCinema.setVisibility(View.VISIBLE);
                homeTvMovie.setVisibility(View.INVISIBLE);
                homeTvMy.setVisibility(View.INVISIBLE);
                homeRbCinema.setChecked(true);
                homeRbMovie.setChecked(false);
                homeRbMy.setChecked(false);
                manager.beginTransaction()
                        .show(fragmentCinema)
                        .hide(fragmentMovie)
                        .hide(fragmentMy)
                        .commit();
                break;
            case R.id.home_rb_movie:
                homeTvCinema.setVisibility(View.INVISIBLE);
                homeTvMovie.setVisibility(View.VISIBLE);
                homeTvMy.setVisibility(View.INVISIBLE);
                homeRbCinema.setChecked(false);
                homeRbMovie.setChecked(true);
                homeRbMy.setChecked(false);
                manager.beginTransaction()
                        .show(fragmentMovie)
                        .hide(fragmentCinema)
                        .hide(fragmentMy)
                        .commit();
                break;
            case R.id.home_rb_my:
                homeTvCinema.setVisibility(View.INVISIBLE);
                homeTvMovie.setVisibility(View.INVISIBLE);
                homeTvMy.setVisibility(View.VISIBLE);
                homeRbCinema.setChecked(false);
                homeRbMovie.setChecked(false);
                homeRbMy.setChecked(true);
                manager.beginTransaction()
                        .show(fragmentMy)
                        .hide(fragmentCinema)
                        .hide(fragmentMovie)
                        .commit();
                break;
        }
    }
}

package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.SelectLocationContract;
import com.bw.movie.model.bean.DistrictLocationBean;
import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.model.bean.FilmDetailTimeBean;
import com.bw.movie.model.bean.SelectFilmRightBean;
import com.bw.movie.model.bean.SelectSmartPriceBean;
import com.bw.movie.model.bean.SelectTimeCinemaBean;
import com.bw.movie.presenter.SelectLocationPresenter;
import com.bw.movie.view.adapter.DistrictLocationAdapter;
import com.bw.movie.view.adapter.FilmSmartPriceAdapter;
import com.bw.movie.view.adapter.SelectFilmRightAdapter;
import com.bw.movie.view.adapter.SelectFilmTimeTopAdapter;
import com.bw.movie.view.adapter.SelectTimeCinemaAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//选择影院页
public class SelectFilmSeatActivity extends BaseActivity<SelectLocationContract.SelectLocationView, SelectLocationPresenter<SelectLocationContract.SelectLocationView>> implements SelectLocationContract.SelectLocationView {

    @BindView(R.id.selectfilmseat_fanhui)
    ImageView selectfilmseatFanhui;
    @BindView(R.id.imgseactseat)
    ImageView imgseactseat;
    @BindView(R.id.name_selectseat)
    TextView nameSelectseat;
    @BindView(R.id.time_seat)
    TextView timeSeat;
    @BindView(R.id.scroe_seat)
    TextView scroeSeat;
    @BindView(R.id.dirctor_seat)
    TextView dirctorSeat;
    @BindView(R.id.tv_seatlocation)
    TextView tvSeatlocation;
    @BindView(R.id.tv_seattime)
    TextView tvSeattime;
    @BindView(R.id.rb_seatprice)
    RadioButton rbSeatprice;
    @BindView(R.id.img_seatsesrach)
    ImageView imgSeatsesrach;
    @BindView(R.id.recycle_smallprice)
    RecyclerView recycleSmallprice;
    private int count = 10;
    private int page = 1;
    private SelectTimeCinemaAdapter selectTimeCinemaAdapter;

    @Override
    public void getSelectTopView(String topString) {
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(topString, FilmDetailBean.class);
        FilmDetailBean.ResultBean filmDetailBeanResult = filmDetailBean.getResult();
        Glide.with(SelectFilmSeatActivity.this).load(filmDetailBeanResult.getImageUrl()).into(imgseactseat);
        nameSelectseat.setText(filmDetailBeanResult.getName());
        timeSeat.setText(filmDetailBeanResult.getDuration());
        scroeSeat.setText(filmDetailBeanResult.getScore() + "" + "分");
        dirctorSeat.setText(filmDetailBeanResult.getMovieDirector().get(0).getName());
    }

    @Override
    public void getSelectLeftView(String locationString) {
        View regionView = LayoutInflater.from(SelectFilmSeatActivity.this).inflate(R.layout.poptoplocationseat_layout, null);
        final PopupWindow mPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        //点击窗口外边窗口消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        //点击窗口外可消失
        mPopupWindow.setContentView(regionView);
        mPopupWindow.showAsDropDown(tvSeatlocation, -20, 0);
        RecyclerView recyclepop_left = regionView.findViewById(R.id.recyclepop_left);
        recyclepop_left.setHasFixedSize(true);
        recyclepop_left.setNestedScrollingEnabled(false);
        recyclepop_left.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recyclepop_left.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        DistrictLocationBean districtLocationBean = gson.fromJson(locationString, DistrictLocationBean.class);
        ArrayList<DistrictLocationBean.ResultBean> districtLocationBeanResult = (ArrayList<DistrictLocationBean.ResultBean>) districtLocationBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectFilmSeatActivity.this);
        recyclepop_left.setLayoutManager(linearLayoutManager);
        DistrictLocationAdapter districtLocationAdapter = new DistrictLocationAdapter(R.layout.item_districtlocationadapter, districtLocationBeanResult);
        recyclepop_left.setAdapter(districtLocationAdapter);
        districtLocationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvSeatlocation.setText(districtLocationBeanResult.get(position).getRegionName());
                mPopupWindow.dismiss();
                SharedPreferences esp = getSharedPreferences("config", MODE_PRIVATE);
                int movieId = esp.getInt("movieId", 0);
                int regionId = districtLocationBeanResult.get(position).getRegionId();
                mPresenter.requestSelectRightInfo(regionId, movieId, count, page);
            }
        });
    }

    @Override
    public void getSelectRightView(String locationreight) {
        View regionView = LayoutInflater.from(SelectFilmSeatActivity.this).inflate(R.layout.poplocacinemaseat_layout, null);
        final PopupWindow mPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setContentView(regionView);
        mPopupWindow.showAsDropDown(tvSeatlocation, -20, 0);
        RecyclerView recyclepop_right = regionView.findViewById(R.id.recyclepop_right);
        recyclepop_right.setHasFixedSize(true);
        recyclepop_right.setNestedScrollingEnabled(false);
        recyclepop_right.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recyclepop_right.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        SelectFilmRightBean selectFilmRightBean = gson.fromJson(locationreight, SelectFilmRightBean.class);
        ArrayList<SelectFilmRightBean.ResultBean> filmRightBeanResult = (ArrayList<SelectFilmRightBean.ResultBean>) selectFilmRightBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(SelectFilmSeatActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclepop_right.setLayoutManager(manager);
        SelectFilmRightAdapter selectFilmRightAdapter = new SelectFilmRightAdapter(R.layout.item_selectdetailrightadapter, filmRightBeanResult);
        recyclepop_right.setAdapter(selectFilmRightAdapter);
        selectFilmRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = filmRightBeanResult.get(position).getCinemaId();
                Intent intent = new Intent(SelectFilmSeatActivity.this, SelectCinemaSeatActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getSelectTimeView(String timeString) {
        View regionView = LayoutInflater.from(SelectFilmSeatActivity.this).inflate(R.layout.popfilmtimeseat_layout, null);
        final PopupWindow tPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        tPopupWindow.setOutsideTouchable(true);
        tPopupWindow.setTouchable(true);
        tPopupWindow.setContentView(regionView);
        tPopupWindow.showAsDropDown(tvSeatlocation, -20, 0);
        RecyclerView recyclepop_toptime = regionView.findViewById(R.id.recyclepop_toptime);
        recyclepop_toptime.setHasFixedSize(true);
        recyclepop_toptime.setNestedScrollingEnabled(false);
        recyclepop_toptime.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recyclepop_toptime.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        FilmDetailTimeBean filmDetailTimeBean = gson.fromJson(timeString, FilmDetailTimeBean.class);
        List<String> timeBeanResult = filmDetailTimeBean.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SelectFilmSeatActivity.this, 2);
        recyclepop_toptime.setLayoutManager(gridLayoutManager);
        SelectFilmTimeTopAdapter selectFilmTimeTopAdapter = new SelectFilmTimeTopAdapter(R.layout.item_filmdetailtimetopadapter, timeBeanResult);
        recyclepop_toptime.setAdapter(selectFilmTimeTopAdapter);
        selectFilmTimeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvSeattime.setText(timeBeanResult.get(position));
                tPopupWindow.dismiss();
                SharedPreferences esp = getSharedPreferences("config", MODE_PRIVATE);
                int movieId = esp.getInt("movieId", 0);
                String data = timeBeanResult.get(position);
                mPresenter.requestSelectTimeCinemaInfo(movieId, data, count, page);
            }
        });
    }

    @Override
    public void getSelectTimeCinemaView(String timeCinemaString) {
        View regionView = LayoutInflater.from(SelectFilmSeatActivity.this).inflate(R.layout.popfilmtimecinemaseat_layout, null);
        final PopupWindow rPopupWindow = new PopupWindow(regionView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        rPopupWindow.setOutsideTouchable(true);
        rPopupWindow.setTouchable(true);
        rPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        rPopupWindow.setContentView(regionView);
        rPopupWindow.showAsDropDown(tvSeatlocation, -20, 0);

        RecyclerView recyclepop_bottomtimecinema = regionView.findViewById(R.id.recyclepop_bottomtimecinema);
        recyclepop_bottomtimecinema.setHasFixedSize(true);
        recyclepop_bottomtimecinema.setNestedScrollingEnabled(false);
        recyclepop_bottomtimecinema.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recyclepop_bottomtimecinema.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        SelectTimeCinemaBean selectTimeCinemaBean = gson.fromJson(timeCinemaString, SelectTimeCinemaBean.class);
        ArrayList<SelectTimeCinemaBean.ResultBean> timeCinemaBeanResult = (ArrayList<SelectTimeCinemaBean.ResultBean>) selectTimeCinemaBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(SelectFilmSeatActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclepop_bottomtimecinema.setLayoutManager(manager);
        selectTimeCinemaAdapter = new SelectTimeCinemaAdapter(R.layout.item_selecttimecinemaadapter, timeCinemaBeanResult);
        recyclepop_bottomtimecinema.setAdapter(selectTimeCinemaAdapter);
        selectTimeCinemaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = timeCinemaBeanResult.get(position).getCinemaId();
                Intent intent = new Intent(SelectFilmSeatActivity.this, SelectCinemaSeatActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getSelectPriceView(String priceString) {
        recycleSmallprice.setHasFixedSize(true);
        recycleSmallprice.setNestedScrollingEnabled(false);
        recycleSmallprice.setItemViewCacheSize(200);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        recycleSmallprice.setRecycledViewPool(recycledViewPool);
        Gson gson = new Gson();
        SelectSmartPriceBean selectSmartPriceBean = gson.fromJson(priceString, SelectSmartPriceBean.class);
        ArrayList<SelectSmartPriceBean.ResultBean> priceBeanResult = (ArrayList<SelectSmartPriceBean.ResultBean>) selectSmartPriceBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectFilmSeatActivity.this, LinearLayoutManager.VERTICAL, false);
        recycleSmallprice.setLayoutManager(linearLayoutManager);
        FilmSmartPriceAdapter filmSmartPriceAdapter = new FilmSmartPriceAdapter(R.layout.item_smartpriceadapter, priceBeanResult);
        recycleSmallprice.setAdapter(filmSmartPriceAdapter);
        filmSmartPriceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int cinemaId = priceBeanResult.get(position).getCinemaId();
                Intent intent = new Intent(SelectFilmSeatActivity.this, SelectCinemaSeatActivity.class);
                intent.putExtra("cinemaId",cinemaId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        SharedPreferences lsp = getSharedPreferences("config", MODE_PRIVATE);
        int movieId = lsp.getInt("movieId", 0);
        mPresenter.requestSelectTopInfo(movieId);
    }

    @Override
    protected SelectLocationPresenter<SelectLocationContract.SelectLocationView> createPresenter() {
        return new SelectLocationPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_selectfilmseat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.selectfilmseat_fanhui, R.id.tv_seatlocation, R.id.tv_seattime, R.id.rb_seatprice, R.id.img_seatsesrach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selectfilmseat_fanhui:
                finish();
                break;
            case R.id.tv_seatlocation:
                mPresenter.requestSelectLeftInfo();
                break;
            case R.id.tv_seattime:
                mPresenter.requestSelectTimeInfo();
                break;
            case R.id.rb_seatprice:
                SharedPreferences lsp = getSharedPreferences("config", MODE_PRIVATE);
                int movieId = lsp.getInt("movieId", 0);
                mPresenter.requestSelectPriceInfo(movieId,count,page);
                break;
            case R.id.img_seatsesrach:
                break;
        }
    }

}
package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.ScoutContract;
import com.bw.movie.model.bean.ScoutBean;
import com.bw.movie.presenter.ScoutPresenter;
import com.bw.movie.view.adapter.ScoutAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoutActivity extends BaseActivity<ScoutContract.ScoutView, ScoutPresenter<ScoutContract.ScoutView>> implements ScoutContract.ScoutView {
    @BindView(R.id.scout_fanhui)
    ImageView scoutFanhui;
    @BindView(R.id.edit_scou)
    EditText editScou;
    @BindView(R.id.serach_scoutv)
    TextView serachScoutv;
    @BindView(R.id.recycle_scout)
    RecyclerView recycleScout;
    private int count = 5;
    private int page = 1;
    private String keyword = "我";
    private ScoutAdapter scoutAdapter;

    @Override
    public void getScoutView(String scoutString) {
        Gson gson = new Gson();
        ScoutBean scoutBean = gson.fromJson(scoutString, ScoutBean.class);
        ArrayList<ScoutBean.ResultBean> scoutBeanResult = (ArrayList<ScoutBean.ResultBean>) scoutBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScoutActivity.this, LinearLayoutManager.VERTICAL, false);
        recycleScout.setLayoutManager(linearLayoutManager);
        scoutAdapter = new ScoutAdapter(R.layout.item_scoutadapter, scoutBeanResult);
        recycleScout.setAdapter(scoutAdapter);
        scoutAdapter.setScoutAdapterBack(new ScoutAdapter.scoutAdapterBack() {
            @Override
            public void getscoutAdapterBack(Button pay_scout) {
                pay_scout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ScoutActivity.this,SelectFilmSeatActivity.class));
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.requestScoutInfo(keyword, count, page);
        serachScoutv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editsco = editScou.getText().toString();
                if (editsco.isEmpty()) {
                    Toast.makeText(ScoutActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.requestScoutInfo(editsco, count, page);
                    scoutAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected ScoutPresenter<ScoutContract.ScoutView> createPresenter() {
        return new ScoutPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_scout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.scout_fanhui)
    public void onViewClicked() {
        finish();
    }
}

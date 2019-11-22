package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    private Unbinder unbinder;
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        initData();
    }

    protected abstract void initData();

    protected abstract P createPresenter();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        unbinder.unbind();
    }
}

package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {

    private Unbinder unbinder;
    public P fPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fPresenter = createFragmentPresenter();
        fPresenter.attachView((V) this);
        initView();
    }

    protected abstract void initView();

    protected abstract P createFragmentPresenter();

    protected abstract int getFragmentLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        fPresenter.detachView();
        unbinder.unbind();
    }
}

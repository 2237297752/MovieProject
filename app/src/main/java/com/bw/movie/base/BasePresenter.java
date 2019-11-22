package com.bw.movie.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    private WeakReference<V> weakReference;

    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }
    public void detachView(){
        weakReference.clear();
    }
    public V getView(){
        return weakReference.get();
    }
}

package com.vabasca.presentation.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Vinh & Tri on 04/16/2019
 */

public abstract class Presenter<T extends BaseView> {

    private T mView;
    private CompositeDisposable mCompositeDisposable;
    
    public Presenter(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    public T getView() {
        return mView;
    }

    public CompositeDisposable getCompositeDisposable(){
        return mCompositeDisposable;
    }

    public void attachView(T view){
        mView = view;
    }

    public void detachView(){
        mView = null;
        mCompositeDisposable.dispose();
    }
}

package com.vabasca.presentation.splash;

import com.vabasca.data.UserRepository;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.di.ActivityScope;
import com.vabasca.presentation.base.Presenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

@ActivityScope
public class SplashPresenter extends Presenter<SplashContract.View>
        implements SplashContract.Presenter {

    private UserRepository mUserRepository;

    @Inject
    public SplashPresenter(CompositeDisposable compositeDisposable,
                           UserRepository userRepository) {
        super(compositeDisposable);
        mUserRepository = userRepository;
    }

    @Override
    public void authenticate() {
        getCompositeDisposable()
                .add(mUserRepository.authenticate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authenticateSuccess, this::authenticateFailure));
    }

    private void authenticateSuccess(AuthResponse authResponse){
        getView().navigateToMainScreen();
    }

    private void authenticateFailure(Throwable error){
        getView().navigateToAuthenticationScreen();
    }
}

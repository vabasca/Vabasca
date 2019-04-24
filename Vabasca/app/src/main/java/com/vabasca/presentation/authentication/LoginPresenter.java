package com.vabasca.presentation.authentication;

import com.vabasca.data.UserRepository;
import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.di.ActivityScope;
import com.vabasca.presentation.base.Presenter;
import com.vabasca.utils.ErrorUtils;
import com.vabasca.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vinh & Tri on 04/21/2019
 */

@ActivityScope
public class LoginPresenter extends Presenter<LoginContract.View>
        implements LoginContract.Presenter {

    private UserRepository mUserRepository;

    @Inject
    public LoginPresenter(CompositeDisposable compositeDisposable,
                          UserRepository userRepository) {
        super(compositeDisposable);
        mUserRepository = userRepository;
    }

    @Override
    public void login(String email, String password) {

        if (ValidationUtils.isNullOrEmpty(email, password)) {
            return;
        }
        if (!ValidationUtils.isValidEmail(email)) {
            getView().setErrorEmailField();
            return;
        }
        if (!ValidationUtils.isValidPassword(password)) {
            getView().setErrorPasswordField();
            return;
        }

        getView().showLoading();
        getCompositeDisposable()
                .add(mUserRepository.login(new LoginRequest(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loginSuccess, this::loginFailure));
    }

    private void loginSuccess(AuthResponse authResponse){
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void loginFailure(Throwable error){
        getView().hideLoading();
        getView().loginFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }
}

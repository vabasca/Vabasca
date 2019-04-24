package com.vabasca.presentation.authentication;

import com.vabasca.data.UserRepository;
import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.data.network.model.RegisterRequest;
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
public class RegisterPresenter extends Presenter<RegisterContract.View>
        implements RegisterContract.Presenter {

    private UserRepository mUserRepository;

    @Inject
    public RegisterPresenter(CompositeDisposable compositeDisposable,
                             UserRepository userRepository) {
        super(compositeDisposable);
        mUserRepository = userRepository;
    }

    @Override
    public void register(String email, String password, String confirmPassword, String userName) {

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

        if (!password.equals(confirmPassword)){
            getView().setErrorConfirmPasswordField();
            return;
        }

        getView().showLoading();
        getCompositeDisposable()
                .add(mUserRepository.registerUser(new RegisterRequest(email, password, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::registerSuccess, this::registerFailure));
    }

    private void registerSuccess(AuthResponse authResponse){
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void registerFailure(Throwable error){
        getView().hideLoading();
        getView().registerFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }
}

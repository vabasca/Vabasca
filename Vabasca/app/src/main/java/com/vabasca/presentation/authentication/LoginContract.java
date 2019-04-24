package com.vabasca.presentation.authentication;

import com.vabasca.presentation.base.BaseView;

/**
 * Created by Vinh & Tri on 04/20/2019
 */

public interface LoginContract {

    interface View extends BaseView {

        void loginFailure(String error);

        void setErrorEmailField();

        void setErrorPasswordField();

        void navigateToMainScreen();
    }

    interface Presenter {
        void login(String email, String password);
    }
}

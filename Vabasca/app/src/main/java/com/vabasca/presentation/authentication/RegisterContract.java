package com.vabasca.presentation.authentication;

import com.vabasca.presentation.base.BaseView;

/**
 * Created by Vinh & Tri on 04/21/2019
 */

public interface RegisterContract {

    interface View extends BaseView {

        void registerFailure(String error);

        void setErrorEmailField();

        void setErrorPasswordField();

        void setErrorConfirmPasswordField();

        void navigateToMainScreen();
    }

    interface Presenter {

        void register(String email, String password, String confirmPassword, String userName);
    }
}

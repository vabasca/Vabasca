package com.vabasca.presentation.splash;

import com.vabasca.presentation.base.BaseView;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

public interface SplashContract {

    interface View extends BaseView{

        void navigateToMainScreen();

        void navigateToAuthenticationScreen();
    }

    interface Presenter{

        void authenticate();
    }
}

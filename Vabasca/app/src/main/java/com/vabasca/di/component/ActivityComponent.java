package com.vabasca.di.component;

import com.vabasca.di.module.ActivityModule;
import com.vabasca.di.ActivityScope;
import com.vabasca.presentation.authentication.LoginFragment;
import com.vabasca.presentation.authentication.RegisterFragment;
import com.vabasca.presentation.main.CreateTodoFragment;
import com.vabasca.presentation.main.MainActivity;
import com.vabasca.presentation.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(CreateTodoFragment createTodoFragment);
    void inject(SplashActivity splashActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
}

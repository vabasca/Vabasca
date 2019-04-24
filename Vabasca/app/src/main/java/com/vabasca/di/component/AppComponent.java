package com.vabasca.di.component;

import android.content.Context;

import com.vabasca.App;
import com.vabasca.data.TodoRepository;
import com.vabasca.data.UserRepository;
import com.vabasca.data.network.services.TodoService;
import com.vabasca.data.preferences.SharedPrefs;
import com.vabasca.di.ApplicationContext;
import com.vabasca.di.module.AppModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App App);

    @ApplicationContext
    Context context();

    Gson gson();

    Retrofit retrofit();

    SharedPrefs sharedPrefs();

    TodoService todoService();

    TodoRepository todoRepository();

    UserRepository userRepository();
}

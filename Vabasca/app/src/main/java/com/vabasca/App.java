package com.vabasca;

import android.app.Application;
import android.content.Context;

import com.vabasca.di.component.AppComponent;
import com.vabasca.di.component.DaggerAppComponent;
import com.vabasca.di.module.AppModule;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

public class App extends Application {

    private AppComponent mAppComponent;

    public static App get (Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent component() {
        return mAppComponent;
    }
}

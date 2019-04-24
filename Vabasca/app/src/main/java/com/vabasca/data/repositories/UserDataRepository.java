package com.vabasca.data.repositories;

import com.vabasca.data.UserRepository;
import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.data.network.model.RegisterRequest;
import com.vabasca.data.repositories.local.UserLocalData;
import com.vabasca.data.repositories.remote.UserRemoteData;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public class UserDataRepository implements UserRepository {

    private UserLocalData mUserLocalData;
    private UserRemoteData mUserRemoteData;

    @Inject
    UserDataRepository(UserRemoteData userRemoteData, UserLocalData userLocalData) {
        this.mUserRemoteData = userRemoteData;
        this.mUserLocalData = userLocalData;
    }

    @Override
    public Observable<AuthResponse> authenticate() {
        String token = mUserLocalData.getToken();
        if (token != null){
            return mUserRemoteData.authenticate();
        } else {
            return Observable.error(new Throwable("No token"));
        }
    }

    @Override
    public Observable<AuthResponse> login(LoginRequest loginRequest) {
        return mUserRemoteData.login(loginRequest)
                .doOnNext(authResponse -> {
                    mUserLocalData.setToken(authResponse.getToken());
                    mUserLocalData.setCurrentUserId(authResponse.getUserId());
                });
    }

    @Override
    public Observable<AuthResponse> registerUser(RegisterRequest registerRequest) {
        return mUserRemoteData.register(registerRequest)
                .doOnNext(authResponse -> {
                    mUserLocalData.setToken(authResponse.getToken());
                    mUserLocalData.setCurrentUserId(authResponse.getUserId());
                });
    }

    @Override
    public Completable logout() {
        return mUserLocalData.logout();
    }
}

package com.vabasca.data.repositories.remote;

import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.data.network.model.RegisterRequest;
import com.vabasca.data.network.services.UserService;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public class UserRemoteData {

    private UserService mUserService;

    @Inject
    UserRemoteData(UserService userService) {
        this.mUserService = userService;
    }

    public Observable<AuthResponse> authenticate(){
        return mUserService.authenticate();
    }

    public Observable<AuthResponse> login(LoginRequest loginRequest){
        return mUserService.login(loginRequest);
    }

    public Observable<AuthResponse> register(RegisterRequest registerRequest){
        return mUserService.registerUser(registerRequest);
    }

}

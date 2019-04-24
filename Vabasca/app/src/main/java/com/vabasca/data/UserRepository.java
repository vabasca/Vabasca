package com.vabasca.data;


import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.data.network.model.RegisterRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public interface UserRepository {

    Observable<AuthResponse> authenticate();

    Observable<AuthResponse> login(LoginRequest loginRequest);

    Observable<AuthResponse> registerUser(RegisterRequest registerRequest);

    Completable logout();
}

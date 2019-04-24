package com.vabasca.data.network.services;

import com.vabasca.data.network.model.LoginRequest;
import com.vabasca.data.network.model.AuthResponse;
import com.vabasca.data.network.model.RegisterRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

public interface UserService {

    @GET("me")
    Observable<AuthResponse> authenticate();

    @POST("login")
    Observable<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("register")
    Observable<AuthResponse> registerUser(@Body RegisterRequest registerRequest);
}

package com.vabasca.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

public class AuthResponse {

    @SerializedName("id")
    private String userId;

    @SerializedName("token")
    private String token;

    public AuthResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}

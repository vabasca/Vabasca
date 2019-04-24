package com.vabasca.data.preferences;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public interface SharedPrefs {

    void setCurrentUserId(String userId);

    String getCurrentUserId();

    void setToken(String token);

    String getToken();

    void removeCredentials();
}

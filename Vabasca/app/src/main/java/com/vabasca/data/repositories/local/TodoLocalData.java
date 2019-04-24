package com.vabasca.data.repositories.local;

import com.vabasca.data.preferences.SharedPrefs;
import javax.inject.Inject;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public class TodoLocalData {

    private SharedPrefs mSharedPrefs;

    @Inject
    public TodoLocalData(SharedPrefs sharedPrefs) {
        mSharedPrefs = sharedPrefs;
    }
}

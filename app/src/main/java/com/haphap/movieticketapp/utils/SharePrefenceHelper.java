package com.haphap.movieticketapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePrefenceHelper {

    public static final String USER_ID_KEY = "USER_ID_KEY";

    private static SharePrefenceHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    private SharePrefenceHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static synchronized SharePrefenceHelper getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = new SharePrefenceHelper(PreferenceManager.getDefaultSharedPreferences(context));
        }
        return INSTANCE;
    }

    public void  setLoggedUserId(Long id) {
        sharedPreferences.edit().putLong(USER_ID_KEY,id).apply();
    }

    public Long getUserId() {
        return sharedPreferences.getLong(USER_ID_KEY, -1);
    }
}

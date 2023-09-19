package com.example.carbid.authSave;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class TokensSave implements Serializable {
    private final static String SHARED_PREF_NAME="authentication";
    private final static String TOKEN_KEY = "token_key";
    private final static String REFRESH_TOKEN_KEY="refresh_key";
    public String getAccessToken(Context c ){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return pref.getString(TOKEN_KEY,"");
    }
    public void setAccessToken(Context c , String token){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN_KEY,token);
        editor.apply();
    }
    public String getRefreshToken(Context c ){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return pref.getString(REFRESH_TOKEN_KEY,"");
    }
    public void setRefreshToken(Context c , String refresh_token){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(REFRESH_TOKEN_KEY,refresh_token);
        editor.apply();
    }
}

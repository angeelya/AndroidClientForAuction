package com.example.carbid.authSave;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class UserSave implements Serializable {
    private final static String SHARED_PREF_NAME = "authentication";
    private final static String USER = "user";

    public String getUser(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(USER, "");
    }

    public void setUser(Context c, String user) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER,user);
        editor.apply();
    }
}

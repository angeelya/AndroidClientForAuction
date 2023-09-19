package com.example.carbid.authSave;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class AdminSave implements Serializable {
    private final static String SHARED_PREF_NAME="admin";
    private final static String ADM_KEY = "adm_key";
    public Boolean getModKey(Context c ){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return pref.getBoolean(ADM_KEY,false);
    }
    public void setModKey(Context c , Boolean adm_key){
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(ADM_KEY,adm_key);
        editor.apply();
    }
}

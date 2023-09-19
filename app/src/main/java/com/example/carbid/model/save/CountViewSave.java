package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class CountViewSave implements Serializable {
    private final static String SHARED_PREF_NAME = "count";
    private final static String COUNT = "count_view";

    public String getCount(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(COUNT, "0");
    }

    public void setCount(Context c, String location) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(COUNT,location);
        editor.apply();
    }
}

package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class DestinationSave implements Serializable {
    private final static String SHARED_PREF_NAME = "register";
    private final static String LOCATION = "location";

    public String getLocation(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(LOCATION, "");
    }

    public void setLocation(Context c, String location) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LOCATION,location);
        editor.apply();
    }
}

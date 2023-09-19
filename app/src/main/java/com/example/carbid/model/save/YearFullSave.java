package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class YearFullSave implements Serializable {
    private final static String SHARED_PREF_NAME = "year_full";
    private final static String YEARB = "yearBefore";
    private final static String YEARA = "yearAfter";
    public String getYearBFull(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(YEARB, "");
    }

    public void setYearBFull(Context c, String year) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(YEARB,year);
        editor.apply();
    }
    public String getYearAFull(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(YEARA, "");
    }

    public void setYearAFull(Context c, String year) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(YEARA,year);
        editor.apply();
    }
}

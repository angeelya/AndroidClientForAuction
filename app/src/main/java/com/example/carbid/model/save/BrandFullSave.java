package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class BrandFullSave implements Serializable {
    private final static String SHARED_PREF_NAME = "brand_full";
    private final static String ID = "id_brand";

    public String getID_BrandFull(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(ID, "");
    }

    public void setID_BrandFull(Context c, String id_brand) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ID,id_brand);
        editor.apply();
    }
}

package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class ModelFullSave implements Serializable {
    private final static String SHARED_PREF_NAME = "model_full";
    private final static String ID = "id_model";

    public String getID_ModelFull(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(ID, "");
    }

    public void setID_ModelFull(Context c, String id_model) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ID,id_model);
        editor.apply();
}
}

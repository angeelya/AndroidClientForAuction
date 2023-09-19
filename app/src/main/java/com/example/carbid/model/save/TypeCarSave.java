package com.example.carbid.model.save;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.carbid.model.TypeCar;

import java.io.Serializable;

public class TypeCarSave implements Serializable {
    private final static String SHARED_PREF_NAME = "typeCar";
    private final static String ID = "id_typecar";

    public String getID_TypeCar(Context c) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(ID, "");
    }

    public void setID_TypeCar(Context c, String id_type) {
        SharedPreferences pref = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ID,id_type);
        editor.apply();
    }
}

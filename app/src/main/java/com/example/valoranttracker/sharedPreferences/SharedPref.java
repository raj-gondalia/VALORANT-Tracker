package com.example.valoranttracker.sharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static final String SHARED_PREFERENCES_NAME = "regions";

    private static SharedPref sharedPref = null;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    protected SharedPref() {

    }

    @SuppressLint("CommitPrefEdits")
    public static SharedPref getInstance(Context context) {
        if(sharedPreferences == null) {
            sharedPref = new SharedPref();

            sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

        }

        return sharedPref;
    }

    public void addRegion(String region) {
        editor.putString("region", region);
        editor.commit();
    }

    public String getRegion() {
        return sharedPreferences.getString("region","ap");
    }
}

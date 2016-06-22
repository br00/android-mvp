package com.alessandroborelli.vpi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by br00 on 15/06/2016.
 */
public class AppSettings {

    public static final String LOGGED = "appSettings_logged";

    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public AppSettings(Context aContex){
        mContext = aContex;
        mSharedPreferences = mContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String aKey){
        return mSharedPreferences.getBoolean(aKey, false);
    }

    public void setBoolean(String aKey, boolean aValue){
        mSharedPreferences.edit().putBoolean(aKey,aValue).commit();
    }
}

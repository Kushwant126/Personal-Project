package com.example.personalproject.mainPackage;

import android.content.Context;


public class MyApplication extends android.app.Application {
    public static String APP_DB_LOCK = "Lock";
    public static String LOG_LOCK = "LogLock";
    public static Context mContext;
    public static String STORE_CHECK_CALCULATION_LOCK = "storecheck";

    //	 public static String UNITS_LOCK = "UNITS_LOCK";
    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null)
            mContext = this;
    }
}

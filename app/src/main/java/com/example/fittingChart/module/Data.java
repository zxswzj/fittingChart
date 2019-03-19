package com.example.fittingChart.module;

import android.app.Application;

public class Data extends Application {
    public String TABLE_USER = "users";
    public int DATABASE_VERSION = 2;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
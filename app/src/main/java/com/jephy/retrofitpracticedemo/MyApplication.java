package com.jephy.retrofitpracticedemo;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by jfyang on 12/15/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}

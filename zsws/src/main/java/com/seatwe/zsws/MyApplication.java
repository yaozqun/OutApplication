package com.seatwe.zsws;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/10/11.
 */
public class MyApplication extends Application {
    public static Context context;
    private static MyApplication instance;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public synchronized static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}

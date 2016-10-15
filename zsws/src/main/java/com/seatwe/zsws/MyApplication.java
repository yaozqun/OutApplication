package com.seatwe.zsws;

import android.app.Application;
import android.content.Context;

import com.grgbanking.baselib.util.ToastUtil;

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
        init();
    }

    public void init() {
        ToastUtil.init(getContext());
    }

    public synchronized static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}

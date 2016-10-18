package com.seatwe.zsws;

import android.app.Application;
import android.content.Context;

import com.grgbanking.baselib.config.AppConfig;
import com.grgbanking.baselib.util.DownloadUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.WebService;
import com.seatwe.zsws.constant.FilePathConfig;

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
        AppConfig.init(this, FilePathConfig.FILE_FOLDER, FilePathConfig.APK_NAME);
        WebService.init(this);
        DownloadUtil.init(this);
    }

    public synchronized static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
}

package com.seatwe.zsws.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.grgbanking.baselib.util.UpgradeUtil;
import com.grgbanking.baselib.web.bean.req.UpgradeReqBean;
import com.seatwe.zsws.constant.UrlConstant;

/**
 * Author：燕青 $ on 16/10/22 10:44
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SystemUtil {
    /**
     * 获取程序版本号(VersionCode)
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if (pi != null) {
                        return pi.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 版本更新
     *
     * @param context
     */
    public static void checkUpdate(Context context) {
        UpgradeReqBean req = new UpgradeReqBean();
        req.setApp_id(UrlConstant.APP_ID);
        req.setVersion(getAppVersionName(context));
        UpgradeUtil.init(context, UrlConstant.GET_APK_UPDATE, req, "版本更新", "早收晚收系统版本更新");
    }
}

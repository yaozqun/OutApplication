package com.seatwe.zsws.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.grgbanking.baselib.util.JsonUtils;
import com.grgbanking.baselib.util.ToastUtil;
import com.seatwe.zsws.MyApplication;
import com.seatwe.zsws.R;
import com.seatwe.zsws.model.UserInfo;

public class SharePrefUtil {
    private static SharedPreferences.Editor mEditor;

    private static SharedPreferences mPreferences;

    private static final String PREF_NAME = "app_data_cache";

    private static String USER_INFO = "userInfo";


    public static SharePrefUtil sharePrefUtil;

    private SharePrefUtil() {
        super();
        try {
            mPreferences = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        } catch (NullPointerException e) {
            ToastUtil.longShow(MyApplication.getContext().getResources().getString(R.string.comm_sharePre));
            e.printStackTrace();
        }
    }

    public static SharePrefUtil getInstance() {
        if (sharePrefUtil == null) {
            sharePrefUtil = new SharePrefUtil();
        }
        return sharePrefUtil;
    }

    public void saveUserInfo(UserInfo info) {
        String str = "";
        if (info != null) {
            str = JsonUtils.toJson(info);
        }
        mEditor.putString(USER_INFO, str);
        mEditor.commit();
    }

    public UserInfo getUserInfo() {
        String userInfoStr = mPreferences.getString(USER_INFO, "");
        UserInfo userInfo = JsonUtils.fromJson(userInfoStr, UserInfo.class);
        return userInfo;
    }

}

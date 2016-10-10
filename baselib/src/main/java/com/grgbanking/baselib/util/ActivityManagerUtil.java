package com.grgbanking.baselib.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ActivityManagerUtil {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}

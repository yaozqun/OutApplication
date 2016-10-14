package com.seatwe.zsws.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.ScanboxActivity;
import com.seatwe.zsws.ui.TaskDetailActivity;

/**
 * Created by Administrator on 2016/10/13.
 */
public class ActivityJumpUtil {
    public static final String INFO = "info";

    /**
     * 跳转到任务详情页面
     *
     * @param context
     * @param info
     */
    public static void jumpToTaskDetailActivity(Context context, TaskInfoData info) {
        Intent intent = new Intent(context, TaskDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INFO, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到扫描钞箱页面
     *
     * @param context
     * @param info
     */
    public static void jumpToScanboxActivity(Context context, TaskInfoData info) {
        Intent intent = new Intent(context, ScanboxActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INFO, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

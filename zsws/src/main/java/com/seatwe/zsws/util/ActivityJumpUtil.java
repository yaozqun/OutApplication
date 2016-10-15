package com.seatwe.zsws.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.SendBoxActivity;
import com.seatwe.zsws.ui.TaskDetailActivity;

/**
 * Created by Administrator on 2016/10/13.
 */
public class ActivityJumpUtil {
    public static final String INFO = "info";
    public static final String CHOOSEN_DATE = "chosen_date";
    public static final String CASHBOX_TYPE = "cashbox_type";

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
     * 跳转到送出款箱页面
     *
     * @param context
     * @param info
     */
    public static void jumpToSenBoxActivity(Context context, TaskInfoData info) {
        Intent intent = new Intent(context, SendBoxActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INFO, info);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到款箱页面
     *
     * @param context
     * @param info
     */
    public static void jumpToScanboxActivity(Context context, NetInfoData info, String chosenDate, String cashboxType) {
        Intent intent = new Intent(context, ScanboxActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INFO, info);
        intent.putExtra(CASHBOX_TYPE, cashboxType);
        intent.putExtra(CHOOSEN_DATE, chosenDate);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

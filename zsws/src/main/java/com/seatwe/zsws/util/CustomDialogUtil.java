package com.seatwe.zsws.util;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.ui.view.dialog.CustomDialog;
import com.grgbanking.baselib.util.ActivityManagerUtil;
import com.grgbanking.baselib.util.DialogUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.ui.LoginActivity;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import okhttp3.Call;

public class CustomDialogUtil {
    public static void loginOut(final Context context) {
        final CustomDialog dialog = DialogUtil.getDialog(context);
        dialog.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                loginOutNet();
                clearData();
                ActivityManagerUtil.finishAll();
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });

        dialog.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void loginOutNet() {
        NetService.getInstance().loginOut(new ResultCallback<ResponseBean>() {
            @Override
            public void onSuccess(ResponseBean resp) {
                ToastUtil.shortShow(resp.getMessage());
            }

            @Override
            public void onError(ErrorMsg errorMsg) {
                ToastUtil.shortShow(errorMsg.getMessage());
            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

    public static void clearData() {
        LineInfoBusinessUtil.getInstance().clearLineInfo();
        TaskInfoBusinessUtil.getInstance().clearTaskInfo();
        RecordBoxBusinessUtil.getInstance().clearrecordBoxInfo();
        LineNodeBusinessUtil.getInstance().clearLineNodeInfo();
    }
}

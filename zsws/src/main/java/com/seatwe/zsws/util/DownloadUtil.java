package com.seatwe.zsws.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import java.util.List;

import okhttp3.Call;

/**
 * Author：燕青 $ on 16/10/17 22:29
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class DownloadUtil {
    private static ProgressDialog progressDialog;

    public static void downloadData(Context context, LoginReqBean reqBean) {
        progressDialog = ProgressUtil.show(context, "登录中...");
        NetService.getInstance().login(reqBean,
                new ResultCallback<LoginRespBean>() {
                    @Override
                    public void onSuccess(LoginRespBean resp) {
                        progressDialog.dismiss();
                        // 保存数据
                        if (resp.getData() != null) {
                            LineInfoBusinessUtil.getInstance().saveLineInfoData(resp.getData());
                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onPre(Call call) {
                        progressDialog.dismiss();
                    }
                });
    }

    /**
     * 下载任务信息
     */
    public static void downloadTaskInfo() {
        NetService.getInstance().downTask(new ResultCallback<List<TaskInfoData>>() {
            @Override
            public void onSuccess(List<TaskInfoData> resp) {
                TaskInfoBusinessUtil.getInstance().saveTaskInfoData(resp);
            }

            @Override
            public void onError(ErrorMsg errorMsg) {

            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

    /**
     * 下载钞箱信息
     */
    public static void downloadCashBox() {
        NetService.getInstance().downCashboxInfo(new ResultCallback<List<CashBoxData>>() {
            @Override
            public void onSuccess(List<CashBoxData> resp) {

            }

            @Override
            public void onError(ErrorMsg errorMsg) {

            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

    /**
     * 下载网点信息
     */
    public static void downloadNetInfo(){
        NetService.getInstance().downNetInfo(new ResultCallback<List<NetInfoData>>() {
            @Override
            public void onSuccess(List<NetInfoData> resp) {

            }

            @Override
            public void onError(ErrorMsg errorMsg) {

            }

            @Override
            public void onPre(Call call) {

            }
        });
    }
}

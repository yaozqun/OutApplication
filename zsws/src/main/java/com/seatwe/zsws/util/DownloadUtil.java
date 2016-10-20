package com.seatwe.zsws.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.resp.CashBoxRespBean;
import com.grgbanking.baselib.web.bean.resp.NetInfoRespBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.bean.resp.TaskInfoRespBean;
import com.seatwe.zsws.model.UserInfo;
import com.seatwe.zsws.ui.MainActivity;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import okhttp3.Call;
public class DownloadUtil {
    private static ProgressDialog progressDialog;

    public static void login(final Context context, final LoginReqBean reqBean) {
        progressDialog = ProgressUtil.show(context, "登录中...");
        NetService.getInstance().login(reqBean,
                new ResultCallback<LoginRespBean>() {
                    @Override
                    public void onSuccess(LoginRespBean resp) {
                        progressDialog.dismiss();
                        // 保存数据
                        if (resp.getData() != null) {
                            LineInfoBusinessUtil.getInstance().saveLineInfoData(resp.getData());
                            //将用户信息保存到SP中
                            UserInfo userInfo = new UserInfo();
                            userInfo.setLogin_name(reqBean.getLogin_name());
                            userInfo.setLogin_password(reqBean.getLogin_password());
                            userInfo.setLine_id(resp.getData().getId());
                            SharePrefUtil.getInstance().saveUserInfo(userInfo);

                            if (LineInfoBusinessUtil.getInstance().queryAllLineInfo() == null) {
                                downloadCashBox(context);//下载钞箱信息
                            } else {
                                if (!resp.getData().getId().equals(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId())) {
                                    CashboxBaseBusinessUtil.getInstance().clearCashboxInfo();
                                    NetInfoBusinessUtil.getInstance().clearNetInfo();
                                    LineInfoBusinessUtil.getInstance().clearLineInfo();
                                    LineNodeBusinessUtil.getInstance().clearLineNodeInfo();
                                    TaskInfoBusinessUtil.getInstance().clearTaskInfo();

                                    downloadCashBox(context);//下载钞箱信息
                                }

                            }

                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        ToastUtil.shortShow("登录失败");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onPre(Call call) {
                        progressDialog.dismiss();
                    }
                });
    }

    /**
     * 下载钞箱信息
     */
    public static void downloadCashBox(final Context context) {
        progressDialog .setMessage("下载钞箱信息中...");
        NetService.getInstance().downCashboxInfo(new ResultCallback<CashBoxRespBean>() {
            @Override
            public void onSuccess(CashBoxRespBean resp) {
                CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(resp.getData());
                downloadNetInfo(context);
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
    public static void downloadNetInfo(final Context context) {
        progressDialog .setMessage("下载网点信息中...");
        NetService.getInstance().downNetInfo(new ResultCallback<NetInfoRespBean>() {
            @Override
            public void onSuccess(NetInfoRespBean resp) {
                NetInfoBusinessUtil.getInstance().saveNetInfoData(resp.getData());
                downloadTaskInfo(context);
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
     * 下载任务信息
     */
    public static void downloadTaskInfo(final Context context) {
        progressDialog .setMessage("下载任务信息中...");
        NetService.getInstance().downTask(new ResultCallback<TaskInfoRespBean>() {
            @Override
            public void onSuccess(TaskInfoRespBean resp) {
                TaskInfoBusinessUtil.getInstance().saveTaskInfoData(resp.getData());
                context.startActivity(new Intent(context, MainActivity.class));
                progressDialog.dismiss();
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


}

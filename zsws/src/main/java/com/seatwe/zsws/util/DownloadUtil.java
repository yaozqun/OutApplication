package com.seatwe.zsws.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.resp.CashBoxRespBean;
import com.grgbanking.baselib.web.bean.resp.NetInfoRespBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.bean.resp.TaskInfoRespBean;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LineNodeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.model.UserInfo;
import com.seatwe.zsws.ui.MainActivity;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import java.util.ArrayList;
import java.util.List;

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
                        ToastUtil.shortShow(resp.getMessage());
                        // 保存数据
                        if (resp.getData() != null) {

                            //将用户信息保存到SP中
                            UserInfo userInfo = new UserInfo();
                            userInfo.setLogin_name(reqBean.getLogin_name());
                            userInfo.setLogin_password(reqBean.getLogin_password());
                            userInfo.setLine_id(resp.getData().getId());
                            SharePrefUtil.getInstance().saveUserInfo(userInfo);

                            if (LineInfoBusinessUtil.getInstance().queryAllLineInfo() == null) {
                                LineInfoBusinessUtil.getInstance().saveLineInfoData(resp.getData());
                                LogUtil.e("线路信息", "线路编号：" + LineInfoBusinessUtil.getInstance().queryAllLineInfo().getLine_number());

                                downloadCashBox(context);//下载钞箱信息
                            } else {
                                if (!resp.getData().getId().equals(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId())) {
                                    CashboxBaseBusinessUtil.getInstance().clearCashboxInfo();
                                    NetInfoBusinessUtil.getInstance().clearNetInfo();
                                    LineInfoBusinessUtil.getInstance().clearLineInfo();
                                    LineNodeBusinessUtil.getInstance().clearLineNodeInfo();
                                    TaskInfoBusinessUtil.getInstance().clearTaskInfo();

                                    LineInfoBusinessUtil.getInstance().saveLineInfoData(resp.getData());
                                    LogUtil.e("线路信息", "线路编号：" + LineInfoBusinessUtil.getInstance().queryAllLineInfo().getLine_number());

                                    downloadCashBox(context);//下载钞箱信息
                                } else {
                                    context.startActivity(new Intent(context, MainActivity.class));
                                }

                            }

                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        ToastUtil.shortShow(errorMsg.getMessage());
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
        progressDialog.setMessage("下载钞箱信息中...");
        NetService.getInstance().downCashboxInfo(new ResultCallback<CashBoxRespBean>() {
            @Override
            public void onSuccess(CashBoxRespBean resp) {
                CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(resp.getData());
                LogUtil.e("钞箱信息", "网点编号：" + CashboxBaseBusinessUtil.getInstance().queryAllCashboxInfo().get(0).getCashbox_num());

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
        progressDialog.setMessage("下载网点信息中...");
        NetService.getInstance().downNetInfo(new ResultCallback<NetInfoRespBean>() {
            @Override
            public void onSuccess(NetInfoRespBean resp) {
                NetInfoBusinessUtil.getInstance().saveNetInfoData(resp.getData());
                LogUtil.e("网点信息", "网点名称：" + NetInfoBusinessUtil.getInstance().queryAllNetInfo().get(0).getNet_name());

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
        progressDialog.setMessage("下载任务信息中...");
        NetService.getInstance().downTask(new ResultCallback<TaskInfoRespBean>() {
            @Override
            public void onSuccess(TaskInfoRespBean resp) {
                TaskInfoBusinessUtil.getInstance().saveTaskInfoData(resp.getData());
                LogUtil.e("任务信息", "钞箱编号：" + TaskInfoBusinessUtil.getInstance().queryAllTaskInfo().get(0).getCashbox_num());
                //任务信息下载完成后,将钞箱信息保存到钞箱操作记录表中
                for (TaskInfoData data : resp.getData()) {
                    RecordboxInfoData info = new RecordboxInfoData();
                    info.setBox_code(data.getCashbox_num());
                    info.setTransfer_net(data.getNet_id());
                    info.setTransfer_status(data.getTask_status());
                    info.setCashbox_type(CashboxTypeConstant.TYPE_SEND);
                    info.setTransfer_type(data.getTask_type());
                    info.setId(CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByCode(data.getCashbox_num()).getId());
                    RecordBoxBusinessUtil.getInstance().createOrUpdate(info);
                }
                LogUtil.e("钞箱记录信息", "钞箱编号：" + RecordBoxBusinessUtil.getInstance().queryAllRecordbox().get(0).getBox_code());

                saveLineNodeInfo(context);
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

    /**
     * 保存节点信息
     * @param context
     */
    public static void saveLineNodeInfo(Context context) {
        //保存节点信息
        List<ArriveNodeReqBean> list5 = new ArrayList<ArriveNodeReqBean>();
        String[] nodeName = new String[]{context.getResources().getString(R.string.node_name_start), context.getResources().getString(R.string.node_name_end)};
        for (int i = 0; i < nodeName.length; i++) {
            ArriveNodeReqBean bean = new ArriveNodeReqBean();
            bean.setNode_name(nodeName[i]);
            bean.setLine_id(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId());
            bean.setLocalStatus(LocalStatusConstant.UN_DONE);
            bean.setLine_id(i + "");
            bean.setNode_type(i + "");
            bean.setCode(i == 0 ? LineNodeConstant.NODE_START : LineNodeConstant.NODE_END);
            list5.add(bean);
        }
        LineNodeBusinessUtil.getInstance().saveLineNodeInfoData(list5);
        LogUtil.e("线路节点信息", "线路节点名称：" + LineNodeBusinessUtil.getInstance().queryAllLineNodeInfo().get(0).getNode_name());
    }

}

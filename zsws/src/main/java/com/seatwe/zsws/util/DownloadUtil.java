package com.seatwe.zsws.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.ui.view.loading.ShapeLoadingDialog;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.LoginReqBean;
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
    //    private static ProgressDialog progressDialog;
    private static DownloadUtil instance;
    private static ShapeLoadingDialog loadingDialog;
    private static Context context;

    public static synchronized DownloadUtil getInstance() {
        if (instance == null) {
            instance = new DownloadUtil();
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        loadingDialog = new ShapeLoadingDialog(context);
        loadingDialog.setCancelable(true);
        loadingDialog.setButtonText("拼命下载中");
        loadingDialog.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    // 显示 loading 对话框
    private static void showLoadingDialog(String msg, boolean showProgressBar) {
        loadingDialog.setLoadingText(msg);
        loadingDialog.showProgressBar(showProgressBar);
        loadingDialog.setProgress(0);
        loadingDialog.showButton(showProgressBar);
        loadingDialog.show();
    }

    /**
     * 登录
     *
     * @param reqBean
     */
    public void login(final LoginReqBean reqBean) {
        showLoadingDialog(context.getResources().getString(R.string.logining), false);
        NetService.getInstance().login(reqBean,
                new ResultCallback<LineInfoData>() {
                    @Override
                    public void onSuccess(LineInfoData resp) {
                        ToastUtil.shortShow("登录成功");
                        // 保存数据
                        if (resp != null) {

                            //将用户信息保存到SP中
                            UserInfo userInfo = new UserInfo();
                            userInfo.setLogin_name(reqBean.getLogin_name());
                            userInfo.setLogin_password(reqBean.getLogin_password());
                            userInfo.setLine_id(resp.getId());
                            SharePrefUtil.getInstance().saveUserInfo(userInfo);

                            if (LineInfoBusinessUtil.getInstance().queryAllLineInfo() == null) {
                                LineInfoBusinessUtil.getInstance().saveLineInfoData(resp);
                                LogUtil.e("线路信息", "线路编号：" + LineInfoBusinessUtil.getInstance().queryAllLineInfo().getLine_number());

                                downloadCashBox();//下载钞箱信息
                            } else {
                                if (!resp.getId().equals(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId())) {
                                    LineInfoBusinessUtil.getInstance().clearLineInfo();
                                    LineNodeBusinessUtil.getInstance().clearLineNodeInfo();
                                    TaskInfoBusinessUtil.getInstance().clearTaskInfo();

                                    LineInfoBusinessUtil.getInstance().saveLineInfoData(resp);
                                    LogUtil.e("线路信息", "线路编号：" + LineInfoBusinessUtil.getInstance().queryAllLineInfo().getLine_number());

                                    downloadCashBox();//下载钞箱信息

                                } else {
                                    loadingDialog.dismiss();
                                    context.startActivity(new Intent(context, MainActivity.class));
                                }

                            }

                        } else {

                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        loadingDialog.dismiss();
                        LineInfoBusinessUtil.getInstance().clearLineInfo();
                        ToastUtil.shortShow(errorMsg.getMessage());
                    }

                    @Override
                    public void onPre(Call call) {
                    }
                });
    }

    /**
     * 下载钞箱信息
     */
    public void downloadCashBox() {
        showLoadingDialog(context.getString(R.string.downloading_cashbox_info), false);
        NetService.getInstance().downCashboxInfo(new ResultCallback<List<CashBoxData>>() {
            @Override
            public void onSuccess(List<CashBoxData> resp) {
                if (resp != null && resp.size() > 0) {
                    CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(resp);
                    LogUtil.e("钞箱信息", "网点编号：" + CashboxBaseBusinessUtil.getInstance().queryAllCashboxInfo().get(0).getCashbox_num());
                }

                downloadNetInfo();
            }

            @Override
            public void onError(ErrorMsg errorMsg) {
                ToastUtil.shortShow(errorMsg.getMessage());
                downloadNetInfo();

            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

    /**
     * 下载网点信息
     */
    public void downloadNetInfo() {
        showLoadingDialog(context.getString(R.string.downloading_net_info), false);
        NetService.getInstance().downNetInfo(new ResultCallback<List<NetInfoData>>() {
            @Override
            public void onSuccess(List<NetInfoData> resp) {
                if (resp != null && resp.size() > 0) {
                    NetInfoBusinessUtil.getInstance().saveNetInfoData(resp);
                    LogUtil.e("网点信息", "网点名称：" + NetInfoBusinessUtil.getInstance().queryAllNetInfo().get(0).getNet_name());

                }

                downloadTaskInfo();
            }

            @Override
            public void onError(ErrorMsg errorMsg) {
                ToastUtil.shortShow(errorMsg.getMessage());
                downloadTaskInfo();
            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

    /**
     * 下载任务信息
     */
    public void downloadTaskInfo() {
        showLoadingDialog(context.getString(R.string.downloading_task_info), false);
        NetService.getInstance().downTask(new ResultCallback<List<TaskInfoData>>() {
            @Override
            public void onSuccess(List<TaskInfoData> resp) {
                loadingDialog.dismiss();
                if (resp != null && resp.size() > 0) {
                    TaskInfoBusinessUtil.getInstance().saveTaskInfoData(resp);
                    LogUtil.e("任务信息", "网点编号：" + TaskInfoBusinessUtil.getInstance().queryAllTaskInfo().get(0).getNet_id());
                    //任务信息下载完成后,将钞箱信息保存到钞箱操作记录表中
                    for (TaskInfoData data : resp) {
                        RecordboxInfoData info = new RecordboxInfoData();
                        info.setBox_code(data.getCashbox_num());
                        info.setTransfer_net(data.getNet_id());
                        info.setTransfer_status(data.getTask_status());
                        info.setCashbox_type(CashboxTypeConstant.TYPE_SEND);
                        info.setTransfer_type(data.getTask_type());
                        info.setId(data.getCashbox_num() == null ? "" : CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByCode(data.getCashbox_num()).getId());
                        RecordBoxBusinessUtil.getInstance().createOrUpdate(info);
                    }
                    LogUtil.e("钞箱记录信息", "钞箱编号：" + RecordBoxBusinessUtil.getInstance().queryAllRecordbox().get(0).getTransfer_net());

                    saveLineNodeInfo();
                    context.startActivity(new Intent(context, MainActivity.class));

                }

            }

            @Override
            public void onError(ErrorMsg errorMsg) {
                loadingDialog.dismiss();
                LineInfoBusinessUtil.getInstance().clearLineInfo();
                ToastUtil.shortShow(errorMsg.getMessage());
            }

            @Override
            public void onPre(Call call) {
            }
        });
    }

    /**
     * 保存节点信息
     */
    public void saveLineNodeInfo() {
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

package com.seatwe.zsws.util;

import android.content.Context;
import android.view.View;

import com.grgbanking.baselib.ui.view.dialog.CustomDialog;
import com.grgbanking.baselib.util.DateTimeUtil;
import com.grgbanking.baselib.util.DialogUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LineNodeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.constant.TransferTypeConstant;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;

import java.util.Date;
import java.util.List;

public class BarcodeScannedUtil {

    /**
     * 送出钞箱扫描
     *
     * @param context
     * @param listCashbox
     * @param barcodeStr
     * @return
     */
    public static int scannedSuccess(Context context, List<RecordboxInfoData> listCashbox, String barcodeStr) {
        int count = 0;
        boolean result = false;
        for (RecordboxInfoData data : listCashbox) {
            if (barcodeStr.equals(data.getBox_code())) {
                if (data.getLocalStatus() == LocalStatusConstant.UN_DONE) {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scan_success));
                    result = true;
                    data.setLocalStatus(LocalStatusConstant.DONE);
                    RecordBoxBusinessUtil.getInstance().createOrUpdate(data);//将钞箱状态保存到数据库
                    break;
                } else {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_already));
                    result = false;
                    break;
                }
            } else {
                count++;
                if (count == listCashbox.size()) {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_error));
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            return count;
        } else {
            return -1;
        }
    }


    /**
     * 任务扫描
     *
     * @param context
     * @param list
     * @param barcodeStr
     * @return
     */
    public static int scannedTaskSuccess(Context context, List<TaskInfoData> list, String barcodeStr) {
        int count = 0;
        boolean result = false;
        for (TaskInfoData data : list) {
            String netCode = NetInfoBusinessUtil.getInstance().queryNetInfoById(data.getNet_id()).getNet_code();
            if (barcodeStr.equals(netCode)) {
                if (data.getLocalStatus() != LocalStatusConstant.UPLOADED) {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scan_success));
                    result = true;
                    data.setLocalStatus(LocalStatusConstant.DONE);
                    data.setArriveTime(DateTimeUtil.formatTime(new Date()));
                    TaskInfoBusinessUtil.getInstance().createOrUpdate(data);//将任务状态保存到数据库
                    break;
                } else {
                    ToastUtil.shortShow(context.getResources().getString(R.string.uploaded_already));
                    result = false;
                    break;
                }
            } else {
                count++;
                if (count == list.size()) {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_error));
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            return count;
        } else {
            return -1;
        }
    }


    /**
     * 收取或中调钞箱信息处理
     *
     * @param context
     * @param listCashbox
     * @param netInfoData
     * @param date
     * @param cashboxType
     * @param barcodeStr
     * @return
     */
    public static RecordboxInfoData receivedOrMiddle(Context context, List<RecordboxInfoData> listCashbox, NetInfoData netInfoData, String date, String cashboxType, String barcodeStr) {
        int count = 0;
        RecordboxInfoData infoData = new RecordboxInfoData();
        if (cashboxType.equals(CashboxTypeConstant.TYPE_MIDDLE)) {
            infoData.setIsMiddle("1");
        } else {
            infoData.setIsMiddle("0");
        }
        if (CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByCode(barcodeStr) != null) {
            if (listCashbox.size() == 0) {
                infoData.setLocalStatus(LocalStatusConstant.DONE);
                infoData.setTransfer_type(TransferTypeConstant.NET_TYPE_00_IN);
                infoData.setTransfer_status("0");
                infoData.setCashbox_type(cashboxType);
                infoData.setBox_code(barcodeStr);
                infoData.setTransfer_net(netInfoData.getId());
                infoData.setTransfer_time(date);

                RecordBoxBusinessUtil.getInstance().createOrUpdate(infoData);//保存到数据库
            } else {
                for (RecordboxInfoData data : listCashbox) {
                    if (barcodeStr.equals(data.getBox_code())) {
                        ToastUtil.shortShow(context.getResources().getString(R.string.scanned_already));
                        break;
                    } else {
                        count++;
                        if (count == listCashbox.size()) {
                            infoData.setLocalStatus(LocalStatusConstant.DONE);
                            infoData.setTransfer_type(TransferTypeConstant.NET_TYPE_00_IN);
                            infoData.setTransfer_status("0");
                            infoData.setCashbox_type(cashboxType);
                            infoData.setBox_code(barcodeStr);
                            infoData.setTransfer_net(netInfoData.getId());
                            infoData.setTransfer_time(date);

                            RecordBoxBusinessUtil.getInstance().createOrUpdate(infoData);//保存到数据库
                            break;
                        }
                    }
                }
            }
        } else {
            ToastUtil.shortShow(context.getResources().getString(R.string.scanned_error));
        }
        return infoData;
    }

    /**
     * 节点扫描
     *
     * @param context
     * @param listNode
     * @param barcodeStr
     * @return
     */
    public static int scannedNodeSuccess(Context context, List<ArriveNodeReqBean> listNode, String barcodeStr) {
        int count = 0;
        boolean result = false;
        for (ArriveNodeReqBean data : listNode) {
            if (barcodeStr.equals(data.getCode())) {
                if (data.getLocalStatus() == LocalStatusConstant.UN_DONE) {
//                    if (barcodeStr.equals(LineNodeConstant.NODE_END) && listNode.get(0).getLocalStatus() == LocalStatusConstant.UN_DONE) {
//                        result = showDialog(context, data);
//                    }
                    ToastUtil.shortShow(context.getResources().getString(R.string.scan_success));
                    result = true;
                    data.setLocalStatus(LocalStatusConstant.DONE);
                    LineNodeBusinessUtil.getInstance().createOrUpdate(data);//将钞箱状态保存到数据库
                    break;
                } else {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_already));
                    result = false;
                    break;
                }
            } else {
                count++;
                if (count == listNode.size()) {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_error));
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            return count;
        } else {
            return -1;
        }
    }

    /**
     * @param context
     * @param data
     * @return
     */
    public static boolean showDialog(final Context context, final ArriveNodeReqBean data) {
        final boolean[] result = {false};
        final CustomDialog customDialog = DialogUtil.getDialog(context);
        customDialog.setCancelable(false);//不能手动取消
        customDialog.withMessage(context.getResources().getString(R.string.start_node_not_scanned)).withButton1Text(context.getResources().getString(R.string.continue_scan))
                .withButton2Text(context.getResources().getString(R.string.cancel)).show();

        customDialog.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.shortShow(context.getResources().getString(R.string.scan_success));
                result[0] = true;
                data.setLocalStatus(LocalStatusConstant.DONE);
                LineNodeBusinessUtil.getInstance().createOrUpdate(data);//将钞箱状态保存到数据库
                customDialog.dismiss();
            }
        });
        return result[0];
    }
}

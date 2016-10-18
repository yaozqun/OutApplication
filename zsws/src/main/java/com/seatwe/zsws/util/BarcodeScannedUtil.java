package com.seatwe.zsws.util;

import android.content.Context;

import com.grgbanking.baselib.util.DateTimeUtil;
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

/**
 * Author：燕青 $ on 16/10/15 16:42
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class BarcodeScannedUtil {
    /**
     * 钞箱扫描
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
                    ToastUtil.shortShow("扫描成功");
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
     * 钞箱扫描
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
            String netCode = NetInfoBusinessUtil.getInstance().queryNetInfoById(data.getId()).getNet_code();
            if (barcodeStr.equals(netCode)) {
                if (data.getLocalStatus() == LocalStatusConstant.UN_DONE) {
                    ToastUtil.shortShow("扫描成功");
                    result = true;
                    data.setLocalStatus(LocalStatusConstant.DONE);
                    data.setArriveTime(DateTimeUtil.formatTime(new Date()));
                    TaskInfoBusinessUtil.getInstance().createOrUpdate(data);//将任务状态保存到数据库
                    break;
                } else {
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_already));
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
     * 钞箱扫描
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
                    ToastUtil.shortShow("扫描成功");
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

}

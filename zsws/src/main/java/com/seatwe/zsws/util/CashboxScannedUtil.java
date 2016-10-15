package com.seatwe.zsws.util;

import android.content.Context;

import com.grgbanking.baselib.util.ToastUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

/**
 * Author：燕青 $ on 16/10/15 16:42
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class CashboxScannedUtil {
    public static int scannedSuccess(Context context, List<RecordboxInfoData> listCashbox, String barcodeStr) {
        int count = 0;
        boolean result = false;
        for (RecordboxInfoData data : listCashbox) {
            if (barcodeStr.equals(data.getBox_code())){
                if(data.getLocalStatus() == LocalStatusConstant.UN_DONE){
                    ToastUtil.shortShow("扫描成功");
                    result = true;
                    data.setLocalStatus(LocalStatusConstant.DONE);
                    RecordBoxBusinessUtil.getInstance().createOrUpdate(data);//将钞箱状态保存到数据库
                    break;
                }else{
                    ToastUtil.shortShow(context.getResources().getString(R.string.scanned_already));
                    result = false;
                    break;
                }
            }else{
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

}

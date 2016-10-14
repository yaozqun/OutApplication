package com.seatwe.zsws.util.db;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.db.service.impl.CashboxInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.LineInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.LineNodeInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.NetInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.TaskInfoServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class CashboxBaseBusinessUtil {
    private static CashboxBaseBusinessUtil instance;
    private static CashboxInfoServiceImpl cashboxInfoService;

    public static synchronized CashboxBaseBusinessUtil getInstance() {
        if (instance == null) {
            instance = new CashboxBaseBusinessUtil();
            cashboxInfoService = new CashboxInfoServiceImpl(CashBoxData.class);
        }
        return instance;
    }

    /**
     * 保存钞箱信息
     *
     * @param data
     */
    public void saveCashboxInfoData(List<CashBoxData> data) {
        try {
            cashboxInfoService.saveCashboxInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询钞箱信息
     */
    public List<CashBoxData> queryCashboxInfoByNetId(int netId) {
        try {
            return cashboxInfoService.queryCashboxInfoByNetId(netId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询钞箱信息
     */
    public List<CashBoxData> queryCashboxInfo() {
        try {
            return cashboxInfoService.queryCashboxInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除任务信息
     */
    public void clearCashboxInfo() {
        try {
            cashboxInfoService.delete(queryCashboxInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

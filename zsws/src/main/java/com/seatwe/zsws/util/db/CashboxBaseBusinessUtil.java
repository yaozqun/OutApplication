package com.seatwe.zsws.util.db;

import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.db.service.impl.CashboxInfoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CashBoxData> queryCashboxInfoByNetId(String netId) {
        return cashboxInfoService.queryCashboxInfoByNetId(netId);
    }

    /**
     * 查询钞箱信息
     */
    public List<CashBoxData> queryAllCashboxInfo() {
        try {
            return cashboxInfoService.queryCashboxInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 删除任务信息
     */
    public void clearCashboxInfo() {
        try {
            cashboxInfoService.delete(queryAllCashboxInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据钞箱编号查询钞箱信息
     *
     * @param code
     * @return
     */
    public CashBoxData queryCashboxInfoByCode(String code) {
        LogUtil.e("code", code);
        List<CashBoxData> list = cashboxInfoService.queryCashboxInfoByCode(code);
        LogUtil.e("list", list.size()+"");
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * 查询钞箱版本号
     *
     * @return
     */
    public String queryCurrentVersion() {
        try {
            List<CashBoxData> list = cashboxInfoService.queryForAll();
            if(list!=null&&list.size()>0){
                return list.get(0).getVersion();
            }else{
                return "0";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
        }
    }
}

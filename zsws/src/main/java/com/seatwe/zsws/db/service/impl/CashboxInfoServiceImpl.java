package com.seatwe.zsws.db.service.impl;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ICashboxInfoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashboxInfoServiceImpl extends BaseDbServiceImpl<CashBoxData>
        implements ICashboxInfoService {
    public CashboxInfoServiceImpl(Class<CashBoxData> classEntity) {
        super(classEntity);
    }

    public CashboxInfoServiceImpl(Class<CashBoxData> classEntity,
                                  BaseDbTransactionImpl baseDbTransactionImpl) {
        super(classEntity, baseDbTransactionImpl);
    }

    @Override
    public void saveCashboxInfo(List<CashBoxData> resp) throws SQLException {
        create(resp);// 保存数据
    }

    public List<CashBoxData> queryCashboxInfo() throws SQLException {
        return dao.queryForAll();
    }

    public List<CashBoxData> queryCashboxInfoByNetId(String netId) {
        List<CashBoxData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("net_id", netId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CashBoxData> queryCashboxInfoByCode(String cashbox_num) {
        List<CashBoxData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("cashbox_num", cashbox_num).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

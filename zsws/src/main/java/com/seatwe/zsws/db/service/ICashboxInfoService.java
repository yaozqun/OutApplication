package com.seatwe.zsws.db.service;

import java.sql.SQLException;
import java.util.List;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.db.IBaseDbService;

public interface ICashboxInfoService extends IBaseDbService<CashBoxData,Integer> {
    public abstract void saveCashboxInfo(List<CashBoxData> resp) throws SQLException;
}

package com.seatwe.zsws.db.dao.impl;


import com.seatwe.zsws.db.BaseDbDaoImpl;
import com.seatwe.zsws.db.dao.ICashboxInfoDao;
import com.seatwe.zsws.db.dao.ILineInfoDao;

public class CashboxInfoDaoImpl<CashBoxData> extends BaseDbDaoImpl<CashBoxData> implements
        ICashboxInfoDao<CashBoxData> {

    public CashboxInfoDaoImpl(Class<CashBoxData> classOfT) {
        super(classOfT);
    }

}

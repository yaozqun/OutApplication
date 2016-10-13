package com.seatwe.zsws.db.dao.impl;


import com.seatwe.zsws.db.BaseDbDaoImpl;
import com.seatwe.zsws.db.dao.ILineInfoDao;

public class RecordboxInfoDaoImpl<RecorderBoxData> extends BaseDbDaoImpl<RecorderBoxData> implements
        ILineInfoDao<RecorderBoxData> {

    public RecordboxInfoDaoImpl(Class<RecorderBoxData> classOfT) {
        super(classOfT);
    }

}

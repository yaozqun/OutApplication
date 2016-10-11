package com.seatwe.zsws.db.dao.impl;


import com.seatwe.zsws.db.BaseDbDaoImpl;
import com.seatwe.zsws.db.dao.ILineInfoDao;

public class TaskInfoDaoImpl<LineInfoData> extends BaseDbDaoImpl<LineInfoData> implements
        ILineInfoDao<LineInfoData> {

    public TaskInfoDaoImpl(Class<LineInfoData> classOfT) {
        super(classOfT);
    }

}

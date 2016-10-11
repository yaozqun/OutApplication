package com.seatwe.zsws.db.dao.impl;


import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.db.BaseDbDaoImpl;
import com.seatwe.zsws.db.dao.ILineInfoDao;

public class LineInfoDaoImpl<LineInfoData> extends BaseDbDaoImpl<LineInfoData> implements
        ILineInfoDao<LineInfoData> {

    public LineInfoDaoImpl(Class<LineInfoData> classOfT) {
        super(classOfT);
    }

}

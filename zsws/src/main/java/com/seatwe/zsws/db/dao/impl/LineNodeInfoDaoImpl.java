package com.seatwe.zsws.db.dao.impl;


import com.seatwe.zsws.db.BaseDbDaoImpl;
import com.seatwe.zsws.db.dao.ILineInfoDao;

public class LineNodeInfoDaoImpl<ArriveNodeReqBean> extends BaseDbDaoImpl<ArriveNodeReqBean> implements
        ILineInfoDao<ArriveNodeReqBean> {

    public LineNodeInfoDaoImpl(Class<ArriveNodeReqBean> classOfT) {
        super(classOfT);
    }

}

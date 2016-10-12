package com.seatwe.zsws.db.service;

import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.db.IBaseDbService;

import java.sql.SQLException;
import java.util.List;

public interface ILineNodeInfoService extends IBaseDbService<ArriveNodeReqBean,Integer> {
    public abstract void saveLineNodeInfo(List<ArriveNodeReqBean> resp) throws SQLException;

}

package com.seatwe.zsws.db.service;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.db.IBaseDbService;

import java.sql.SQLException;
import java.util.List;

public interface INetInfoService extends IBaseDbService<NetInfoData,Integer> {
    public abstract void saveNetInfo(List<NetInfoData> resp) throws SQLException;
}

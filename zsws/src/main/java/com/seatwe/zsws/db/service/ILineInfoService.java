package com.seatwe.zsws.db.service;

import java.sql.SQLException;

import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.db.IBaseDbService;

public interface ILineInfoService extends IBaseDbService<LineInfoData,Integer> {
    public abstract void saveLineInfo(LineInfoData resp) throws SQLException;

}

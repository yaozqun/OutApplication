package com.seatwe.zsws.db.service;

import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.db.IBaseDbService;

import java.sql.SQLException;
import java.util.List;

public interface IRecordboxInfoService extends IBaseDbService<RecordboxInfoData,Integer> {
    public abstract void saveRecordBoxInfo(List<RecordboxInfoData> resp) throws SQLException;
}

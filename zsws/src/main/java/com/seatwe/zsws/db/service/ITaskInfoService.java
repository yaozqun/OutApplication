package com.seatwe.zsws.db.service;

import java.sql.SQLException;
import java.util.List;

import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.db.IBaseDbService;

public interface ITaskInfoService extends IBaseDbService<TaskInfoData,Integer> {
    public abstract void saveLineInfo(List<TaskInfoData> resp) throws SQLException;
}

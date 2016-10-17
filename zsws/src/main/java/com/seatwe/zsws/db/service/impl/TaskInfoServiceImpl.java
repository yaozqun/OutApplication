package com.seatwe.zsws.db.service.impl;

import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ITaskInfoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskInfoServiceImpl extends BaseDbServiceImpl<TaskInfoData>
    implements ITaskInfoService {
  public TaskInfoServiceImpl(Class<TaskInfoData> classEntity) {
    super(classEntity);
  }

  public TaskInfoServiceImpl(Class<TaskInfoData> classEntity,
      BaseDbTransactionImpl baseDbTransactionImpl) {
    super(classEntity, baseDbTransactionImpl);
  }

  @Override
  public void saveLineInfo(List<TaskInfoData> resp) throws SQLException {
    create(resp);// 保存数据
  }

  public List<TaskInfoData> queryTaskInfoByNetId(int netId) {
    List<TaskInfoData>list = new ArrayList<>();
    try {
      list = dao.getQueryBuilder().where().eq("net_id", netId).query();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<TaskInfoData> queryTaskInfoByLocalStatsu(int localStatus) {
    List<TaskInfoData>list = new ArrayList<>();
    try {
      list = dao.getQueryBuilder().where().eq("local_status", localStatus).query();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }



}

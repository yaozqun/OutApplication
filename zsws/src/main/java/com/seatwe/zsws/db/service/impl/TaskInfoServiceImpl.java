package com.seatwe.zsws.db.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ILineInfoService;
import com.seatwe.zsws.db.service.ITaskInfoService;

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

  public List<TaskInfoData> queryTaskInfo() throws SQLException {
    return dao.queryForAll();
  }
}

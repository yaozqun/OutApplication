package com.seatwe.zsws.db.service.impl;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.INetInfoService;

import java.sql.SQLException;
import java.util.List;

public class NetInfoServiceImpl extends BaseDbServiceImpl<NetInfoData>
    implements INetInfoService {
  public NetInfoServiceImpl(Class<NetInfoData> classEntity) {
    super(classEntity);
  }

  public NetInfoServiceImpl(Class<NetInfoData> classEntity,
                            BaseDbTransactionImpl baseDbTransactionImpl) {
    super(classEntity, baseDbTransactionImpl);
  }

  @Override
  public void saveNetInfo(List<NetInfoData> resp) throws SQLException {
    create(resp);// 保存数据
  }

  public List<NetInfoData> queryNetInfo() throws SQLException {
    return dao.queryForAll();
  }
}

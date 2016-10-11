package com.seatwe.zsws.db.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ICashboxInfoService;

public class CashboxInfoServiceImpl extends BaseDbServiceImpl<CashBoxData>
    implements ICashboxInfoService {
  public CashboxInfoServiceImpl(Class<CashBoxData> classEntity) {
    super(classEntity);
  }

  public CashboxInfoServiceImpl(Class<CashBoxData> classEntity,
                                BaseDbTransactionImpl baseDbTransactionImpl) {
    super(classEntity, baseDbTransactionImpl);
  }

  @Override
  public void saveCashboxInfo(List<CashBoxData> resp) throws SQLException {
    create(resp);// 保存数据
  }

  public List<CashBoxData> queryCashboxInfo() throws SQLException {
    return dao.queryForAll();
  }
}

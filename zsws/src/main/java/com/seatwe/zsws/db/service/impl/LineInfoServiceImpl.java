package com.seatwe.zsws.db.service.impl;

import com.grgbanking.baselib.util.JsonUtils;
import com.seatwe.zsws.TestDatas;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ILineInfoService;

import java.sql.SQLException;

public class LineInfoServiceImpl extends BaseDbServiceImpl<LineInfoData>
    implements ILineInfoService {
  public LineInfoServiceImpl(Class<LineInfoData> classEntity) {
    super(classEntity);
  }

  public LineInfoServiceImpl(Class<LineInfoData> classEntity,
      BaseDbTransactionImpl baseDbTransactionImpl) {
    super(classEntity, baseDbTransactionImpl);
  }

  @Override
  public void saveLineInfo(LineInfoData resp) throws SQLException {
      create(resp);//保存数据
  }

  public LineInfoData queryLineInfo() throws SQLException {
    return dao.queryForAll().get(0);
  }
}

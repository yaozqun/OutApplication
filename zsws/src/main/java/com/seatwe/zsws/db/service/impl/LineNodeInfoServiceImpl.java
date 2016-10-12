package com.seatwe.zsws.db.service.impl;

import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.ILineNodeInfoService;

import java.sql.SQLException;
import java.util.List;

public class LineNodeInfoServiceImpl extends BaseDbServiceImpl<ArriveNodeReqBean>
        implements ILineNodeInfoService {
    public LineNodeInfoServiceImpl(Class<ArriveNodeReqBean> classEntity) {
        super(classEntity);
    }

    public LineNodeInfoServiceImpl(Class<ArriveNodeReqBean> classEntity,
                                   BaseDbTransactionImpl baseDbTransactionImpl) {
        super(classEntity, baseDbTransactionImpl);
    }

    @Override
    public void saveLineNodeInfo(List<ArriveNodeReqBean> resp) throws SQLException {
        create(resp);// 保存数据
    }

    public List<ArriveNodeReqBean> queryLineNodeInfo() throws SQLException {
        return dao.queryForAll();
    }
}

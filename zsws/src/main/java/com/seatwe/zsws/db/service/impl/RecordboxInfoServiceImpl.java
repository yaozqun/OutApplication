package com.seatwe.zsws.db.service.impl;

import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.IRecordboxInfoService;

import java.sql.SQLException;
import java.util.List;


public class RecordboxInfoServiceImpl extends BaseDbServiceImpl<RecordboxInfoData>
        implements IRecordboxInfoService {
    public RecordboxInfoServiceImpl(Class<RecordboxInfoData> classEntity) {
        super(classEntity);
    }

    public RecordboxInfoServiceImpl(Class<RecordboxInfoData> classEntity,
                                    BaseDbTransactionImpl baseDbTransactionImpl) {
        super(classEntity, baseDbTransactionImpl);
    }

    @Override
    public void saveRecordboxInfo(List<RecordboxInfoData> resp) throws SQLException {

    }
}

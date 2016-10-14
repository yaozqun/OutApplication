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
    public void saveRecordBoxInfo(List<RecordboxInfoData> resp) throws SQLException {

    }

    /**
     * 根据任务ID和网点Id查询钞箱操作记录
     *
     * @param taskId
     * @param netId
     * @return
     */
    public List<RecordboxInfoData> queryRecordBoxByTaskIdAndNetId(int taskId, int netId) throws SQLException {
        return dao.getQueryBuilder().where().eq("task_id", taskId).and().eq("net_id", netId).query();
    }

    /**
     * 根据箱袋类型查询钞箱记录
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param taskId
     * @param netId
     * @param type
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryRecordBoxByType(int taskId, int netId, int type) throws SQLException {
        return dao.getQueryBuilder().where().eq("task_id", taskId).and().eq("net_id", netId).and().eq("type", type).query();
    }

}

package com.seatwe.zsws.db.service.impl;

import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.db.BaseDbServiceImpl;
import com.seatwe.zsws.db.BaseDbTransactionImpl;
import com.seatwe.zsws.db.service.IRecordboxInfoService;

import java.sql.SQLException;
import java.util.ArrayList;
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
     * @param netId
     * @return
     */
    public List<RecordboxInfoData> queryRecordBoxByNetId(int netId) throws SQLException {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("transfer_net", netId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据箱袋类型查询钞箱记录
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param netId
     * @param cashbox_type
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryRecordBoxByNetIdAndType(int netId, String cashbox_type) {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("transfer_net", netId).and().eq("cashbox_type", cashbox_type).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据箱袋类型和本地查询已扫描的钞箱记录
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param netId
     * @param cashbox_type
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryByNetIdAndTypeAndLocalStatus(int netId, String cashbox_type, int localStatus) {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("transfer_net", netId).and().eq("cashbox_type", cashbox_type).and().eq("local_status", localStatus).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据箱袋类型和本地状态查询钞箱记录
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param cashbox_type
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryByCashboxTypeAndLocalStatus(String cashbox_type, int localStatus) {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("cashbox_type", cashbox_type).and().eq("local_status", localStatus).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据箱袋类型钞箱记录
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param cashbox_type
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryByCashboxByType(String cashbox_type) {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("cashbox_type", cashbox_type).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 0: 送出
     * 1：收取
     * 2：中调
     *
     * @param localStatus
     * @return
     * @throws SQLException
     */
    public List<RecordboxInfoData> queryByCashboxByNetIdAndLocalStatus(int netId, String localStatus) {
        List<RecordboxInfoData> list = new ArrayList<>();
        try {
            list = dao.getQueryBuilder().where().eq("transfer_net", netId).and().eq("local_status", localStatus).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

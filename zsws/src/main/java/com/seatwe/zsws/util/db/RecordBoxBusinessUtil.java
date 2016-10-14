package com.seatwe.zsws.util.db;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.db.service.impl.CashboxInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.RecordboxInfoServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class RecordBoxBusinessUtil {
    private static RecordBoxBusinessUtil instance;
    private static RecordboxInfoServiceImpl recordboxInfoService;

    public static synchronized RecordBoxBusinessUtil getInstance() {
        if (instance == null) {
            instance = new RecordBoxBusinessUtil();
            recordboxInfoService = new RecordboxInfoServiceImpl(RecordboxInfoData.class);
        }
        return instance;
    }

    /**
     * 保存钞箱操作记录信息
     *
     * @param data
     */
    public void createOrUpdate(RecordboxInfoData data) {
        try {
            recordboxInfoService.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据网点ID和任务ID查询钞箱操作记录信息
     */
    public List<RecordboxInfoData> queryRecordBoxByTaskIdAndNetId(int taskId, int netId) {
        try {
            return recordboxInfoService.queryRecordBoxByTaskIdAndNetId(taskId, netId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据箱袋类型查询钞箱记录信息
     */
    public List<RecordboxInfoData> queryRecordBoxByType(int taskId, int netId, int type) {
        try {
            return recordboxInfoService.queryRecordBoxByType(taskId, netId, type);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除所有钞箱记录信息
     */
    public void clearrecordBoxInfo() {
        try {
            recordboxInfoService.delete(recordboxInfoService.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

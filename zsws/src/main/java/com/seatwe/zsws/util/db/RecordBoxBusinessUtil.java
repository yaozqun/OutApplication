package com.seatwe.zsws.util.db;

import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.db.service.impl.RecordboxInfoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
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
     * 查询所有钞箱操作记录信息
     */
    public List<RecordboxInfoData> queryAllRecordbox() {
        try {
            return recordboxInfoService.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 根据网点ID和任务ID查询钞箱操作记录信息
     */
    public List<RecordboxInfoData> queryRecordBoxByNetId(int netId) {
        try {
            return recordboxInfoService.queryRecordBoxByNetId(netId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据箱袋类型查询钞箱记录信息
     */
    public List<RecordboxInfoData> queryRecordBoxByNetIdAndType(int netId, String type) {
        return recordboxInfoService.queryRecordBoxByNetIdAndType(netId, type);
    }

    /**
     * 删除所有钞箱记录信息
     */
    public void clearrecordBoxInfo() {
        try {
            recordboxInfoService.delete(queryAllRecordbox());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package com.seatwe.zsws.util.db;

import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.db.service.impl.TaskInfoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class TaskInfoBusinessUtil {
    private static TaskInfoBusinessUtil instance;
    private static TaskInfoServiceImpl taskInfoService;

    public static synchronized TaskInfoBusinessUtil getInstance() {
        if (instance == null) {
            instance = new TaskInfoBusinessUtil();
            taskInfoService = new TaskInfoServiceImpl(TaskInfoData.class);
        }
        return instance;
    }


    /**
     * 保存任务信息
     *
     * @param data
     */
    public void saveTaskInfoData(List<TaskInfoData> data) {
        try {
            taskInfoService.saveLineInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存或修改任务信息
     *
     * @param data
     */
    public void createOrUpdate(TaskInfoData data) {
        try {
            taskInfoService.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询任务信息
     */
    public List<TaskInfoData> queryAllTaskInfo() {
        try {
            return taskInfoService.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 查询任务信息
     */
    public List<TaskInfoData> queryTaskInfoByLocalStatus(int localStatus) {
        return taskInfoService.queryTaskInfoByLocalStatsu(localStatus);
    }

    /**
     * 根据网点查询送出钞箱数量
     */
    public int queryTaskInfoByNetId(String netId) {
        int sameNetCount = 0;
        try {
            List<TaskInfoData> list = taskInfoService.queryTaskInfoByNetId(netId);
            if (list != null && list.size() > 0) {

                //查询送出钞箱数量
                for (TaskInfoData data : list) {
                    if (data.getCashbox_num() != null && !"".equals(data.getCashbox_num())) {
                        sameNetCount++;
                    }
                }

                //每个网点需要送出的钞箱数量保存在书库,方便之后调用
                for (TaskInfoData data : list) {
                    data.setCashbox_num_count(sameNetCount);
                    taskInfoService.createOrUpdate(data);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sameNetCount;
    }

    /**
     * 删除任务信息
     */
    public void clearTaskInfo() {
        try {
            taskInfoService.delete(queryAllTaskInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

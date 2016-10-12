package com.seatwe.zsws.util;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.db.service.impl.CashboxInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.LineInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.LineNodeInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.NetInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.TaskInfoServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class BusinessUtil {
    private static BusinessUtil instance;
    private static LineInfoServiceImpl lineInfoService;
    private static TaskInfoServiceImpl taskInfoService;
    private static NetInfoServiceImpl netInfoService;
    private static LineNodeInfoServiceImpl lineNodeInfoService;
    private static CashboxInfoServiceImpl cashboxInfoService;

    public static synchronized BusinessUtil getInstance() {
        if (instance == null) {
            instance = new BusinessUtil();
            lineInfoService = new LineInfoServiceImpl(LineInfoData.class);
            taskInfoService = new TaskInfoServiceImpl(TaskInfoData.class);
            netInfoService = new NetInfoServiceImpl(NetInfoData.class);
            lineNodeInfoService = new LineNodeInfoServiceImpl(ArriveNodeReqBean.class);
            cashboxInfoService = new CashboxInfoServiceImpl(CashBoxData.class);
        }
        return instance;
    }

    /**
     * 保存线路信息
     *
     * @param data
     */
    public void saveLineInfoData(LineInfoData data) {
        try {
            lineInfoService.saveLineInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询线路信息
     */
    public LineInfoData queryLineInfo() {
        try {
            return lineInfoService.queryLineInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除线路信息
     */
    public void clearLineInfo() {
        try {
            lineInfoService.delete(queryLineInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     * 查询任务信息
     */
    public List<TaskInfoData> queryTaskInfo() {
        try {
            return taskInfoService.queryTaskInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除任务信息
     */
    public void clearTaskInfo() {
        try {
            taskInfoService.delete(queryTaskInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存钞箱信息
     *
     * @param data
     */
    public void saveCashboxInfoData(List<CashBoxData> data) {
        try {
            cashboxInfoService.saveCashboxInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询钞箱信息
     */
    public List<CashBoxData> queryCashboxInfo() {
        try {
            return cashboxInfoService.queryCashboxInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除任务信息
     */
    public void clearCashboxInfo() {
        try {
            cashboxInfoService.delete(queryCashboxInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存网点信息
     *
     * @param data
     */
    public void saveNetInfoData(List<NetInfoData> data) {
        try {
            netInfoService.saveNetInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询网点信息
     */
    public List<NetInfoData> queryNetInfo() {
        try {
            return netInfoService.queryNetInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 删除网点信息
     */
    public void clearNetInfo() {
        try {
            netInfoService.delete(queryNetInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据网点ID查询网点信息
     *
     * @param id
     */
    public NetInfoData queryNetInfoById(int id) {
        try {
            return netInfoService.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存线路节点信息
     *
     * @param data
     */
    public void saveLineNodeInfoData(List<ArriveNodeReqBean> data) {
        try {
            lineNodeInfoService.saveLineNodeInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询线路节点信息
     */
    public List<ArriveNodeReqBean> queryLineNodeInfo() {
        try {
            return lineNodeInfoService.queryLineNodeInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除线路节点信息
     */
    public void clearLineNodeInfo() {
        try {
            lineNodeInfoService.delete(queryLineNodeInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

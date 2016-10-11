package com.seatwe.zsws.util;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.db.service.impl.CashboxInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.LineInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.NetInfoServiceImpl;
import com.seatwe.zsws.db.service.impl.TaskInfoServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class BusinessUtil {
    //private static LineInfoServiceImpl lineInfoServiceImpl = new LineInfoServiceImpl(LineInfoData.class);

    /**
     * 保存线路信息
     * @param data
     */
    public static void saveLineInfoData(LineInfoData data){
        LineInfoServiceImpl infoServiceImpl = new LineInfoServiceImpl(LineInfoData.class);
        try {
            infoServiceImpl.saveLineInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询线路信息
     */
    public static LineInfoData queryLineInfo(){
        LineInfoServiceImpl infoServiceImpl = new LineInfoServiceImpl(LineInfoData.class);
        try {
            return infoServiceImpl.queryLineInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存任务信息
     * @param data
     */
    public static void saveTaskInfoData(List<TaskInfoData> data){
        TaskInfoServiceImpl infoServiceImpl = new TaskInfoServiceImpl(TaskInfoData.class);
        try {
            infoServiceImpl.saveLineInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询任务信息
     */
    public static List<TaskInfoData> queryTaskInfo(){
        TaskInfoServiceImpl infoServiceImpl = new TaskInfoServiceImpl(TaskInfoData.class);
        try {
            return infoServiceImpl.queryTaskInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存钞箱信息
     * @param data
     */
    public static void saveCashboxInfoData(List<CashBoxData> data){
        CashboxInfoServiceImpl infoServiceImpl = new CashboxInfoServiceImpl(CashBoxData.class);
        try {
            infoServiceImpl.saveCashboxInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询钞箱信息
     */
    public static List<CashBoxData> queryCashboxInfo(){
        CashboxInfoServiceImpl infoServiceImpl = new CashboxInfoServiceImpl(CashBoxData.class);
        try {
            return infoServiceImpl.queryCashboxInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存网点信息
     * @param data
     */
    public static void saveNetInfoData(List<NetInfoData> data){
        NetInfoServiceImpl infoServiceImpl = new NetInfoServiceImpl(NetInfoData.class);
        try {
            infoServiceImpl.saveNetInfo(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询网点信息
     */
    public static List<NetInfoData> queryNetInfo(){
        NetInfoServiceImpl infoServiceImpl = new NetInfoServiceImpl(NetInfoData.class);
        try {
            return infoServiceImpl.queryNetInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

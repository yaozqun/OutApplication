package com.seatwe.zsws.util.db;

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
public class NetInfoBusinessUtil {
    private static NetInfoBusinessUtil instance;
    private static NetInfoServiceImpl netInfoService;

    public static synchronized NetInfoBusinessUtil getInstance() {
        if (instance == null) {
            instance = new NetInfoBusinessUtil();
            netInfoService = new NetInfoServiceImpl(NetInfoData.class);
        }
        return instance;
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

}

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
public class LineInfoBusinessUtil {
    private static LineInfoBusinessUtil instance;
    private static LineInfoServiceImpl lineInfoService;

    public static synchronized LineInfoBusinessUtil getInstance() {
        if (instance == null) {
            instance = new LineInfoBusinessUtil();
            lineInfoService = new LineInfoServiceImpl(LineInfoData.class);
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

}

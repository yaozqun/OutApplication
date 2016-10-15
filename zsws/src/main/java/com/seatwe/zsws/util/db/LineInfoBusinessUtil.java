package com.seatwe.zsws.util.db;

import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.db.service.impl.LineInfoServiceImpl;

import java.sql.SQLException;

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
    public LineInfoData queryAllLineInfo() {
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
            lineInfoService.delete(queryAllLineInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

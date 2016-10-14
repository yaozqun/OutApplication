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
public class LineNodeBusinessUtil {
    private static LineNodeBusinessUtil instance;
    private static LineNodeInfoServiceImpl lineNodeInfoService;

    public static synchronized LineNodeBusinessUtil getInstance() {
        if (instance == null) {
            instance = new LineNodeBusinessUtil();
            lineNodeInfoService = new LineNodeInfoServiceImpl(ArriveNodeReqBean.class);
        }
        return instance;
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

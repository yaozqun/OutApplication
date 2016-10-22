package com.seatwe.zsws.util.db;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.db.service.impl.NetInfoServiceImpl;

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
    public List<NetInfoData> queryAllNetInfo() {
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
            netInfoService.delete(queryAllNetInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据网点ID查询网点信息
     *
     * @param id
     */
    public NetInfoData queryNetInfoById(String id) {
        try {
            return netInfoService.queryForEq("id", id) == null ? null : netInfoService.queryForEq("id", id).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询钞箱版本号
     *
     * @return
     */
    public String queryCurrentVersion() {
        try {
            List<NetInfoData> list = netInfoService.queryForAll();
            if (list != null && list.size() > 0) {
                int version = Integer.parseInt(list.get(0).getVersion());
                for (NetInfoData netInfoData : list) {
                    int nextVersion = Integer.parseInt(netInfoData.getVersion());
                    if (nextVersion > version) {
                        version = nextVersion;
                    }
                }
                return version+"";
            } else {
                return "0";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
        }
    }
}

package com.grgbanking.baselib.web.bean.resp;


import com.grgbanking.baselib.web.bean.NetInfoData;

import java.util.List;

/**
 * Created by charry on 2016/10/8.
 */
public class NetInfoRespBean {
    private List<NetInfoData> data;

    public List<NetInfoData> getData() {
        return this.data;
    }

    public void setData(List<NetInfoData> data) {
        this.data = data;
    }
}

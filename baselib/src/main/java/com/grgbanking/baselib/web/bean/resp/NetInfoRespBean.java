package com.grgbanking.baselib.web.bean.resp;


import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.bean.ResponseBean;

/**
 * Created by charry on 2016/10/8.
 */
public class NetInfoRespBean extends ResponseBean {
    private NetInfoData data;

    public NetInfoData getData() {
        return data;
    }

    public void setData(NetInfoData data) {
        this.data = data;
    }
}

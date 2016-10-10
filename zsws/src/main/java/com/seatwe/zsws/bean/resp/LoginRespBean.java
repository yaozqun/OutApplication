package com.seatwe.zsws.bean.resp;

import com.seatwe.baselib.network.been.ResponseBean;
import com.seatwe.zsws.bean.LineInfoData;

/**
 * Created by charry on 2016/10/8.
 */
public class LoginRespBean extends ResponseBean{
    private LineInfoData data;

    public LineInfoData getData() {
        return data;
    }

    public void setData(LineInfoData data) {
        this.data = data;
    }
}

package com.grgbanking.baselib.web.bean.resp;


import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.ResponseBean;

/**
 * Created by charry on 2016/10/8.
 */
public class CashBoxRespBean extends ResponseBean {
    private CashBoxData data;

    public CashBoxData getData() {
        return data;
    }

    public void setData(CashBoxData data) {
        this.data = data;
    }
}

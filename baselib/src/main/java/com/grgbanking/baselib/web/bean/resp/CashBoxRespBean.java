package com.grgbanking.baselib.web.bean.resp;


import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.ResponseBean;

import java.util.List;

/**
 * Created by charry on 2016/10/8.
 */
public class CashBoxRespBean extends ResponseBean {
    private List<CashBoxData> data;

    public List<CashBoxData> getData() {
        return this.data;
    }

    public void setData(List<CashBoxData> data) {
        this.data = data;
    }
}

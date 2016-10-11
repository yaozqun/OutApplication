package com.grgbanking.baselib.web.bean.resp;

import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.bean.UpgradeData;

/**
 * Created by charry on 2016/10/8.
 */
public class UpgradeRespBean extends ResponseBean {
    private UpgradeData data;

    public UpgradeData getData() {
        return data;
    }

    public void setData(UpgradeData data) {
        this.data = data;
    }
}

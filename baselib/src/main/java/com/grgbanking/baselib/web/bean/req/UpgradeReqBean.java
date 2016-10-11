package com.grgbanking.baselib.web.bean.req;

/**
 * Created by charry on 2016/10/8.
 */
public class UpgradeReqBean {
    private String version;
    private String app_id;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}

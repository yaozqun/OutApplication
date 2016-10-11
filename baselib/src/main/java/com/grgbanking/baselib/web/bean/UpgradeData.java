package com.grgbanking.baselib.web.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/8.
 */
@DatabaseTable(tableName = "T_SYS_UpgradeData")
public class UpgradeData {
    @DatabaseField(columnName = "app_id")
    private int app_id;
    @DatabaseField(columnName = "version")
    private String version;
    @DatabaseField(columnName = "content")
    private String content;
    @DatabaseField(columnName = "forced_update")
    private String forced_update;
    @DatabaseField(columnName = "url")
    private String url;

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getForced_update() {
        return forced_update;
    }

    public void setForced_update(String forced_update) {
        this.forced_update = forced_update;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

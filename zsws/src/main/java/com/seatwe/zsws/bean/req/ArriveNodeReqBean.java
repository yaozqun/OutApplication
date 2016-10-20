package com.seatwe.zsws.bean.req;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/9.
 */
@DatabaseTable(tableName = "T_SYS_LineNodeInfo")
public class ArriveNodeReqBean {
    @DatabaseField(columnName = "id", id = true, useGetSet = true)
    private String line_id;

    /**
     * 0：开始
     * 1：结束
     */
    @DatabaseField(columnName = "node_type")
    private String node_type;

    @DatabaseField(columnName = "arrive_time")
    private String arrive_time;

    @DatabaseField(columnName = "node_name")
    private String node_name;

    @DatabaseField(columnName = "local_status")
    private int localStatus;

    @DatabaseField(columnName = "code")
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(int localStatus) {
        this.localStatus = localStatus;
    }

    public String getNode_name() {
        return this.node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }
}

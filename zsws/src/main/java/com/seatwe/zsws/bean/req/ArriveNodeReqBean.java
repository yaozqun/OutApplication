package com.seatwe.zsws.bean.req;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/9.
 */
@DatabaseTable(tableName = "T_SYS_LineNodeInfo")
public class ArriveNodeReqBean {
    @DatabaseField(generatedId = true, columnName = "ID", canBeNull = false, unique = true)
    private int line_id;

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

    @DatabaseField(columnName = "status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNode_name() {
        return this.node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
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

package com.seatwe.zsws.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by charry on 2016/10/8.
 */

@DatabaseTable(tableName = "T_SYS_TaskInfo")
public class TaskInfoData implements Serializable{
    @DatabaseField(columnName = "id")
    private int id;
    @DatabaseField(columnName = "line_id")
    private int line_id;
    @DatabaseField(columnName = "task_type")
    private String task_type;
    @DatabaseField(columnName = "task_status")
    private String task_status;
    @DatabaseField(columnName = "net_id")
    private int net_id;
    @DatabaseField(columnName = "orgid")
    private String orgid;
    @DatabaseField(columnName = "cashbox_num")
    private String cashbox_num;
    @DatabaseField(columnName = "arriveTime")
    private String arriveTime;

    public String getArriveTime() {
        return this.arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public int getNet_id() {
        return net_id;
    }

    public void setNet_id(int net_id) {
        this.net_id = net_id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getCashbox_num() {
        return cashbox_num;
    }

    public void setCashbox_num(String cashbox_num) {
        this.cashbox_num = cashbox_num;
    }
}

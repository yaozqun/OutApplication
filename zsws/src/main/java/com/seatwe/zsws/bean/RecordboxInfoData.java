package com.seatwe.zsws.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/10/13.
 */
@DatabaseTable(tableName = "T_SYS_RecordBoxInfo")
public class RecordboxInfoData {

    @DatabaseField(generatedId = true, columnName = "ID", canBeNull = false, unique = true)
    private int id;
    @DatabaseField(columnName = "transfer_type")
    private String transfer_type;
    @DatabaseField(columnName = "transfer_time")
    private String transfer_time;
    @DatabaseField(columnName = "transfer_net")
    private String transfer_net;
    @DatabaseField(columnName = "box_code")
    private String box_code;
    @DatabaseField(columnName = "transfer_status")
    private String transfer_status;
    @DatabaseField(columnName = "task_id")
    private int task_id;
    /**
     * 0: 送出
     * 1：收取
     * 2：中调
     */
    @DatabaseField(columnName = "type")
    private String type;

    public int getTask_id() {
        return this.task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public String getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(String transfer_time) {
        this.transfer_time = transfer_time;
    }

    public String getTransfer_net() {
        return transfer_net;
    }

    public void setTransfer_net(String transfer_net) {
        this.transfer_net = transfer_net;
    }

    public String getBox_code() {
        return box_code;
    }

    public void setBox_code(String box_code) {
        this.box_code = box_code;
    }

    public String getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(String transfer_status) {
        this.transfer_status = transfer_status;
    }
}

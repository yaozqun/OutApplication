package com.seatwe.zsws.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.seatwe.zsws.constant.LocalStatusConstant;

/**
 * Created by Administrator on 2016/10/13.
 */
@DatabaseTable(tableName = "T_SYS_RecordBoxInfo")
public class RecordboxInfoData {

    @DatabaseField(columnName = "id", id = true, useGetSet = true)
    private String id;
    @DatabaseField(columnName = "transfer_type")
    private String transfer_type;
    @DatabaseField(columnName = "transfer_time")
    private String transfer_time;
    @DatabaseField(columnName = "transfer_net")
    /**
     * 网店ID
     */
    private String transfer_net;
    @DatabaseField(columnName = "box_code")
    private String box_code;
    @DatabaseField(columnName = "transfer_status")
    private String transfer_status;
    /**
     * 0: 送出
     * 1：收取
     * 2：中调
     */
    @DatabaseField(columnName = "cashbox_type")
    private String cashbox_type;

    @DatabaseField(columnName = "local_status")
    private int localStatus = LocalStatusConstant.UN_DONE;

    @DatabaseField(columnName = "is_middle")
    private String isMiddle;

    public String getIsMiddle() {
        return isMiddle;
    }

    public void setIsMiddle(String isMiddle) {
        this.isMiddle = isMiddle;
    }

    public int getLocalStatus() {
        return localStatus;
    }

    public void setLocalStatus(int localStatus) {
        this.localStatus = localStatus;
    }

    public String getCashbox_type() {
        return cashbox_type;
    }

    public void setCashbox_type(String cashbox_type) {
        this.cashbox_type = cashbox_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

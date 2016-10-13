package com.seatwe.zsws.bean.req;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.seatwe.zsws.bean.RecordboxInfoData;

import java.util.List;

/**
 * Created by charry on 2016/10/8.
 */
@DatabaseTable(tableName = "T_SYS_RecordInfo")
public class RecordReqBean {
    @DatabaseField(generatedId = true, columnName = "ID", canBeNull = false, unique = true)
    private String id;
    @DatabaseField(columnName = "line_id")
    private String line_id;
    @DatabaseField(columnName = "transfer_type")
    private String transfer_type;
    @DatabaseField(columnName = "transfer_time")
    private String transfer_time;
    @DatabaseField(columnName = "transfer_net")
    private String transfer_net;
    @DatabaseField(columnName = "counterman1")
    private String counterman1;
    @DatabaseField(columnName = "counterman2")
    private String counterman2;
    @DatabaseField(columnName = "bankman_name1")
    private String bankman_name1;
    @DatabaseField(columnName = "bankman_name2")
    private String bankman_name2;
    @DatabaseField(columnName = "car_number")
    private String car_number;
    @DatabaseField(columnName = "box_number")
    private String box_number;
    @DatabaseField(columnName = "note")
    private String note;
    private List<RecordboxInfoData> boxes;
    @DatabaseField(columnName = "boxsId")
    private String boxsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
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

    public String getCounterman1() {
        return counterman1;
    }

    public void setCounterman1(String counterman1) {
        this.counterman1 = counterman1;
    }

    public String getCounterman2() {
        return counterman2;
    }

    public void setCounterman2(String counterman2) {
        this.counterman2 = counterman2;
    }

    public String getBankman_name1() {
        return bankman_name1;
    }

    public void setBankman_name1(String bankman_name1) {
        this.bankman_name1 = bankman_name1;
    }

    public String getBankman_name2() {
        return bankman_name2;
    }

    public void setBankman_name2(String bankman_name2) {
        this.bankman_name2 = bankman_name2;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getBox_number() {
        return box_number;
    }

    public void setBox_number(String box_number) {
        this.box_number = box_number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RecordboxInfoData> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<RecordboxInfoData> boxes) {
        this.boxes = boxes;
    }

    public String getBoxsId() {
        return boxsId;
    }

    public void setBoxsId(String boxsId) {
        this.boxsId = boxsId;
    }

}

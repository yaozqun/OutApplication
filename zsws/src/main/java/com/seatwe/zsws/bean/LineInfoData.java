package com.seatwe.zsws.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/9.
 */
@DatabaseTable(tableName = "T_SYS_LineInfo")
public class LineInfoData {
    @DatabaseField(columnName = "id", id = true, useGetSet = true)
    private String id;
    @DatabaseField(columnName = "line_number")
    private String line_number;
    @DatabaseField(columnName = "line_notes")
    private String line_notes;
    @DatabaseField(columnName = "line_status")
    private String line_status;
    @DatabaseField(columnName = "task_number")
    private String task_number;
    @DatabaseField(columnName = "counterman1")
    private String counterman1;
    @DatabaseField(columnName = "counterman2")
    private String counterman2;
    @DatabaseField(columnName = "escort_name1")
    private String escort_name1;
    @DatabaseField(columnName = "escort_name2")
    private String escort_name2;
    @DatabaseField(columnName = "orgid")
    private String orgid;


    @DatabaseField(columnName = "line_type")
    private String line_type;
    @DatabaseField(columnName = "car_number")
    private String car_number;
    @DatabaseField(columnName = "drive")
    private String drive;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getLine_notes() {
        return line_notes;
    }

    public void setLine_notes(String line_notes) {
        this.line_notes = line_notes;
    }

    public String getTask_number() {
        return task_number;
    }

    public void setTask_number(String task_number) {
        this.task_number = task_number;
    }

    public String getLine_type() {
        return line_type;
    }

    public void setLine_type(String line_type) {
        this.line_type = line_type;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
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

    public String getEscort_name1() {
        return escort_name1;
    }

    public void setEscort_name1(String escort_name1) {
        this.escort_name1 = escort_name1;
    }

    public String getEscort_name2() {
        return escort_name2;
    }

    public void setEscort_name2(String escort_name2) {
        this.escort_name2 = escort_name2;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getLine_status() {
        return line_status;
    }

    public void setLine_status(String line_status) {
        this.line_status = line_status;
    }
}

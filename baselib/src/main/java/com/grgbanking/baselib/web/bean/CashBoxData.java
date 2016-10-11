package com.grgbanking.baselib.web.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/6.
 */
@DatabaseTable(tableName = "T_SYS_CashBox")
public class CashBoxData extends ResponseBean {
    @DatabaseField(generatedId = true, columnName = "ID", canBeNull = false, unique = true)
    private int id;
    @DatabaseField(columnName = "cashbox_num")
    private String cashbox_num;
    @DatabaseField(columnName = "status")
    private String status;
    @DatabaseField(columnName = "brands")
    private String brands;
    @DatabaseField(columnName = "net_id")
    private String net_id;
    @DatabaseField(columnName = "clearing_space")
    private String clearing_space;
    @DatabaseField(columnName = "box_type_id")
    private String box_type_id;
    @DatabaseField(columnName = "box_type_name")
    private String box_type_name;
    @DatabaseField(columnName = "orgid")
    private String orgid;
    @DatabaseField(columnName = "deleted")
    private String deleted;
    @DatabaseField(columnName = "version")
    private String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCashbox_num() {
        return cashbox_num;
    }

    public void setCashbox_num(String cashbox_num) {
        this.cashbox_num = cashbox_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getNet_id() {
        return net_id;
    }

    public void setNet_id(String net_id) {
        this.net_id = net_id;
    }

    public String getClearing_space() {
        return clearing_space;
    }

    public void setClearing_space(String clearing_space) {
        this.clearing_space = clearing_space;
    }

    public String getBox_type_id() {
        return box_type_id;
    }

    public void setBox_type_id(String box_type_id) {
        this.box_type_id = box_type_id;
    }

    public String getBox_type_name() {
        return box_type_name;
    }

    public void setBox_type_name(String box_type_name) {
        this.box_type_name = box_type_name;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

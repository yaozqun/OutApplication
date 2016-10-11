package com.grgbanking.baselib.web.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by charry on 2016/10/7.
 */
@DatabaseTable(tableName = "T_SYS_NetInfo")
public class NetInfoData {
    @DatabaseField(generatedId = true, columnName = "ID", canBeNull = false, unique = true)
    private int id;
    @DatabaseField(columnName = "net_code")
    private String net_code;
    @DatabaseField(columnName = "net_name")
    private String net_name;
    @DatabaseField(columnName = "net_address")
    private String net_address;
    @DatabaseField(columnName = "contacts_name")
    private String contacts_name;
    @DatabaseField(columnName = "tel_number")
    private String tel_number;
    @DatabaseField(columnName = "net_type")
    private String net_type;
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

    public String getNet_code() {
        return net_code;
    }

    public void setNet_code(String net_code) {
        this.net_code = net_code;
    }

    public String getNet_name() {
        return net_name;
    }

    public void setNet_name(String net_name) {
        this.net_name = net_name;
    }

    public String getNet_address() {
        return net_address;
    }

    public void setNet_address(String net_address) {
        this.net_address = net_address;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getNet_type() {
        return net_type;
    }

    public void setNet_type(String net_type) {
        this.net_type = net_type;
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

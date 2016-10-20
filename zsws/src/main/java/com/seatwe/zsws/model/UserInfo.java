package com.seatwe.zsws.model;

/**
 * Created by Administrator on 2016/10/20.
 */
public class UserInfo {
    private String login_name;

    private String line_id;

    private String login_password ;

    public String getLogin_name() {
        return this.login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLine_id() {
        return this.line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getLogin_password() {
        return this.login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}

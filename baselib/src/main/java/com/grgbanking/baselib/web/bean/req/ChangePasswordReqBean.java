package com.grgbanking.baselib.web.bean.req;

/**
 * Created by charry on 2016/10/8.
 */
public class ChangePasswordReqBean {
    private String login_name;
    private String oldPw;
    private String newPw;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
}

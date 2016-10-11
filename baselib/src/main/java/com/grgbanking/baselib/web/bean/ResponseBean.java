package com.grgbanking.baselib.web.bean;

/**
 * Created by charry on 2016/10/7.
 */
public class ResponseBean {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        if ("00".equals(code)) {
            return true;
        } else {
            return false;
        }
    }
}

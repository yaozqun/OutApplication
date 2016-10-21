package com.grgbanking.baselib.web.response;

public class ResponseRoot<T> {
    /**
     * 0：成功
     * 1：不用更新
     * 2：非法程序
     */
    private String code;

    private String message;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public boolean isSuccess() {
        if ("00".equals(code)) {
            return true;
        } else {
            return false;
        }
    }
}
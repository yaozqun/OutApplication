package com.grgbanking.baselib.web.util;

import java.io.IOException;

/*import com.alibaba.fastjson.JSON;
import com.grgbanking.baselib.util.SecurityUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;*/

/**
 * Created by charry on 2016/10/6.
 */
public class NetOkHttpUtils {


   /* *//**
     * get请求
     *
     * @param url
     * @param value
     * @return
     * @throws IOException
     *//*
    public static <T> void get(String url, T value, StringCallback stringCallback) {
        OkHttpUtils
                .get()
                .url(url + "?params=" + JSON.toJSONString(value))
                .build()
                .execute(stringCallback);
    }

    *//**
     * post请求
     *
     * @param url
     * @param value
     * @return
     * @throws IOException
     *//*
    public static <T> void post(String url, T value, Callback Callback) {
        String str = JSON.toJSONString(value);
        //加密 用Web一套加密
        String requseStr = null;
        try {
            requseStr = SecurityUtils.aesEncrypt(str, SecurityUtils.MESSAGE_AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .post()
                .url(url)
                .addParams("param", requseStr)
                .build()
                .execute(Callback);
    }*/
}

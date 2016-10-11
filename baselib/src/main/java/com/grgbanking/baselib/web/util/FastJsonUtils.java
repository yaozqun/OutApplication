package com.grgbanking.baselib.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.grgbanking.baselib.util.SecurityUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by charry on 2016/10/6.
 */
public class FastJsonUtils {
    public static List<String> getStringList(String jsonData) throws Exception {
        return JSON.parseArray(jsonData, String.class);
    }

    public static <T> T getSingleBeanForRespone(String jsonData, Class<T> clazz)
            throws Exception {
        String decode = SecurityUtils.aesDecrypt(jsonData, SecurityUtils.MESSAGE_AES_KEY);
        return getSingleBean(decode, clazz);
    }

    public static <T> T getSingleBean(String jsonData, Class<T> clazz)
            throws Exception {
        return JSON.parseObject(jsonData, clazz);
    }

    public static <T> T getSingleBean(String jsonData, Class<T> clazz, String param)
            throws Exception {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String data = jsonObject.getString(param);
        return JSON.parseObject(data, clazz);
    }


    public static <T> List<T> getBeanList(String jsonData, Class<T> clazz)
            throws Exception {
        return JSON.parseArray(jsonData, clazz);
    }


    public static <T> List<T> getBeanList(String jsonData, Class<T> clazz, String param)
            throws Exception {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String data = jsonObject.getString(param);
        return JSON.parseArray(data, clazz);
    }


    public static List<Map<String, Object>> getBeanMapList(String jsonData)
            throws Exception {
        return JSON.parseObject(jsonData,
                new TypeReference<List<Map<String, Object>>>() {
                });
    }


    public static String dealResponseResult(String result) {
        result = JSONObject.toJSONString(result,
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteSlashAsSpecial,
                SerializerFeature.WriteTabAsSpecial);
        return result;
    }
}

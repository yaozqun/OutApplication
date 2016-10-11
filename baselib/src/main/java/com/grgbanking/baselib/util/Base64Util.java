package com.grgbanking.baselib.util;

import android.util.Base64;

/**
 * Created by charry on 2016/10/8.
 */
public class Base64Util {
    //如果此util不能正常编码的请参考另一种方案：http://www.cnblogs.com/anee/archive/2013/03/14/2960207.html

    /**
     * Base64编码
     *
     * @param str 要编码的字符串
     * @return 编码完成的字符串码
     */
    public static String getEncodeStr(String str) {
        byte byteArr[] = Base64.encode(str.getBytes(), Base64.DEFAULT);
        return new String(byteArr);
    }

    /**
     * @param bytes
     * @return
     */
    public static String getEncodeByte(byte[] bytes) {
        byte byteArr[] = Base64.encode(bytes, Base64.DEFAULT);
        return new String(byteArr);
    }

    /**
     * Base64解码
     *
     * @param encodeStr 被编码的字符串
     * @return 解码完成，输出原本字符串
     */
    public static String getDecodeStr(String encodeStr) {
        byte byteArr[] = Base64.decode(encodeStr, Base64.DEFAULT);
        return new String(byteArr);
    }

    public static byte[] getDecodeStrToByte(String encodeStr) {
        byte[] byteArr = Base64.decode(encodeStr, Base64.DEFAULT);
        return byteArr;
    }
}

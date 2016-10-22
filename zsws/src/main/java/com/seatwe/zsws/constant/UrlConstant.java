package com.seatwe.zsws.constant;

/**
 * Created by charry on 2016/10/6.
 */
public class UrlConstant {

    //    public static String HOST = "http://15844403vs.imwork.net:58182/ad_zsws/mobile-message-operate-zsws!";
//    public static String HOST = "http://10.2.11.50:8081/ad_qjxt/mobile-message-operate-zsws!";
    public static String HOST = "http://charry123.ticp.net/ad_qjxt/mobile-message-operate-zsws!";
    //基础接口
    public static String BASE_HOST = HOST;

    // 登录
    public static String USER_LOGIN = HOST + "userLogin.do";

    // 任务信息下载
    public static String TASK_INFO = HOST + "taskInfo.do";

    // 钞箱信息下载
    public static String GET_CASHBOX = BASE_HOST + "getCashBox.do";

    // 网点信息下载
    public static String GET_NET_INFO = HOST + "getNetInfo.do";

    // 到达节点
    public static String ARRIVE_NODE = HOST + "arriveNode.do";

    // 任务记录上传
    public static String UPLOAD_RECORD = HOST + "uploadRecord.do";

    // 退出
    public static String USER_LOGOUT = HOST + "userLogout.do";

    //修改密码
    public static String CHANGE_PASSWORD = BASE_HOST + "changePassword.do";

    //获取apk升级信息
    public static String GET_APK_UPDATE = BASE_HOST + "getApkUpdate.do";

    public static String APP_ID = "zsws";

}

package com.seatwe.zsws;

import com.seatwe.zsws.constant.UrlConstant;

/**
 * Created by charry on 2016/10/11.
 */
public class TestDatas {

    public static String testSer(String url) {
        // 登录
        if (url.equals(UrlConstant.USER_LOGIN)) {
            return "{\"code\":\"00\",\"data\":{\"id\":\"1\",\"line_number\":\"T-20161010-01\",\"line_notes\":\"写死的测试信息\",\"task_number\":\"2\",\"line_type\":\"早送\",\"car_number\":\"粤H03243\",\"drive\":\"老司\",\"counterman1\":\"业1\",\"counterman2\":\"业2\",\"escort_name1\":\"押1\",\"escort_name2\":\"押2\",\"orgid\":\"001\"}}";
        }
        // 到达节点
        if (url.equals(UrlConstant.ARRIVE_NODE)) {
            return "{\"code\": \"00\",\"message\": \"到达节点记录上传成功\"}";
        }
        //  // 任务信息下载
        if (url.equals(UrlConstant.TASK_INFO)) {
            return "{\"code\":\"00\",\"data\":[{\"id\":\"12\",\"line_id\":\"1\",\"task_type\":\"类型2\",\"task_status\":\"01\",\"net_id\":\"002\",\"orgid\":\"1\",\"cashbox_num\":\"C0000002\"},{\"id\":\"13\",\"line_id\":\"1\",\"task_type\":\"类型1\",\"task_status\":\"01\",\"net_id\":\"001\",\"orgid\":\"2\",\"cashbox_num\":\"C0000001\"},{\"id\":\"14\",\"line_id\":\"1\",\"task_type\":\"类型1\",\"task_status\":\"01\",\"net_id\":\"001\",\"orgid\":\"2\",\"cashbox_num\":\"C0000003\"}]}";
        }

        // 任务记录上传
        if (url.equals(UrlConstant.UPLOAD_RECORD)) {
            return "{\"code\": \"00\",\"message\": \"任务记录上传成功\"}";
        }

        // 退出
        if (url.equals(UrlConstant.USER_LOGOUT)) {
            return "{\"code\": \"00\",\"message\": \"退出成功\"}";
        }

        //修改密码
        if (url.equals(UrlConstant.CHANGE_PASSWORD)) {
            return "{\"code\": \"00\",\"message\": \"修改密码成功\"}";
        }

        //获取钞箱信息
        if (url.equals(UrlConstant.GET_CASHBOX)) {
            //最大版本14
            return "{\"code\":\"00\",\"data\":[{\"id\":\"12\",\"cashbox_num\":\"C0000002\",\"status\":\"0\",\"brands\":\"brands2\",\"net_id\":\"002\",\"clearing_space\":\"ewqewq\",\"box_type_id\":\"234\",\"box_type_name\":\"款箱\",\"orgid\":\"2342\",\"deleted\":\"0\",\"version\":\"13\"},{\"id\":\"13\",\"cashbox_num\":\"C0000001\",\"status\":\"0\",\"brands\":\"brands1\",\"net_id\":\"001\",\"clearing_space\":\"ewqewq\",\"box_type_id\":\"234\",\"box_type_name\":\"款箱\",\"orgid\":\"2342\",\"deleted\":\"0\",\"version\":\"14\"},{\"id\":\"13\",\"cashbox_num\":\"C0000001\",\"status\":\"0\",\"brands\":\"brands1\",\"net_id\":\"001\",\"clearing_space\":\"ewqewq\",\"box_type_id\":\"234\",\"box_type_name\":\"下介箱\",\"orgid\":\"2342\",\"deleted\":\"0\",\"version\":\"14\"}]}";
        }
       /*网点类型：
        01：银行网点
        02：企业网点
        03：银行金库
        00：安达金库
*/
        // 获取网点信息
        if (url.equals(UrlConstant.GET_NET_INFO)) {
            return "{\"code\":\"00\",\"data\":[{\"id\":\"001\",\"net_code\":\"code1\",\"net_name\":\"农行1\",\"net_address\":\"312号\",\"contacts_name\":\"交接人1\",\"tel_number\":\"13112365478\",\"net_type\":\"01\",\"deleted\":\"0\",\"version\":\"13\"},{\"id\":\"002\",\"net_code\":\"code2\",\"net_name\":\"中行1\",\"net_address\":\"105国道\",\"contacts_name\":\"交接人2\",\"tel_number\":\"13521365498\",\"net_type\":\"02\",\"deleted\":\"0\",\"version\":\"14\"},{\"id\":\"003\",\"net_code\":\"code3\",\"net_name\":\"金库\",\"net_address\":\"11288国道\",\"contacts_name\":\"交接人3\",\"tel_number\":\"13521111111\",\"net_type\":\"00\",\"deleted\":\"0\",\"version\":\"14\"}]}";
        }

        //获取apk升级信息
        if (url.equals(UrlConstant.GET_APK_UPDATE)) {
            return "{\"code\":\"00\",\"data\":{app_id:\"zsws\",version:\"1.0.0\",content:\"强制升级\",forced_update:\"1\",url:\"http://127.0.0.1/\"}}";
        }
        //*****************************************金库接口
        // 登录
        if (url.equals(UrlConstant.USER_LOGIN)) {
            return "{\"code\":\"00\",\"data\":{\"id\":\"1\",\"line_number\":\"T-2016-10-10\",\"line_notes\":\"写死的测试信息\",\"task_number\":\"2\",\"line_type\":\"早送\",\"car_number\":\"粤H03243\",\"drive\":\"老司\",\"counterman1\":\"业1\",\"counterman2\":\"业2\",\"escort_name1\":\"押1\",\"escort_name2\":\"押2\",\"orgid\":\"001\"}}";
        }
        //  // 任务信息下载
        if (url.equals(UrlConstant.TASK_INFO)) {
            return "{\"code\":\"00\",\"data\":[{\"id\":\"12\",\"line_id\":\"1\",\"task_type\":\"类型2\",\"task_status\":\"01\",\"net_id\":\"002\",\"orgid\":\"1\",\"cashbox_num\":\"C0000002\"},{\"id\":\"13\",\"line_id\":\"1\",\"task_type\":\"类型1\",\"task_status\":\"01\",\"net_id\":\"001\",\"orgid\":\"2\",\"cashbox_num\":\"C0000001\"},{\"id\":\"14\",\"line_id\":\"1\",\"task_type\":\"类型1\",\"task_status\":\"01\",\"net_id\":\"001\",\"orgid\":\"2\",\"cashbox_num\":\"C0000003\"}]}";
        }

        // 任务记录上传
        if (url.equals(UrlConstant.UPLOAD_RECORD)) {
            return "{\"code\": \"00\",\"message\": \"任务记录上传成功\"}";
        }

        // 退出
        if (url.equals(UrlConstant.USER_LOGOUT)) {
            return "{\"code\": \"00\",\"message\": \"退出成功\"}";
        }

        //获取apk升级信息
        if (url.equals(UrlConstant.GET_APK_UPDATE)) {
            return "{\"code\":\"00\",\"data\":{app_id:\"zsws\",version:\"1.0.0\",content:\"强制升级\",forced_update:\"1\",url:\"http://127.0.0.1/\"}}";
        }
        return "{\"code\": \"99\",\"message\": \"错误的url\"}";
    }
}

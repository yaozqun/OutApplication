package com.seatwe.zsws.web;

import com.google.gson.reflect.TypeToken;
import com.grgbanking.baselib.core.BaseService;
import com.grgbanking.baselib.core.callback.JsonCallback;
import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.web.WebService;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.bean.req.BaseInfoReqBean;
import com.grgbanking.baselib.web.response.ResponseRoot;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.req.LogoutReqBean;
import com.seatwe.zsws.bean.req.RecordReqBean;
import com.seatwe.zsws.bean.req.TaskInfoReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;

import java.util.List;

public class NetService extends BaseService {
    private static NetService service;

    private NetService() {
    }

    public static NetService getInstance() {
        if (service == null) {
            synchronized (NetService.class) {
                if (service == null) {
                    service = new NetService();
                }
            }
        }
        return service;
    }

    /**
     * 登录
     *
     * @param callback
     */
    public void login(LoginReqBean req, ResultCallback<LoginRespBean> callback) {
        WebService.getInstance().asyncPost(UrlConstant.USER_LOGIN, req, new
                JsonCallback<LoginRespBean>(new TypeToken<ResponseRoot<LoginRespBean>>() {
        }.getType(),
                callback));

    }

    /**
     * 下载钞箱信息
     *
     * @param callback
     */
    public void downCashboxInfo(ResultCallback<List<CashBoxData>> callback) {
        BaseInfoReqBean req = new BaseInfoReqBean();
        req.setVersion("1");
        WebService.getInstance().asyncPost(UrlConstant.GET_CASHBOX, req, new
                JsonCallback<List<CashBoxData>>(new TypeToken<ResponseRoot<List<CashBoxData>>>() {
        }.getType(),
                callback));
    }

    /**
     * 下载网点信息
     *
     * @param callback
     */
    public void downNetInfo(ResultCallback<List<NetInfoData>> callback) {
        BaseInfoReqBean req = new BaseInfoReqBean();
        req.setVersion("1");
        WebService.getInstance().asyncPost(UrlConstant.GET_NET_INFO, req, new
                JsonCallback<List<NetInfoData>>(new TypeToken<ResponseRoot<List<NetInfoData>>>() {
        }.getType(),
                callback));
    }


    /**
     * 下载任务
     *
     * @param callback
     */
    public void downTask(ResultCallback<List<TaskInfoData>> callback) {
        TaskInfoReqBean req = new TaskInfoReqBean();
        req.setId(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId());
        WebService.getInstance().asyncPost(UrlConstant.TASK_INFO, req, new
                JsonCallback<List<TaskInfoData>>(new TypeToken<ResponseRoot<List<TaskInfoData>>>() {
        }.getType(),
                callback));
    }

    /**
     * 注销登录--------返回结果
     *
     * @param callback
     */
    public void loginOut(ResultCallback<ResponseBean> callback) {
        LogoutReqBean req = new LogoutReqBean();
        req.setLine_id(TaskInfoBusinessUtil.getInstance().queryAllTaskInfo().get(0).getLine_id() + "");
        req.setLogin_name("");//登录名
        WebService.getInstance().asyncPost(UrlConstant.TASK_INFO, req, new
                JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
        }.getType(),
                callback));
    }

    /**
     * 到达节点---------返回结果
     *
     * @param callback
     */
    public void arriveNode(ArriveNodeReqBean req, ResultCallback<ResponseBean> callback) {
        WebService.getInstance().asyncPost(UrlConstant.TASK_INFO, req, new
                JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
        }.getType(),
                callback));
    }

    /**
     * 上传记录---------返回结果
     *
     * @param callback
     */
    public void uploadRecord(RecordReqBean req, ResultCallback<ResponseBean> callback) {
        WebService.getInstance().asyncPost(UrlConstant.TASK_INFO, req, new
                JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
        }.getType(),
                callback));
    }

    /**
     * 修改密码----------请求参数+返回结果
     *
     * @param callback
     */
    public void changePassword(RecordReqBean req, ResultCallback<LoginRespBean> callback) {
        WebService.getInstance().asyncPost(UrlConstant.TASK_INFO, req, new
                JsonCallback<LoginRespBean>(new TypeToken<LoginRespBean>() {
        }.getType(),
                callback));
    }
}
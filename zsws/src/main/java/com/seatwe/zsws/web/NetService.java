package com.seatwe.zsws.web;

import com.google.gson.reflect.TypeToken;
import com.grgbanking.baselib.core.BaseService;
import com.grgbanking.baselib.core.callback.JsonCallback;
import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.web.WebService;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.bean.req.BaseInfoReqBean;
import com.grgbanking.baselib.web.bean.req.ChangePasswordReqBean;
import com.grgbanking.baselib.web.bean.resp.CashBoxRespBean;
import com.grgbanking.baselib.web.bean.resp.NetInfoRespBean;
import com.grgbanking.baselib.web.response.ResponseRoot;
import com.grgbanking.baselib.web.util.FastJsonUtils;
import com.seatwe.zsws.TestDatas;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.req.LogoutReqBean;
import com.seatwe.zsws.bean.req.RecordReqBean;
import com.seatwe.zsws.bean.req.TaskInfoReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.bean.resp.TaskInfoRespBean;
import com.seatwe.zsws.constant.AppUserConfig;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.util.SharePrefUtil;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;

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
        if (AppUserConfig.isTest) {
            try {
                LoginRespBean respData = FastJsonUtils.getSingleBean(TestDatas.testSer(UrlConstant.USER_LOGIN), LoginRespBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.USER_LOGIN, req, new
                    JsonCallback<LoginRespBean>(new TypeToken<ResponseRoot<LoginRespBean>>() {
            }.getType(),
                    callback));
        }


    }

    /**
     * 下载钞箱信息
     *
     * @param callback
     */
    public void downCashboxInfo(ResultCallback<CashBoxRespBean> callback) {
        BaseInfoReqBean req = new BaseInfoReqBean();
        req.setVersion(CashboxBaseBusinessUtil.getInstance().queryCurrentVersion());//当前版本号

        if (AppUserConfig.isTest) {
            try {
                CashBoxRespBean respData = FastJsonUtils.getSingleBean(TestDatas.testSer(UrlConstant.GET_CASHBOX), CashBoxRespBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.GET_CASHBOX, req, new
                    JsonCallback<CashBoxRespBean>(new TypeToken<ResponseRoot<CashBoxRespBean>>() {
            }.getType(),
                    callback));
        }

    }

    /**
     * 下载网点信息
     *
     * @param callback
     */
    public void downNetInfo(ResultCallback<NetInfoRespBean> callback) {
        BaseInfoReqBean req = new BaseInfoReqBean();
        req.setVersion(NetInfoBusinessUtil.getInstance().queryCurrentVersion());
        if (AppUserConfig.isTest) {
            try {
                NetInfoRespBean respData = FastJsonUtils.getSingleBean(TestDatas.testSer(UrlConstant.GET_NET_INFO), NetInfoRespBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.GET_NET_INFO, req, new
                    JsonCallback<NetInfoRespBean>(new TypeToken<ResponseRoot<NetInfoRespBean>>() {
            }.getType(),
                    callback));
        }

    }


    /**
     * 下载任务
     *
     * @param callback
     */
    public void downTask(ResultCallback<TaskInfoRespBean> callback) {
        TaskInfoReqBean req = new TaskInfoReqBean();
        req.setId(Integer.parseInt(LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId()));

        if (AppUserConfig.isTest) {
            try {
                TaskInfoRespBean respData = FastJsonUtils.getSingleBean(TestDatas.testSer(UrlConstant.TASK_INFO), TaskInfoRespBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.TASK_INFO, req, new
                    JsonCallback<TaskInfoRespBean>(new TypeToken<ResponseRoot<TaskInfoRespBean>>() {
            }.getType(),
                    callback));
        }

    }

    /**
     * 注销登录--------返回结果
     *
     * @param callback
     */
    public void loginOut(ResultCallback<ResponseBean> callback) {
        LogoutReqBean req = new LogoutReqBean();
        req.setLine_id(SharePrefUtil.getInstance().getUserInfo().getLine_id());
        req.setLogin_name(SharePrefUtil.getInstance().getUserInfo().getLogin_name());//登录名

        if (AppUserConfig.isTest) {
            ResponseBean respData = null;
            try {
                respData = FastJsonUtils.getSingleBean(TestDatas.testSer(UrlConstant.USER_LOGOUT), ResponseBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            WebService.getInstance().asyncGet(UrlConstant.TASK_INFO, req, new
                    JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
            }.getType(),
                    callback));
        }

    }

    /**
     * 到达节点---------返回结果
     *
     * @param callback
     */
    public void arriveNode(ArriveNodeReqBean req, ResultCallback<ResponseBean> callback) {
        if (AppUserConfig.isTest) {
            try {
                ResponseBean respData = FastJsonUtils.getSingleBean(new TestDatas().testSer(UrlConstant.ARRIVE_NODE), ResponseBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.ARRIVE_NODE, req, new
                    JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
            }.getType(),
                    callback));
        }

    }

    /**
     * 上传记录---------返回结果
     *
     * @param callback
     */
    public void uploadRecord(RecordReqBean req, ResultCallback<ResponseBean> callback) {
        if (AppUserConfig.isTest) {
            try {
                ResponseBean respData = FastJsonUtils.getSingleBean(new TestDatas().testSer(UrlConstant.UPLOAD_RECORD), ResponseBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.UPLOAD_RECORD, req, new
                    JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
            }.getType(),
                    callback));
        }

    }


    /**
     * 修改密码----------请求参数+返回结果
     *
     * @param callback
     */
    public void changePassword(ChangePasswordReqBean req, ResultCallback<ResponseBean> callback) {
        if (AppUserConfig.isTest) {
            try {
                ResponseBean respData = FastJsonUtils.getSingleBean(new TestDatas().testSer(UrlConstant.CHANGE_PASSWORD), ResponseBean.class);
                callback.onSuccess(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            WebService.getInstance().asyncGet(UrlConstant.CHANGE_PASSWORD, req, new
                    JsonCallback<ResponseBean>(new TypeToken<ResponseBean>() {
            }.getType(),
                    callback));
        }
    }
}
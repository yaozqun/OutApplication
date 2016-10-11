package com.seatwe.zsws.web;

import com.google.gson.reflect.TypeToken;
import com.grgbanking.baselib.core.BaseService;
import com.grgbanking.baselib.core.callback.JsonCallback;
import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.web.WebService;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.constant.UrlConstant;

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
  public void login(LoginReqBean req,ResultCallback<LoginRespBean> callback) {
    //测试
    test(new JsonCallback<LoginRespBean>(new TypeToken<LoginRespBean>() {
    }.getType(), callback));
    
    /*
     * WebService.getInstance().asyncPost(UrlConstant.USER_LOGIN, req, new
     * JsonCallback<LoginRespBean>(new TypeToken<LoginRespBean>() { }.getType(),
     * callback));
     */
  }
}
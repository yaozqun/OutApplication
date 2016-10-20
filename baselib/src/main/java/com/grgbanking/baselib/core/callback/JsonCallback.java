package com.grgbanking.baselib.core.callback;

import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

import com.grgbanking.baselib.util.JsonUtils;
import com.grgbanking.baselib.util.SecurityUtils;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.response.ResponseRoot;
import com.grgbanking.baselib.web.entity.ErrorMsg;

public class JsonCallback<T> extends BaseCallback<T> {
    public final static String TAG = "JsonCallback";
    Type type;

    public JsonCallback(Type type, ResultCallback<T> resultCallback) {
        super(resultCallback);
        this.type = type;
    }

    public JsonCallback(Type type, ResultCallback<T> resultCallback, CallbackHandler handler) {
        super(resultCallback, handler);
        this.type = type;

    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            if (!response.isSuccessful()) {
                LogUtil.i(TAG, "onResponse Failure:" + response.code());
                onFailure(call, new ErrorMsg(response.code()));
                return;
            }
            String bodyStr = SecurityUtils.aesDecrypt(response.body().string(), SecurityUtils.MESSAGE_AES_KEY);
            String url = response.request().url().url().toString();
            LogUtil.i(TAG, "onResponse :   url=" + url + " bodyStr= " + bodyStr);
            ResponseRoot respRoot = JsonUtils.fromJson(bodyStr, type);
//            String code = respRoot.getCode();
//            String msg = respRoot.getMsg();
            if (respRoot.isSuccess()) {
                onSuccess(call, response, respRoot);
                return;
            } else {
                onFailure(call, new ErrorMsg(Integer.parseInt(respRoot.getCode()), respRoot.getMsg()));
                return;
            }

        } catch (Exception e) {
            LogUtil.e(TAG, "onResponse", e);
            onFailure(call, new ErrorMsg(ErrorMsg.CODE_WEB_ERROE));
        }
    }

    public void onSuccess(Call call, Response response, ResponseRoot respRoot) {
        onSuccess(call, response, (T) respRoot.getData());
    }


}
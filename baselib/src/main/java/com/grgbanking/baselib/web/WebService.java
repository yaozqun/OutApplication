package com.grgbanking.baselib.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Context;
import android.util.Log;

import com.grgbanking.baselib.util.JsonUtils;
import com.grgbanking.baselib.util.SecurityUtils;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.okhttp.ProgressListener;
import com.grgbanking.baselib.util.NetWorkUtil;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.grgbanking.baselib.web.okhttp.ProgressRequestBody;
import com.grgbanking.baselib.web.request.RequestHeader;

public class WebService {
    private static final int TIME_OUT = 5;//单位：s
    public final static String TAG = "WebService";
    private static WebService webService;
    private Context ctx;
    private static OkHttpClient okHttp;

    private RequestHeader requestHeader;

    private WebService(Context ctx) {
        this.ctx = ctx;
    }

    public static void init(Context ctx) {
        webService = new WebService(ctx);
        okHttp = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(TIME_OUT, TimeUnit.MINUTES)
                .writeTimeout(TIME_OUT, TimeUnit.MINUTES)
                .build();
    }

    public static WebService getInstance() {
        if (webService == null) {
            throw new RuntimeException("WebService must be init(),before use");
        }
        return webService;
    }

    /**
     * 开启异步网络请求
     */
    public void asyncGet(final String url, WebCallback callback) {
        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            Request request = new Request.Builder().url(url).build();
            Call call = okHttp.newCall(request);
            callback.onPre(call);
            call.enqueue(callback);
            LogUtil.i(TAG, "asyncGet :    url=" + url);
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }

    /**
     * 开启异步网络请求
     */
    public void asyncGet(final String url, Object req, WebCallback callback) {
        String json = null;
        String uri = url;
        try {
            String temp = JsonUtils.toJson(req);
            Log.e("json", temp);
            //加密
            json = SecurityUtils.aesEncrypt(JsonUtils.toJson(req), SecurityUtils.MESSAGE_AES_KEY);
            uri = url + "?param=" + json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("json", json);
        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            Request request = new Request.Builder().url(uri).build();
            Call call = okHttp.newCall(request);
            callback.onPre(call);
            call.enqueue(callback);
            LogUtil.i(TAG, "asyncGet :    url=" + uri);
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }


    /**
     * 开启异步网络请求
     */
    public void asyncPost(final String url, final Object req, WebCallback callback) {

        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            String json = null;
            try {
                //加密
                json = SecurityUtils.aesEncrypt(JsonUtils.toJson(req), SecurityUtils.MESSAGE_AES_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("json", json);
            RequestBody body = new FormBody.Builder().add("params", json).build();
            Request request = new Request.Builder().url(url).post(body).build();
            Call call = okHttp.newCall(request);
            callback.onPre(call);
            call.enqueue(callback);
            LogUtil.i(TAG, "asyncPost :    url=" + url + "?param=" + JsonUtils.toJson(req));
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }

    /**
     * 不是异步请求，需要在子线程执行
     */
    public void syncPost(final String url, final String json, WebCallback callback) throws Exception {
        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            //加密
            String reqStr = SecurityUtils.aesEncrypt(json, SecurityUtils.MESSAGE_AES_KEY);
            RequestBody body = new FormBody.Builder().add("", reqStr).build();
            Request request = new Request.Builder().url(url).post(body).build();
            Call call = okHttp.newCall(request);
            callback.onPre(call);
            try {
                LogUtil.i(TAG, "asyncPost :    url=" + url + ";param=" + json);
                Response response = call.execute();
                callback.onResponse(call, response);
            } catch (IOException e) {
                callback.onFailure(call, e);
            }
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }

    public void upLoadFile(final String url, final Object req, Map<String, File> files, WebCallback callback, ProgressListener progressListener) {
        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            String json = JsonUtils.toJson(req);
            upLoadFile(url, json, files, callback, progressListener);
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }

    public void upLoadFile(final String url, final String json, Map<String, File> files, WebCallback callback, ProgressListener progressListener) {
        if (NetWorkUtil.isNetWorkConnected(ctx)) {
            //构造上传请求，类似web表单
            MultipartBody.Builder requestBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("param", json);
            for (String key : files.keySet()) {
                File file = files.get(key);
                requestBuilder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
            }
            //进行包装，使其支持监听上传进度
            final Request request = new Request.Builder().url(url).post(new ProgressRequestBody(requestBuilder.build(), progressListener)).build();
//            final Request request = new Request.Builder().url(url).post(requestBuilder.build()).build();
            Call call = okHttp.newCall(request);
            callback.onPre(call);
            call.enqueue(callback);
            LogUtil.i(TAG, "asyncPost :    url=" + url + ";param=" + json);
        } else {
            callback.onFailure(null, new ErrorMsg(ErrorMsg.CODE_NO_NETWORK));
        }
    }
}

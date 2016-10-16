package com.seatwe.zsws.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.JsonUtils;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.TestDatas;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.bean.resp.LoginRespBean;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private EditTextToDel et_userName, et_pwd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        bt_login = (Button) findViewById(R.id.bt_login);
        et_userName = (EditTextToDel) findViewById(R.id.et_userName);
        et_pwd = (EditTextToDel) findViewById(R.id.et_pwd);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    // 下载测试数据
    public void test() {

        JSONObject object1 = null;
        String data1 = null;
        try {
            object1 = new JSONObject(TestDatas.testSer(UrlConstant.USER_LOGIN));
            data1 = object1.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LineInfoData resp1 = JsonUtils.fromJson(data1, LineInfoData.class);
        //登录成功之后，获取到线路信息，若本地有数据，先与本地数据相比较，若线路id相同，则不下载，若不同，则先清除原有数据，在下载新数据
        if (LineInfoBusinessUtil.getInstance().queryAllLineInfo() == null) {
            downloadTestData();
        } else {
            if (resp1.getId() != LineInfoBusinessUtil.getInstance().queryAllLineInfo().getId()) {
                CashboxBaseBusinessUtil.getInstance().clearCashboxInfo();
                LineInfoBusinessUtil.getInstance().clearLineInfo();
                LineNodeBusinessUtil.getInstance().clearLineNodeInfo();
                NetInfoBusinessUtil.getInstance().clearNetInfo();
                TaskInfoBusinessUtil.getInstance().clearTaskInfo();
            }

        }
        startActivity(new Intent(this, MainActivity.class));
    }

    private void downloadTestData() {
        try {
            JSONObject object1 = new JSONObject(TestDatas.testSer(UrlConstant.USER_LOGIN));
            String data1 = object1.getString("data");
            LineInfoData resp1 = JsonUtils.fromJson(data1, LineInfoData.class);
            LineInfoBusinessUtil.getInstance().saveLineInfoData(resp1);
            LogUtil.e("线路信息", "线路编号：" + LineInfoBusinessUtil.getInstance().queryAllLineInfo().getLine_number());

            //下载任务信息
            JSONObject object2 = new JSONObject(TestDatas.testSer(UrlConstant.TASK_INFO));
            List<TaskInfoData> list2 = new ArrayList<TaskInfoData>();
            JSONArray arr2 = object2.getJSONArray("data");
            for (int i = 0; i < arr2.length(); i++) {
                list2.add(JsonUtils.fromJson(arr2.get(i).toString(), TaskInfoData.class));
            }
            TaskInfoBusinessUtil.getInstance().saveTaskInfoData(list2);
            LogUtil.e("任务信息", "钞箱编号：" + TaskInfoBusinessUtil.getInstance().queryAllTaskInfo().get(0).getCashbox_num());
            //任务信息下载完成后,将钞箱信息保存到钞箱操作记录表中
            for(TaskInfoData data:list2){
                RecordboxInfoData info = new RecordboxInfoData();
                info.setBox_code(data.getCashbox_num());
                info.setTransfer_net(data.getNet_id());
                info.setTransfer_status(data.getTask_status());
                info.setCashbox_type(CashboxTypeConstant.TYPE_SEND);
                info.setTransfer_type(data.getTask_type());
                RecordBoxBusinessUtil.getInstance().createOrUpdate(info);
            }
            LogUtil.e("钞箱记录信息", "钞箱编号：" + RecordBoxBusinessUtil.getInstance().queryAllRecordbox().get(0).getBox_code());


            //下载钞箱信息
            JSONObject object3 = new JSONObject(TestDatas.testSer(UrlConstant.GET_CASHBOX));
            List<CashBoxData> list3 = new ArrayList<CashBoxData>();
            JSONArray arr3 = object3.getJSONArray("data");
            for (int i = 0; i < arr3.length(); i++) {
                list3.add(JsonUtils.fromJson(arr3.get(i).toString(), CashBoxData.class));
            }
            CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(list3);
            List<CashBoxData> ll = CashboxBaseBusinessUtil.getInstance().queryAllCashboxInfo();
            LogUtil.e("钞箱信息", "网点编号：" + CashboxBaseBusinessUtil.getInstance().queryAllCashboxInfo().get(0).getCashbox_num());

            //下载网点信息
            JSONObject object4 = new JSONObject(TestDatas.testSer(UrlConstant.GET_NET_INFO));
            List<NetInfoData> list4 = new ArrayList<NetInfoData>();
            JSONArray arr4 = object4.getJSONArray("data");
            for (int i = 0; i < arr4.length(); i++) {
                list4.add(JsonUtils.fromJson(arr4.get(i).toString(), NetInfoData.class));
            }
            NetInfoBusinessUtil.getInstance().saveNetInfoData(list4);
            LogUtil.e("网点信息", "网点名称：" + NetInfoBusinessUtil.getInstance().queryAllNetInfo().get(0).getNet_name());

            //保存节点信息
            List<ArriveNodeReqBean> list5 = new ArrayList<ArriveNodeReqBean>();
            String[] nodeName = new String[]{getResources().getString(R.string.node_name_start), getResources().getString(R.string.node_name_end)};
            for (int i = 0; i < nodeName.length; i++) {
                ArriveNodeReqBean bean = new ArriveNodeReqBean();
                bean.setNode_name(nodeName[i]);
                bean.setLine_id(i);
                bean.setNode_type(i + "");
                bean.setLocalStatus(LocalStatusConstant.UN_DONE);
                bean.setLine_id(i);
                bean.setArrive_time("20161012202822");
                list5.add(bean);
            }
            LineNodeBusinessUtil.getInstance().saveLineNodeInfoData(list5);
            LogUtil.e("线路节点信息", "线路节点名称：" + LineNodeBusinessUtil.getInstance().queryAllLineNodeInfo().get(0).getNode_name());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否输入用户名和密码
     *
     * @return
     */
    private boolean checkInput() {
        String username = et_userName.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.shortShow("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.shortShow("密码不能为空");
            return false;
        }
        return true;
    }

    /**
     * 登录请求
     */
    public void login() {
        progressDialog = ProgressUtil.show(this, "登录中...");
        final LoginReqBean reqBean = new LoginReqBean();
        reqBean.setLogin_name(et_userName.getText().toString().trim());
        reqBean.setLogin_password(et_pwd.getText().toString().trim());
        NetService.getInstance().login(reqBean,
                new ResultCallback<LoginRespBean>() {
                    @Override
                    public void onSuccess(LoginRespBean resp) {
                        progressDialog.dismiss();
                        // 保存数据
                        if (resp.getData() != null) {
                            LineInfoBusinessUtil.getInstance().saveLineInfoData(resp.getData());
                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onPre(Call call) {
                        progressDialog.dismiss();
                    }
                });
    }
}

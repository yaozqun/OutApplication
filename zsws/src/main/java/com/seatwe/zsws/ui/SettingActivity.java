package com.seatwe.zsws.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ActivityManagerUtil;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.bean.resp.CashBoxRespBean;
import com.grgbanking.baselib.web.bean.resp.NetInfoRespBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import okhttp3.Call;

/**
 * Created by charry on 2016/10/7.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    protected static final String tag = SettingActivity.class.getSimpleName();

    private TextView versionTv;
    private TextView versionInfoTv;
    private EditText net;
    private Button saveNetButton;
    private Button logoutButton;
    private Button modifyPasswordButton;
    private Button uprogressDialogateInfoButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initOnclickListener();
    }

    private void initView() {
        versionTv = (TextView) findViewById(R.id.setting_version);
        versionInfoTv = (TextView) findViewById(R.id.setting_version_info);
        net = (EditText) findViewById(R.id.setting_server_one_remote_et);
        saveNetButton = (Button) findViewById(R.id.setting_server_commit_btn);
        logoutButton = (Button) findViewById(R.id.setting_logout_btn);
        modifyPasswordButton = (Button) findViewById(R.id.setting_modify_password_btn);
        uprogressDialogateInfoButton = (Button) findViewById(R.id.setting_update_box_net_btn);

        boolean isLogin = getIntent().getBooleanExtra("login", false);
        if (isLogin) {
            logoutButton.setVisibility(View.VISIBLE);
            modifyPasswordButton.setVisibility(View.VISIBLE);
            net.setEnabled(false);
            saveNetButton.setEnabled(false);
            saveNetButton.setVisibility(View.GONE);
        } else {
            logoutButton.setVisibility(View.GONE);
            modifyPasswordButton.setVisibility(View.GONE);
            net.setEnabled(true);
            saveNetButton.setEnabled(true);
            saveNetButton.setVisibility(View.VISIBLE);
        }

    }

    private void initOnclickListener() {
        saveNetButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        modifyPasswordButton.setOnClickListener(this);
        uprogressDialogateInfoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_modify_password_btn:
                startActivity(new Intent(SettingActivity.this,MainActivity.class));
                break;
            case R.id.setting_logout_btn:
                logout();
                break;
            case R.id.setting_server_commit_btn:
                saveNetConfig();
                break;
            case R.id.setting_update_box_net_btn:
                downLoadNet();
                downLoadCashBox();
                break;
        }
    }

    private void logout() {
        progressDialog = ProgressUtil.show(this,"正在注销退出系统...");
        try {
            NetService.getInstance().loginOut(
                    new ResultCallback<ResponseBean>() {
                        @Override
                        public void onSuccess(ResponseBean resp) {
                            progressDialog.dismiss();
                            ActivityManagerUtil.finishAll();
                            startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                            finish();
                            System.out.print(resp);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            ToastUtil.shortShow(errorMsg.getMessage());
                                progressDialog.dismiss();
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveNetConfig() {
        UrlConstant.BASE_HOST = net.getText().toString() + "/baselib/";
        UrlConstant.HOST = net.getText().toString() + "/jkgl/";
//doing保存本地
    }

    private void downLoadCashBox() {
        progressDialog = ProgressUtil.show(this,"正在更新基础信息（款箱）...");
        try {
            NetService.getInstance().downCashboxInfo(
                    new ResultCallback<CashBoxRespBean>() {
                        @Override
                        public void onSuccess(CashBoxRespBean resp) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            try {
                                SaveCashBox(resp);
                            } catch (Exception e) {
                                Log.d(tag, e.toString());
                            }
                            System.out.print(resp);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            ToastUtil.shortShow(errorMsg.getMessage());
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveCashBox(CashBoxRespBean resprogressDialogata) {
        try {
            if (resprogressDialogata.isSuccess()) {
                // 返回标识为成功时所执行的操作
                CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(resprogressDialogata.getData());
                ToastUtil.shortShow("更新款箱信息完成");
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            } else {
                // 返回标识为失败时所执行的操作
                ToastUtil.longShow(resprogressDialogata.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downLoadNet() {
        progressDialog = ProgressUtil.show(this,"正在更新基础信息（网点）...");
        try {
            NetService.getInstance().downNetInfo(
                    new ResultCallback<NetInfoRespBean>() {
                        @Override
                        public void onSuccess(NetInfoRespBean resp) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            try {
                                SaveNet(resp);
                            } catch (Exception e) {
                                Log.d(tag, e.toString());
                            }
                            System.out.print(resp);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            ToastUtil.shortShow(errorMsg.getMessage());
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
           /* if (TestContant.isTest) {
                progressDialog.dismiss();
                try {
                    NetInfoRespBean loginResprogressDialogata = FastJsonUtils.getSingleBean(new TestDatas().testSer(UrlConstant.GET_NET_INFO), NetInfoRespBean.class);
                    SaveNet(loginResprogressDialogata);
                } catch (Exception e) {
                    Log.d(tag, e.toString());
                }
            } else
                NetOkHttpUtils.post(UrlConstant.GET_NET_INFO, infoReqBean, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            NetInfoRespBean resprogressDialogata = FastJsonUtils.getSingleBeanForRespone(response, NetInfoRespBean.class);
                            SaveNet(resprogressDialogata);
                        } catch (Exception e) {
                            Log.d(tag, e.toString());
                        }
                        System.out.print(response);
                    }
                });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveNet(NetInfoRespBean resprogressDialogata) {
        try {
            if (resprogressDialogata.isSuccess()) {
                // 返回标识为成功时所执行的操作
                NetInfoBusinessUtil.getInstance().saveNetInfoData(resprogressDialogata.getData());
                ToastUtil.shortShow("更新网点信息完成");
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            } else {
                // 返回标识为失败时所执行的操作
                ToastUtil.longShow(resprogressDialogata.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

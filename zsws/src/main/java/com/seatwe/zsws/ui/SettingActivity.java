package com.seatwe.zsws.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.bean.req.BaseInfoReqBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.constant.UrlConstant;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.CustomDialogUtil;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import java.util.List;

import okhttp3.Call;

/**
 * Created by charry on 2016/10/7.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    protected static final String tag = SettingActivity.class.getSimpleName();

    private EditText net;
    private Button updateAppButton;
    private Button saveNetButton;
    private Button logoutButton;
    private Button modifyPasswordButton;
    private Button updateInfoButton;
    ProgressDialog pD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initOnclickListener();
    }

    private void initView() {
        updateAppButton = (Button) findViewById(R.id.setting_update_app_btn);
        net = (EditText) findViewById(R.id.setting_server_one_remote_et);
        saveNetButton = (Button) findViewById(R.id.setting_server_commit_btn);
        logoutButton = (Button) findViewById(R.id.setting_logout_btn);
        modifyPasswordButton = (Button) findViewById(R.id.setting_modify_password_btn);
        updateInfoButton = (Button) findViewById(R.id.setting_update_box_net_btn);

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
        net.setText(UrlConstant.HOST);
    }

    private void initOnclickListener() {
        saveNetButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        modifyPasswordButton.setOnClickListener(this);
        updateInfoButton.setOnClickListener(this);
        updateAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_modify_password_btn:
                startActivity(new Intent(SettingActivity.this,ModifyPasswordActivity.class));
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
            case R.id.setting_update_app_btn:
                checkUpdateApp();
                break;
        }
    }

    private void saveNetConfig() {

    }

    private void logout() {
        CustomDialogUtil.loginOut(this);
    }

    private void checkUpdateApp() {

    }

    private void downLoadCashBox() {
        pD = ProgressUtil.show(SettingActivity.this,"正在更新基础信息（款箱）...");
        try {
            NetService.getInstance().downCashboxInfo(new ResultCallback<List<CashBoxData>>() {
                        @Override
                        public void onSuccess(List<CashBoxData> resp) {
                            if (pD.isShowing())
                                pD.dismiss();
                            try {
                                if (resp != null) {
                                    // 返回标识为成功时所执行的操作
                                    CashboxBaseBusinessUtil.getInstance().saveCashboxInfoData(resp);
                                    ToastUtil.shortShow("更新款箱信息完成");
                                }
                            } catch (Exception e) {
                                Log.d(tag, e.toString());
                            }
                            System.out.print(resp);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            ToastUtil.shortShow(errorMsg.getMessage());
                            if (pD.isShowing())
                                pD.dismiss();
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (pD.isShowing())
                                pD.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downLoadNet() {
        pD = ProgressUtil.show(SettingActivity.this,"正在更新基础信息（网点）...");
        try {
            BaseInfoReqBean infoReqBean = new BaseInfoReqBean();

            NetService.getInstance().downNetInfo(new ResultCallback<List<NetInfoData>>() {
                        @Override
                        public void onSuccess(List<NetInfoData> resp) {
                            if (pD.isShowing())
                                pD.dismiss();
                            try {
                                if (resp != null) {
                                    // 返回标识为成功时所执行的操作
                                    NetInfoBusinessUtil.getInstance().saveNetInfoData(resp);
                                    ToastUtil.shortShow("更新网点信息完成");
                                }
                            } catch (Exception e) {
                                Log.d(tag, e.toString());
                            }
                            System.out.print(resp);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            ToastUtil.shortShow(errorMsg.getMessage());
                            if (pD.isShowing())
                                pD.dismiss();
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (pD.isShowing())
                                pD.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

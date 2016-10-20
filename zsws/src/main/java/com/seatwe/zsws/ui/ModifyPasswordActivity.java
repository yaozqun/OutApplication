package com.seatwe.zsws.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.bean.req.ChangePasswordReqBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.SharePrefUtil;
import com.seatwe.zsws.web.NetService;

import okhttp3.Call;

/**
 * Created by charry on 2016/10/18.
 */
public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {
    private Button saveButton;
    private ImageButton oldPasswordButton;
    private ImageButton newPasswordButton;
    private ImageButton confirmPasswordButton;
    private EditText oldPasswordTv;
    private EditText newPasswordTv;
    private EditText confirmPasswordTv;
    private static final String tag = ModifyPasswordActivity.class.getSimpleName();

    private ProgressDialog pD;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        initView();
        initOnclickListener();
    }

    private void initView() {
        oldPasswordButton = (ImageButton) findViewById(R.id.modify_password_old_password_btn);
        oldPasswordTv = (EditText) findViewById(R.id.modify_password_old_password);
        newPasswordButton = (ImageButton) findViewById(R.id.modify_password_new_password_btn);
        newPasswordTv = (EditText) findViewById(R.id.modify_password_new_password);
        confirmPasswordButton = (ImageButton) findViewById(R.id.modify_password_confirm_password_btn);
        confirmPasswordTv = (EditText) findViewById(R.id.modify_password_confirm_password);
        saveButton = (Button) findViewById(R.id.modify_password_commit_btn);

    }

    private void initOnclickListener() {
        oldPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPasswordTv.setTransformationMethod(null);
            }
        });

        newPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPasswordTv.setTransformationMethod(null);
            }
        });

        confirmPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPasswordTv.setTransformationMethod(null);
            }
        });

        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_password_commit_btn:
//                confirmPassword();
                break;
        }
    }

    private boolean validatePassword() {
        if (!SharePrefUtil.getInstance().getUserInfo().getLogin_password().equals(oldPasswordTv.getText().toString().trim())) {
            ToastUtil.shortShow("请输入正确的旧密码");
            oldPasswordTv.requestFocus();
            return false;
        }
        if ("".equals(newPasswordTv.getText().toString().trim())) {
            ToastUtil.shortShow("请输入新密码");
            newPasswordTv.requestFocus();
            return false;
        }
        if ("".equals(confirmPasswordTv.getText().toString().trim())) {
            ToastUtil.shortShow("请输入确认密码");
            confirmPasswordTv.requestFocus();
            return false;
        }
        if (!newPasswordTv.getText().toString().equals(oldPasswordTv.getText().toString())) {
            ToastUtil.shortShow("新密码与旧密码相同，请重新输入新密码");
            newPasswordTv.requestFocus();
            return false;
        }
        if (!newPasswordTv.getText().toString().equals(confirmPasswordTv.getText().toString())) {
            ToastUtil.shortShow("新密码与确认密码不一致，请重新输入");
            confirmPasswordTv.requestFocus();
            return false;
        }
        return true;
    }

    private void confirmPassword() {
        if (validatePassword()) {
            pD = ProgressUtil.show(this, "正在保存密码...");
            ChangePasswordReqBean lInfo = new ChangePasswordReqBean();
            lInfo.setLogin_name(SharePrefUtil.getInstance().getUserInfo().getLogin_name());
            lInfo.setNewPw(newPasswordTv.getText().toString());
            lInfo.setOldPw(oldPasswordTv.getText().toString());
            NetService.getInstance().changePassword(lInfo,
                    new ResultCallback<ResponseBean>() {
                        @Override
                        public void onSuccess(ResponseBean respData) {
                            if (pD.isShowing())
                                pD.dismiss();
                            try {
                                if (respData.isSuccess()) {
                                    exitView(respData);
                                } else {
                                    // 返回标识为失败时所执行的操作
                                    ToastUtil.longShow(respData.getMessage());
                                }
                            } catch (Exception e) {
                                Log.d(tag, e.toString());
                            }
                            System.out.print(respData);
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            pD.dismiss();
                            ToastUtil.shortShow(errorMsg.getMessage());
                            System.out.print(errorMsg.getMessage());
                        }

                        @Override
                        public void onPre(Call call) {
                            if (pD.isShowing())
                                pD.dismiss();
                        }
                    });
           /* if (TestContant.isTest) {
                pD.dismiss();
                try {
                    ResponseBean respData = FastJsonUtils.getSingleBean(new TestDatas().testSer(UrlConstant.CHANGE_PASSWORD), ResponseBean.class);
                    exitView(respData);
                } catch (Exception e) {
                    // Log.d(tag, e.toString());
                }
            } else
                NetOkHttpUtils.post(UrlConstant.CHANGE_PASSWORD, lInfo, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (pD.isShowing())
                            pD.dismiss();
                        ToastUtil.longShow(e.getMessage());
                        System.out.print(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (pD.isShowing())
                            pD.dismiss();
                        try {
                            ResponseBean respData = FastJsonUtils.getSingleBeanForRespone(response, ResponseBean.class);
                            exitView(respData);
                        } catch (Exception e) {
                            //Log.d(tag, e.toString());
                        }
                        System.out.print(response);
                    }
                });*/
        }
    }

    private void exitView(ResponseBean respData) {
        ToastUtil.shortShow(respData.getMessage());
        finish();
    }

}

package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.ToastUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.req.LoginReqBean;
import com.seatwe.zsws.util.DownloadUtil;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private EditTextToDel et_userName, et_pwd;

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
                //测试代码
//                TestDownloadUtil.test(LoginActivity.this);
                if(checkInput()){
                    login();
                }
            }
        });
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
        final LoginReqBean reqBean = new LoginReqBean();
        reqBean.setLogin_name(et_userName.getText().toString().trim());
        reqBean.setLogin_password(et_pwd.getText().toString().trim());
        DownloadUtil.login(this, reqBean);
    }
}

package com.seatwe.zsws.ui;

import android.os.Bundle;

import com.seatwe.zsws.R;
import com.seatwe.zsws.ui.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.setting));
    }
}

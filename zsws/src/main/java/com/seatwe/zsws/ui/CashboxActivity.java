package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seatwe.zsws.R;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;

public class CashboxActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_sendBox, bt_receiveBox, bt_middleBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashbox);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.module_box));
        btnTitleRight.setVisibility(View.GONE);
        bt_sendBox = (Button) findViewById(R.id.bt_sendBox);
        bt_receiveBox = (Button) findViewById(R.id.bt_collectBox);
        bt_middleBox = (Button) findViewById(R.id.bt_middleBox);

        bt_sendBox.setOnClickListener(this);
        bt_receiveBox.setOnClickListener(this);
        bt_middleBox.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sendBox:
                ActivityJumpUtil.jumpToTypeActivity(CashboxActivity.this, CashboxTypeConstant.TYPE_SEND);
                break;

            case R.id.bt_collectBox:
                ActivityJumpUtil.jumpToTypeActivity(CashboxActivity.this, CashboxTypeConstant.TYPE_RECEIVE);
                break;

            case R.id.bt_middleBox:
                ActivityJumpUtil.jumpToTypeActivity(CashboxActivity.this, CashboxTypeConstant.TYPE_MIDDLE);
                break;
        }
    }
}

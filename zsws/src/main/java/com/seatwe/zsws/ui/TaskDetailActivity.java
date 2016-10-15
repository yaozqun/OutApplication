package com.seatwe.zsws.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

public class TaskDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_netName, tv_arriveTime, tv_boxSum;
    private Button bt_sendBox, bt_collectBox, bt_middleBox;

    private TaskInfoData taskInfoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        init();
    }

    public void init() {
        initView();
        initData();
    }

    public void initView() {
        tv_netName = (TextView) findViewById(R.id.tv_netName);
        tv_arriveTime = (TextView) findViewById(R.id.tv_arriveTime);
        tv_boxSum = (TextView) findViewById(R.id.tv_boxSum);
        bt_sendBox = (Button) findViewById(R.id.bt_sendBox);
        bt_collectBox = (Button) findViewById(R.id.bt_collectBox);
        bt_middleBox = (Button) findViewById(R.id.bt_middleBox);

        bt_sendBox.setOnClickListener(this);
        bt_collectBox.setOnClickListener(this);
        bt_middleBox.setOnClickListener(this);

    }

    public void initData() {
        //获取任务信息
        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        NetInfoData netInfoData =
                NetInfoBusinessUtil.getInstance().queryNetInfoById(taskInfoData.getNet_id());
        tv_netName.setText("网点：(" + netInfoData.getNet_code() + ")" + netInfoData.getNet_address());
        tv_arriveTime.setText("到达时间：" + taskInfoData.getArriveTime());

        int sendBoxCount = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND).size();
        int receiveBoxCount = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_RECEIVE).size();
        int middleBoxCount = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_MIDDLE).size();

        tv_boxSum.setText("送出" + sendBoxCount + "个，收取" + receiveBoxCount + "个，中调" + middleBoxCount + "个");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sendBox:
                ActivityJumpUtil.jumpToSenBoxActivity(TaskDetailActivity.this, taskInfoData);
                break;

            case R.id.bt_collectBox:

                break;

            case R.id.bt_middleBox:
                startActivity(new Intent(TaskDetailActivity.this, ChooseNetActivity.class));
                break;
        }
    }
}

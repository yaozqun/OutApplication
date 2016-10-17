package com.seatwe.zsws.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.ui.adapter.SendBoxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.UploadUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

public class TransferActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_sendBoxSum, tv_receiveBoxSum, tv_middleBoxSum;
    private AdaptScrViewListView asvlv_sendBox, asvlv_receiveBox, asvlv_middleBox;
    private Button bt_transferConfirm;

    private List<RecordboxInfoData> listSendBox, listReceiveBox, listMiddleBox;
    private SendBoxAdapter adapterSend, adapterReceive, adapterMiddle;

    private TaskInfoData taskInfoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.task_transfer));
        btnTitleRight.setVisibility(View.INVISIBLE);

        tv_sendBoxSum = (TextView) findViewById(R.id.tv_sendBoxSum);
        tv_receiveBoxSum = (TextView) findViewById(R.id.tv_receiveBoxSum);
        tv_middleBoxSum = (TextView) findViewById(R.id.tv_middleBoxSum);
        asvlv_sendBox = (AdaptScrViewListView) findViewById(R.id.asvlv_sendBox);
        asvlv_receiveBox = (AdaptScrViewListView) findViewById(R.id.asvlv_receiveBox);
        asvlv_middleBox = (AdaptScrViewListView) findViewById(R.id.asvlv_middleBox);
        bt_transferConfirm = (Button) findViewById(R.id.bt_transferConfirm);
        bt_transferConfirm.setOnClickListener(this);

        //获取任务信息
        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        listSendBox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND);
        listReceiveBox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_RECEIVE);
        listMiddleBox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_MIDDLE);

        tv_sendBoxSum.setText("送出款箱(共" + listSendBox.size() + "个)");
        tv_receiveBoxSum.setText("收取款箱(共" + listReceiveBox.size() + "个)");
        tv_middleBoxSum.setText("中调款箱(共" + listMiddleBox.size() + "个)");

        adapterSend = new SendBoxAdapter(this, listSendBox, false);
        adapterReceive = new SendBoxAdapter(this, listReceiveBox, false);
        adapterMiddle = new SendBoxAdapter(this, listMiddleBox, false);

        asvlv_sendBox.setAdapter(adapterSend);
        asvlv_receiveBox.setAdapter(adapterReceive);
        asvlv_middleBox.setAdapter(adapterMiddle);

    }

    @Override
    public void onClick(View v) {
        //确认交接
        UploadUtil.uploadRecord(TransferActivity.this, taskInfoData);//上传
        startActivity(new Intent(TransferActivity.this, TaskActivity.class));
    }
}

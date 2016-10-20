package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.ToastUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.SendBoxAdapter;
import com.seatwe.zsws.ui.base.ScanBaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.BarcodeScannedUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

public class SendBoxActivity extends ScanBaseActivity {
    private AdaptScrViewListView asvlv_cashbox;

    private SendBoxAdapter adapter;

    private List<RecordboxInfoData> listCashbox;

    private TaskInfoData taskInfoData;

    private TextView tv_boxSum;

    private EditTextToDel et_inputCode;

    private Button bt_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendbox);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.scan_cashbox));
        btnTitleRight.setText(getResources().getString(R.string.sure));
        //获取任务信息
        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);

        asvlv_cashbox = (AdaptScrViewListView) findViewById(R.id.asvlv_box);
        tv_boxSum = (TextView) findViewById(R.id.tv_boxSum);
        et_inputCode = (EditTextToDel) findViewById(R.id.et_inputCode);
        bt_sure = (Button) findViewById(R.id.bt_sure);

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcodeStr = et_inputCode.getText().toString().trim();
                if (barcodeStr == null || barcodeStr.equals("")) {
                    ToastUtil.shortShow("请先输入钞箱条码");
                } else {
                    int pos = BarcodeScannedUtil.scannedSuccess(SendBoxActivity.this, listCashbox, barcodeStr);
                    if (pos != -1) {
                        listCashbox.get(pos).setLocalStatus(LocalStatusConstant.DONE);
                        adapter.notifyDataSetChanged();
                        tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");
                    }

                }
            }
        });

        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        listCashbox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND);

        tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");

        adapter = new SendBoxAdapter(this, listCashbox, true);
        asvlv_cashbox.setAdapter(adapter);

        asvlv_cashbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = BarcodeScannedUtil.scannedSuccess(SendBoxActivity.this, listCashbox, listCashbox.get(position).getBox_code());
                if (pos != -1) {
                    listCashbox.get(pos).setLocalStatus(LocalStatusConstant.DONE);
                    adapter.notifyDataSetChanged();
                    tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");
                }
            }
        });

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        if (barcodeStr == null || barcodeStr.equals("")) {
            int pos = BarcodeScannedUtil.scannedSuccess(SendBoxActivity.this, listCashbox, barcodeStr);
            if (pos != -1) {
                listCashbox.get(pos).setLocalStatus(LocalStatusConstant.DONE);
                adapter.notifyDataSetChanged();
                tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");
            }
        }

    }

}

package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.SendBoxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.CashboxScannedUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

public class ScanboxActivity extends BaseActivity {
    private AdaptScrViewListView asvlv_cashbox;

    private TextView tv_sendDate, tv_sendNet, tv_boxSum;

    private EditTextToDel et_inputCode;

    private Button bt_sure;

    private SendBoxAdapter adapter;

    private List<RecordboxInfoData> listCashbox;

    //private TaskInfoData taskInfoData;

    private NetInfoData netInfoData;

    private String sendDate;

    private String cashboxType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanbox);
        init();
    }

    public void init() {
        initView();
        initListener();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.scan_cashbox));
        btnTitleRight.setVisibility(View.VISIBLE);
        btnTitleRight.setText(getResources().getString(R.string.sure));

        //获取网点信息
        netInfoData = (NetInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
//      netInfoData = NetInfoBusinessUtil.getInstance().queryNetInfoById(taskInfoData.getNet_id());
        sendDate = getIntent().getStringExtra(ActivityJumpUtil.CHOOSEN_DATE);
        cashboxType = getIntent().getStringExtra(ActivityJumpUtil.CASHBOX_TYPE);

        asvlv_cashbox = (AdaptScrViewListView) findViewById(R.id.asvlv_box);
        tv_sendDate = (TextView) findViewById(R.id.tv_sendDate);
        tv_sendNet = (TextView) findViewById(R.id.tv_sendNet);
        tv_boxSum = (TextView) findViewById(R.id.tv_boxSum);
        et_inputCode = (EditTextToDel) findViewById(R.id.et_inputCode);
        bt_sure = (Button) findViewById(R.id.bt_sure);

        tv_sendDate.setText("送箱日期:" + sendDate);
        tv_sendNet.setText("送箱地点:" + netInfoData.getNet_name());

        listCashbox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(netInfoData.getId(), cashboxType);
        adapter = new SendBoxAdapter(this, listCashbox, true);
        asvlv_cashbox.setAdapter(adapter);

        tv_boxSum.setText("已扫描" + listCashbox.size() + "个");
    }

    public void initListener() {
        asvlv_cashbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("position", position + "");
                int pos = CashboxScannedUtil.scannedSuccess(ScanboxActivity.this, listCashbox, listCashbox.get(position).getBox_code());
                if (pos != -1) {
                    listCashbox.get(pos).setLocalStatus(LocalStatusConstant.DONE);
                    adapter.notifyDataSetChanged();
                }
                LogUtil.e("pos", pos + "");
            }
        });

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcodeStr = et_inputCode.getText().toString().trim();
                if (barcodeStr == null || barcodeStr.equals("")) {
                    ToastUtil.shortShow("请先输入钞箱条码");
                } else {
                    RecordboxInfoData infoData = CashboxScannedUtil.receivedOrMiddle(ScanboxActivity.this, listCashbox, netInfoData, sendDate, cashboxType, barcodeStr);
                    if (infoData.getBox_code() != null) {
                        listCashbox.add(infoData);
                        adapter.notifyDataSetChanged();
                        tv_boxSum.setText("已扫描" + listCashbox.size() + "个");
                    }

                }
            }
        });

        btnTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //ActivityJumpUtil.jumpToTaskDetailActivity(ScanboxActivity.this, taskInfoData);
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
    }


}

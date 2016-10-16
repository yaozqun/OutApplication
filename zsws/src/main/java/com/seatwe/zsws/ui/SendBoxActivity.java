package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.util.log.LogUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.SendBoxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.CashboxScannedUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

public class SendBoxActivity extends BaseActivity {
    private AdaptScrViewListView asvlv_cashbox;

    private SendBoxAdapter adapter;

    private List<RecordboxInfoData> listCashbox;

    private TaskInfoData taskInfoData;

    private TextView tv_boxSum;

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


        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        listCashbox = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetIdAndType(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND);

        tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");

        adapter = new SendBoxAdapter(this, listCashbox,true);
        asvlv_cashbox.setAdapter(adapter);

        asvlv_cashbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("position", position + "");
                int pos = CashboxScannedUtil.scannedSuccess(SendBoxActivity.this, listCashbox, listCashbox.get(position).getBox_code());
                if (pos != -1) {
                    listCashbox.get(pos).setLocalStatus(LocalStatusConstant.DONE);
                    adapter.notifyDataSetChanged();
                    tv_boxSum.setText("计划" + listCashbox.size() + "个,扫描" + RecordBoxBusinessUtil.getInstance().queryByNetIdAndTypeAndLocalStatus(taskInfoData.getNet_id(), CashboxTypeConstant.TYPE_SEND, LocalStatusConstant.DONE).size() + "个");
                }
                LogUtil.e("pos", pos + "");
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
    }

}

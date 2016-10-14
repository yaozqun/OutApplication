package com.seatwe.zsws.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.util.DateTimeUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.adapter.ScanboxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.Date;
import java.util.List;

public class ScanboxActivity extends BaseActivity {
    private AdaptScrViewListView asvlv_cashbox;

    private ScanboxAdapter adapter;

    private List<CashBoxData> listCashbox;

    private TaskInfoData taskInfoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanbox);
        initView();
    }

    public void initView() {
        //获取任务信息
        taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);

        asvlv_cashbox = (AdaptScrViewListView) findViewById(R.id.asvlv_box);
        TaskInfoData taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        listCashbox = CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByNetId(taskInfoData.getNet_id());
        adapter = new ScanboxAdapter(this, listCashbox);
        asvlv_cashbox.setAdapter(adapter);

        asvlv_cashbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        for (CashBoxData data : listCashbox) {
            if (barcodeStr.equals(data.getCashbox_num())) {
                RecordboxInfoData info = new RecordboxInfoData();
                info.setBox_code(data.getCashbox_num());
                info.setId(data.getId());
                info.setNet_id(data.getNet_id());
                info.setTask_id(taskInfoData.getId());
                //交接网点  是保存网点id？还是网点名称？
                info.setTransfer_net(NetInfoBusinessUtil.getInstance().queryNetInfoById(data.getNet_id()).getNet_name());
                info.setTransfer_time(DateTimeUtil.formatTime(new Date()));
                RecordBoxBusinessUtil.getInstance().createOrUpdate(info);//将已扫描的钞箱保存进数据库
            }
        }
    }
}

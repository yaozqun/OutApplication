package com.seatwe.zsws.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.adapter.ScanboxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;

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

    public void initView(){
        asvlv_cashbox = (AdaptScrViewListView) findViewById(R.id.asvlv_box);
        TaskInfoData taskInfoData = (TaskInfoData) getIntent().getSerializableExtra(ActivityJumpUtil.INFO);
        listCashbox = CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByNetId(taskInfoData.getNet_id());
        adapter = new ScanboxAdapter(this,listCashbox);
        asvlv_cashbox.setAdapter(adapter);
    }
}

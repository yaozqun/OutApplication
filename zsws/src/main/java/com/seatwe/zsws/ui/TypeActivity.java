package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.SendBoxAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.List;

public class TypeActivity extends BaseActivity {
    private TextView tv_boxSum;
    private AdaptScrViewListView asvlv_box;
    private SendBoxAdapter adapter;
    private List<RecordboxInfoData> listBox;

    private String cashboxType;

    public TypeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initView();
    }

    public void initView() {

        cashboxType = getIntent().getStringExtra(ActivityJumpUtil.CASHBOX_TYPE);
        if (cashboxType.equals(CashboxTypeConstant.TYPE_SEND)) {
            tvTitleSubject.setText(getResources().getString(R.string.send_box));
        } else if (cashboxType.equals(CashboxTypeConstant.TYPE_RECEIVE)) {
            tvTitleSubject.setText(getResources().getString(R.string.collect_box));
        } else {
            tvTitleSubject.setText(getResources().getString(R.string.middle_box));
        }
        tv_boxSum = (TextView) findViewById(R.id.tv_boxSum);
        asvlv_box = (AdaptScrViewListView) findViewById(R.id.asvlv_box);

        listBox = RecordBoxBusinessUtil.getInstance().queryByCashboxType(cashboxType);
        adapter = new SendBoxAdapter(this, listBox, false);
        asvlv_box.setAdapter(adapter);

        if (cashboxType.equals(CashboxTypeConstant.TYPE_SEND)) {
            int planToSend = RecordBoxBusinessUtil.getInstance().queryByCashboxType(cashboxType).size();
            int unSend = RecordBoxBusinessUtil.getInstance().queryByCashboxTypeAndLocalStatus(cashboxType, LocalStatusConstant.UN_DONE).size();
            int aleadySend = planToSend - unSend;
            tv_boxSum.setText("合计:计划送出" + planToSend + "个,未送" + unSend + "个,已送" + aleadySend + "个");
        } else {
            int sum = RecordBoxBusinessUtil.getInstance().queryByCashboxType(cashboxType).size();
            tv_boxSum.setText("合计:" + sum + "个");
        }

    }
}

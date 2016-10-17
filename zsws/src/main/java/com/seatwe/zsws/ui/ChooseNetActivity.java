package com.seatwe.zsws.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.util.DateTimeUtil;
import com.grgbanking.baselib.util.DateUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.ui.adapter.ChooseNetAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ChooseNetActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_sendDate;
    private ImageView iv_chooseDate;
    private AdaptScrViewListView asvlv_net;
    private List<NetInfoData> listNet;
    private ChooseNetAdapter adapter;
    private String chooseDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_net);
        init();
    }

    public void initView() {
        chooseDate = DateTimeUtil.formatDate(new Date());//默认是今天
        tvTitleSubject.setText(getResources().getString(R.string.choose_net));
        btnTitleRight.setText(getResources().getString(R.string.sure));
        tv_sendDate = (TextView) findViewById(R.id.tv_sendDate);
        iv_chooseDate = (ImageView) findViewById(R.id.iv_chooseDate);
        asvlv_net = (AdaptScrViewListView) findViewById(R.id.asvlv_net);

        iv_chooseDate.setOnClickListener(this);
        tv_sendDate.setText(chooseDate);


        listNet = NetInfoBusinessUtil.getInstance().queryAllNetInfo();
        adapter = new ChooseNetAdapter(this, listNet);
        asvlv_net.setAdapter(adapter);

    }

    public void initListener() {
        btnTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getPos() != -1) {
                    ActivityJumpUtil.jumpToScanboxActivity(ChooseNetActivity.this, listNet.get(adapter.getPos()), DateUtil.formatTime(new Date()), CashboxTypeConstant.TYPE_MIDDLE);
                    finish();
                } else {
                    ToastUtil.shortShow("请先选择网点");
                }
            }
        });
    }

    public void init() {
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_chooseDate:
//                new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,null).show();

                Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
                DatePickerDialog dateDlg = new DatePickerDialog(this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                DatePicker picker = dateDlg.getDatePicker();
//                picker.set
                picker.setMinDate(dateAndTime.getTimeInMillis());
                dateDlg.show();
                break;
        }
    }

    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            Calendar dateAndTime = Calendar.getInstance();
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            chooseDate = DateTimeUtil.formatDate(dateAndTime.getTime());
//            upDateDate(dateAndTime);

        }
    };

}

package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.DateTimeUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.constant.CashboxTypeConstant;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.TaskAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.ui.base.ScanBaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.BarcodeScannedUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;

import java.util.Date;
import java.util.List;

public class TaskActivity extends ScanBaseActivity {
    private AdaptScrViewListView asvlv_task;

    private TaskAdapter adapter;

    private List<TaskInfoData> listTask;

    private EditTextToDel et_inputCode;

    private Button bt_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.module_task));
        btnTitleRight.setText(getResources().getString(R.string.upload));
        asvlv_task = (AdaptScrViewListView) findViewById(R.id.asvlv_task);

        et_inputCode = (EditTextToDel) findViewById(R.id.et_inputCode);
        bt_sure = (Button) findViewById(R.id.bt_sure);

        listTask = removeDuplicate(TaskInfoBusinessUtil.getInstance().queryAllTaskInfo());
        adapter = new TaskAdapter(this, listTask);
        asvlv_task.setAdapter(adapter);

        asvlv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ActivityJumpUtil.jumpToTaskDetailActivity(TaskActivity.this, listTask.get(position));
            }
        });

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcodeStr = et_inputCode.getText().toString().trim();
                if (barcodeStr == null || barcodeStr.equals("")) {
                    ToastUtil.shortShow("请先输入钞箱条码");
                } else {
                    scan(barcodeStr);
                }
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        if (barcodeStr == null || barcodeStr.equals("")) {
            scan(barcodeStr);
        }
    }

    /**
     * 过滤网点ID相同的任务
     *
     * @param list
     */
    public List<TaskInfoData> removeDuplicate(List<TaskInfoData> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getNet_id() == list.get(i).getNet_id()) {
                    list.remove(j);
                }
            }
        }

        //查询每个任务(网点)下的朝向数量
        for (int k = 0; k < list.size(); k++) {
            list.get(k).setCashbox_num_count(TaskInfoBusinessUtil.getInstance().queryTaskInfoByNetId(list.get(k).getNet_id()));
        }
        return list;
    }

    public void scan(String barcodeStr){
        int pos = BarcodeScannedUtil.scannedTaskSuccess(this, listTask, barcodeStr);
        if (pos != -1) {
            listTask.get(pos).setLocalStatus(LocalStatusConstant.DONE);
            adapter.notifyDataSetChanged();
            ActivityJumpUtil.jumpToTaskDetailActivity(this, listTask.get(pos));
        }
    }
}

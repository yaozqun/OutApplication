package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.util.log.LogUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.adapter.TaskAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.BusinessUtil;

import java.util.List;

public class TaskActivity extends BaseActivity {
    private AdaptScrViewListView asvlv_task;

    private TaskAdapter adapter;

    private List<TaskInfoData> listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.module_task));
        asvlv_task = (AdaptScrViewListView) findViewById(R.id.asvlv_task);

        listTask = BusinessUtil.getInstance().queryTaskInfo();
        adapter = new TaskAdapter(this, listTask);
        asvlv_task.setAdapter(adapter);

        asvlv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityJumpUtil.jumpToTaskDetailActivity(TaskActivity.this, listTask.get(position));
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        for(TaskInfoData taskInfo : listTask){
            if(BusinessUtil.getInstance().queryNetInfoById(taskInfo.getNet_id()).getNet_code().equals(barcodeStr)){
                ActivityJumpUtil.jumpToTaskDetailActivity(TaskActivity.this,taskInfo);
            }
        }
    }
}

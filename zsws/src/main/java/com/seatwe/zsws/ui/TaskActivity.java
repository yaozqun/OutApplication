package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.util.DateTimeUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.ui.adapter.TaskAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.ActivityJumpUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;

import java.util.Date;
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

        listTask = removeDuplicate(TaskInfoBusinessUtil.getInstance().queryAllTaskInfo());
        adapter = new TaskAdapter(this, listTask);
        asvlv_task.setAdapter(adapter);

        asvlv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listTask.get(position).setArriveTime(DateTimeUtil.formatTime(new Date()));
                TaskInfoBusinessUtil.getInstance().createOrUpdate(listTask.get(position));//将到达时间保存至数据库
                ActivityJumpUtil.jumpToTaskDetailActivity(TaskActivity.this, listTask.get(position));
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        for (TaskInfoData taskInfo : listTask) {
            if (NetInfoBusinessUtil.getInstance().queryNetInfoById(taskInfo.getNet_id()).getNet_code().equals(barcodeStr)) {
                ActivityJumpUtil.jumpToTaskDetailActivity(TaskActivity.this, taskInfo);
            }
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
}

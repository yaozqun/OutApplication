package com.seatwe.zsws.bean.resp;

import com.seatwe.zsws.bean.TaskInfoData;

import java.util.List;

/**
 * Created by charry on 2016/10/8.
 */
public class TaskInfoRespBean {
    private List<TaskInfoData> data;

    public List<TaskInfoData> getData() {
        return data;
    }

    public void setData(List<TaskInfoData> data) {
        this.data = data;
    }
}

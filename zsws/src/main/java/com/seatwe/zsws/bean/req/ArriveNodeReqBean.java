package com.seatwe.zsws.bean.req;

/**
 * Created by charry on 2016/10/9.
 */
public class ArriveNodeReqBean {
    private String line_id;
    private String node_type;
    private String arrive_time;

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }
}

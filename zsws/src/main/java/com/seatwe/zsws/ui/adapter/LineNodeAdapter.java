package com.seatwe.zsws.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.constant.LocalStatusConstant;

import java.util.List;

public class LineNodeAdapter extends BaseAdapter {
    private Activity context;

    private List<ArriveNodeReqBean> info;

    public LineNodeAdapter(Activity context, List<ArriveNodeReqBean> info) {
        super();
        this.context = context;
        this.info = info;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        int count = 0;
        if (info != null) {
            count = info.size();
        }
        return count;
    }

    @Override
    public ArriveNodeReqBean getItem(int position) {
        // TODO Auto-generated method stub
        ArriveNodeReqBean item = null;
        if (info != null) {
            item = info.get(position);
        }
        return item;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_line_node_item, null);
            viewHolder.tv_lineName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.iv_flag = (ImageView) convertView.findViewById(R.id.iv_flag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ArriveNodeReqBean item = getItem(position);
        if (null != item) {
            viewHolder.tv_lineName.setText(item.getNode_name());
            viewHolder.tv_status.setText(item.getLocalStatus()+"");
            if (item.getLocalStatus()== LocalStatusConstant.DONE) {
                viewHolder.iv_flag.setVisibility(View.VISIBLE);
            }else{
                viewHolder.iv_flag.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_lineName;// 任务名称

        TextView tv_status;// 设备编号

        ImageView iv_flag;// 是否已扫描

    }

}

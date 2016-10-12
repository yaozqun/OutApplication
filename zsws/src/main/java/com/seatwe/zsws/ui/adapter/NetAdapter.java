package com.seatwe.zsws.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.util.BusinessUtil;

import java.util.List;

public class NetAdapter extends BaseAdapter {
    private Activity context;

    private List<TaskInfoData> info;

    public NetAdapter(Activity context, List<TaskInfoData> info) {
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
    public TaskInfoData getItem(int position) {
        // TODO Auto-generated method stub
        TaskInfoData item = null;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_net_item, null);
            viewHolder.tv_netName = (TextView) convertView.findViewById(R.id.tv_netName);
            viewHolder.tv_contact = (TextView) convertView.findViewById(R.id.tv_contact);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tv_planSendBox = (TextView) convertView.findViewById(R.id.tv_planSendBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TaskInfoData item = getItem(position);
        if (null != item) {
            viewHolder.tv_netName.setText((position + 1) + BusinessUtil.getInstance().q);
//            viewHolder.tv_contact.setText("联系人：" + item.getTel_number());
//            viewHolder.tv_address.setText("地址：" + item.getNet_address());
//            viewHolder.tv_planSendBox.setText("计划送箱：" + item.get);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_netName;

        TextView tv_contact;

        TextView tv_address;

        TextView tv_planSendBox;
    }

}

package com.seatwe.zsws.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;

import java.util.List;

public class ScanboxAdapter extends BaseAdapter {
    private Activity context;

    private List<CashBoxData> info;

    public ScanboxAdapter(Activity context, List<CashBoxData> info) {
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
    public CashBoxData getItem(int position) {
        // TODO Auto-generated method stub
        CashBoxData item = null;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_scanbox_item, null);
            viewHolder.tv_netName = (TextView) convertView.findViewById(R.id.tv_netName);
            viewHolder.tv_cashboxCode = (TextView) convertView.findViewById(R.id.tv_cashboxCode);
            viewHolder.tv_cashboxType = (TextView) convertView.findViewById(R.id.tv_cashboxType);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CashBoxData item = getItem(position);
        if (null != item) {
            NetInfoData netInfo = NetInfoBusinessUtil.getInstance().queryNetInfoById(item.getNet_id());
            viewHolder.tv_netName.setText("所属机构：" + netInfo.getNet_name());
            viewHolder.tv_cashboxCode.setText("款箱编号：" + item.getCashbox_num());
            viewHolder.tv_cashboxType.setText("款箱类型：" + item.getBox_type_name());
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_netName;

        TextView tv_cashboxCode;

        TextView tv_cashboxType;

    }

}

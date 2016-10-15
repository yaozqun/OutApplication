package com.seatwe.zsws.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;

import java.util.List;

public class ChooseNetAdapter extends BaseAdapter {
    private Activity context;

    private List<NetInfoData> info;

    private int pos = -1;

    public int getPos() {
        return pos;
    }

    public ChooseNetAdapter(Activity context, List<NetInfoData> info) {
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
    public NetInfoData getItem(int position) {
        // TODO Auto-generated method stub
        NetInfoData item = null;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_choose_net_item, null);
            viewHolder.tv_netName = (TextView) convertView.findViewById(R.id.tv_netName);
            viewHolder.tv_contact = (TextView) convertView.findViewById(R.id.tv_contact);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.ib_choose = (ImageButton) convertView.findViewById(R.id.ib_choose);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NetInfoData item = getItem(position);
        if (null != item) {
            viewHolder.tv_netName.setText(item.getNet_name());
            viewHolder.tv_contact.setText("联系人：" + item.getContacts_name() + ":" + item.getTel_number());
            viewHolder.tv_address.setText("地址：" + item.getNet_address());

            if(pos == position){
                viewHolder.ib_choose.setBackgroundResource(R.mipmap.flag_true);
            }else{
                viewHolder.ib_choose.setBackgroundResource(R.mipmap.del);
            }

            viewHolder.ib_choose.setOnClickListener(new MyOnClickListenr(position));
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_netName;

        TextView tv_contact;

        TextView tv_address;

        ImageButton ib_choose;

    }

    public class MyOnClickListenr implements View.OnClickListener{
        private int position;

        public MyOnClickListenr(int position){
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            pos = position;
            notifyDataSetChanged();
        }
    }

}

package com.seatwe.zsws.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grgbanking.baselib.web.bean.NetInfoData;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.util.db.CashboxBaseBusinessUtil;
import com.seatwe.zsws.util.db.NetInfoBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;

import java.util.Iterator;
import java.util.List;

public class SendBoxAdapter extends BaseAdapter {
    private Activity context;

    private List<RecordboxInfoData> info;

    private boolean showFlag;

    public SendBoxAdapter(Activity context, List<RecordboxInfoData> info, boolean showFlag) {
        super();
        this.context = context;
        this.info = info;
        this.showFlag = showFlag;
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
    public RecordboxInfoData getItem(int position) {
        // TODO Auto-generated method stub
        RecordboxInfoData item = null;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_sendbox_item, null);
            viewHolder.tv_netName = (TextView) convertView.findViewById(R.id.tv_netName);
            viewHolder.tv_cashboxCode = (TextView) convertView.findViewById(R.id.tv_cashboxCode);
            viewHolder.tv_cashboxType = (TextView) convertView.findViewById(R.id.tv_cashboxType);
            viewHolder.iv_flagTrue = (ImageView) convertView.findViewById(R.id.iv_flagTrue);
            viewHolder.iv_flagFalse = (ImageView) convertView.findViewById(R.id.iv_flagFalse);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RecordboxInfoData item = getItem(position);
        if (null != item) {
            NetInfoData netInfo = NetInfoBusinessUtil.getInstance().queryNetInfoById(item.getTransfer_net());
            viewHolder.tv_netName.setText("所属网点：" + netInfo.getNet_name());
            viewHolder.tv_cashboxCode.setText("款箱编号：" + item.getBox_code());

            viewHolder.tv_cashboxType.setText("款箱类型：" + CashboxBaseBusinessUtil.getInstance().queryCashboxInfoByCode(item.getBox_code()).getBox_type_name());
            if (showFlag) {
                if (item.getLocalStatus() == LocalStatusConstant.DONE) {
                    viewHolder.iv_flagTrue.setVisibility(View.VISIBLE);
                    viewHolder.iv_flagTrue.setBackgroundResource(R.mipmap.flag_true);
                    viewHolder.iv_flagFalse.setOnClickListener(new MyClickListener(position));
                } else {
                    viewHolder.iv_flagTrue.setVisibility(View.GONE);
                }
            } else {
                viewHolder.iv_flagTrue.setVisibility(View.GONE);
                viewHolder.iv_flagFalse.setVisibility(View.GONE);
            }


        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_netName;

        TextView tv_cashboxCode;

        TextView tv_cashboxType;

        ImageView iv_flagTrue;

        ImageView iv_flagFalse;

    }

    public class MyClickListener implements View.OnClickListener {
        private int position;

        public MyClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            RecordBoxBusinessUtil.getInstance().deleteRecordBoxInfo(info.get(position));
            info.remove(position);
            notifyDataSetChanged();
        }
    }
}

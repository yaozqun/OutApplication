package com.seatwe.zsws.ui;

import android.os.Bundle;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.ui.adapter.LineNodeAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.BusinessUtil;

import java.util.List;

public class LineNodeActivity extends BaseActivity {
    private AdaptScrViewListView asvlv_node;
    private LineNodeAdapter adapter;

    private List<ArriveNodeReqBean> listNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_node);
        initView();
    }

    public void initView(){
        tvTitleSubject.setText(getResources().getString(R.string.module_line));
        asvlv_node = (AdaptScrViewListView) findViewById(R.id.asvlv_node);

        listNode = BusinessUtil.getInstance().queryLineNodeInfo();
        adapter = new LineNodeAdapter(this,listNode);
        asvlv_node.setAdapter(adapter);
    }
}

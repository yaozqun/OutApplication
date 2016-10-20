package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.LineNodeAdapter;
import com.seatwe.zsws.ui.base.ScanBaseActivity;
import com.seatwe.zsws.util.BarcodeScannedUtil;
import com.seatwe.zsws.util.UploadUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;

import java.util.List;

public class LineNodeActivity extends ScanBaseActivity {
    private AdaptScrViewListView asvlv_node;
    private LineNodeAdapter adapter;

    private List<ArriveNodeReqBean> listNode;

    private EditTextToDel et_inputCode;

    private Button bt_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_node);
        initView();
    }

    public void initView() {
        tvTitleSubject.setText(getResources().getString(R.string.module_line));
        asvlv_node = (AdaptScrViewListView) findViewById(R.id.asvlv_node);

        et_inputCode = (EditTextToDel) findViewById(R.id.et_inputCode);
        bt_sure = (Button) findViewById(R.id.bt_sure);

        listNode = LineNodeBusinessUtil.getInstance().queryAllLineNodeInfo();
        adapter = new LineNodeAdapter(this, listNode);
        asvlv_node.setAdapter(adapter);

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcodeStr = et_inputCode.getText().toString().trim();
                if (barcodeStr == null || barcodeStr.equals("")) {
                    ToastUtil.shortShow("请先输入钞箱条码");
                } else {
                    scan(barcodeStr);
                }
            }
        });
    }

    @Override
    public void onScanResult(String barcodeStr) {
        super.onScanResult(barcodeStr);
        LogUtil.e("条形码Linemnode  ：", barcodeStr);
        scan(barcodeStr);
    }

    public void scan(String barcodeStr) {
        int pos = BarcodeScannedUtil.scannedNodeSuccess(this, listNode, barcodeStr);
        if (pos != -1) {
            listNode.get(pos).setLocalStatus(LocalStatusConstant.DONE);
            adapter.notifyDataSetChanged();

            //上传
            UploadUtil.uploadLineNode(this, listNode.get(pos));
        }
    }

}

package com.seatwe.zsws.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.ui.view.AdaptScrViewListView;
import com.grgbanking.baselib.ui.view.EditTextToDel;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.grgbanking.baselib.web.response.ResponseRoot;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.ui.adapter.LineNodeAdapter;
import com.seatwe.zsws.ui.base.ScanBaseActivity;
import com.seatwe.zsws.util.BarcodeScannedUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.web.NetService;

import java.util.List;

import okhttp3.Call;

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
            //上传
            uploadNode(pos);
        }
    }

    public void uploadNode(final int position) {
        final ArriveNodeReqBean bean = listNode.get(position);
        final ArriveNodeReqBean req = new ArriveNodeReqBean();
        req.setArrive_time(bean.getArrive_time());
        req.setLine_id(bean.getLine_id());
        req.setNode_type(bean.getNode_type());

        NetService.getInstance().arriveNode(req, new ResultCallback<ResponseRoot>() {
            @Override
            public void onSuccess(ResponseRoot resp) {
                ToastUtil.shortShow(getResources().getString(R.string.scan_success));
                bean.setLocalStatus(LocalStatusConstant.UPLOADED);
                LineNodeBusinessUtil.getInstance().createOrUpdate(bean);

                listNode.get(position).setLocalStatus(LocalStatusConstant.DONE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(ErrorMsg errorMsg) {
                ToastUtil.shortShow(errorMsg.getMessage());
            }

            @Override
            public void onPre(Call call) {

            }
        });
    }
}

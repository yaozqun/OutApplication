package com.seatwe.zsws.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.util.ProgressUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.web.bean.ResponseBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;
import com.seatwe.zsws.R;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.RecordboxInfoData;
import com.seatwe.zsws.bean.TaskInfoData;
import com.seatwe.zsws.bean.req.ArriveNodeReqBean;
import com.seatwe.zsws.bean.req.RecordReqBean;
import com.seatwe.zsws.constant.LocalStatusConstant;
import com.seatwe.zsws.util.db.LineInfoBusinessUtil;
import com.seatwe.zsws.util.db.LineNodeBusinessUtil;
import com.seatwe.zsws.util.db.RecordBoxBusinessUtil;
import com.seatwe.zsws.util.db.TaskInfoBusinessUtil;
import com.seatwe.zsws.web.NetService;

import java.util.List;

import okhttp3.Call;

public class UploadUtil {

    /**
     * 上传指定网点下的操作记录
     *
     * @param taskInfoData
     */
    public static void uploadRecord(Context context, final TaskInfoData taskInfoData) {
        final List<RecordboxInfoData> listUpload = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetId(taskInfoData.getNet_id());
        if (listUpload.size() > 0) {
            final ProgressDialog progressDialog = ProgressUtil.show(context, context.getResources().getString(R.string.uploading));
            LineInfoData lineInfoData = LineInfoBusinessUtil.getInstance().queryAllLineInfo();
            //上传参数
            RecordReqBean req = new RecordReqBean();
            req.setTransfer_type(taskInfoData.getTask_type());
            req.setLine_id(taskInfoData.getLine_id() + "");
            req.setCar_number(lineInfoData.getCar_number());
            req.setCounterman1(lineInfoData.getCounterman1());
            req.setCounterman2(lineInfoData.getCounterman2());
            req.setNote(lineInfoData.getLine_notes());
            req.setTransfer_net(taskInfoData.getNet_id() + "");
            req.setTransfer_time(listUpload.get(0).getTransfer_time());
            req.setBankman_name1("");
            req.setBoxes(listUpload);

            NetService.getInstance().uploadRecord(req, new ResultCallback<ResponseBean>() {
                @Override
                public void onSuccess(ResponseBean resp) {
                    ToastUtil.shortShow("上传成功");
                    progressDialog.dismiss();
                    //上传成功后修改本地状态为已上传
                    taskInfoData.setLocalStatus(LocalStatusConstant.UPLOADED);
                    TaskInfoBusinessUtil.getInstance().createOrUpdate(taskInfoData);

                    for (RecordboxInfoData recordboxInfoData : listUpload) {
                        recordboxInfoData.setLocalStatus(LocalStatusConstant.UN_DONE);
                        RecordBoxBusinessUtil.getInstance().createOrUpdate(recordboxInfoData);
                    }

                }

                @Override
                public void onError(ErrorMsg errorMsg) {
                    progressDialog.dismiss();
                }

                @Override
                public void onPre(Call call) {
                    progressDialog.dismiss();
                }
            });
        } else {
            ToastUtil.shortShow("当前没有可上传的任务");
        }

    }


    /**
     * 将所有未上传的操作记录循环上传
     */
    public static void uploadAllRecord(Context context) {

        List<TaskInfoData> listTaskInfo = TaskInfoBusinessUtil.getInstance().queryTaskInfoByLocalStatus(LocalStatusConstant.DONE);
        if (listTaskInfo.size() > 0) {
            for (final TaskInfoData taskInfoData : listTaskInfo) {
                final List<RecordboxInfoData> listUpload = RecordBoxBusinessUtil.getInstance().queryRecordBoxByNetId(taskInfoData.getNet_id());
                if (listUpload.size() > 0) {
                    final ProgressDialog progressDialog = ProgressUtil.show(context, context.getResources().getString(R.string.uploading));

                    LineInfoData lineInfoData = LineInfoBusinessUtil.getInstance().queryAllLineInfo();

                    RecordReqBean req = new RecordReqBean();
                    req.setTransfer_type(taskInfoData.getTask_type());
                    req.setLine_id(taskInfoData.getLine_id() + "");
                    req.setCar_number(lineInfoData.getCar_number());
                    req.setCounterman1(lineInfoData.getCounterman1());
                    req.setCounterman2(lineInfoData.getCounterman2());
                    req.setNote(lineInfoData.getLine_notes());
                    req.setTransfer_net(taskInfoData.getNet_id() + "");
                    req.setTransfer_time(listUpload.get(0).getTransfer_time());
                    req.setBankman_name1("");
                    req.setBoxes(listUpload);

                    NetService.getInstance().uploadRecord(req, new ResultCallback<ResponseBean>() {
                        @Override
                        public void onSuccess(ResponseBean resp) {
                            progressDialog.dismiss();
                            //上传成功后修改本地状态为已上传
                            taskInfoData.setLocalStatus(LocalStatusConstant.UPLOADED);
                            TaskInfoBusinessUtil.getInstance().createOrUpdate(taskInfoData);

                            for (RecordboxInfoData recordboxInfoData : listUpload) {
                                recordboxInfoData.setLocalStatus(LocalStatusConstant.UN_DONE);
                                RecordBoxBusinessUtil.getInstance().createOrUpdate(recordboxInfoData);
                            }
                        }

                        @Override
                        public void onError(ErrorMsg errorMsg) {
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onPre(Call call) {
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    ToastUtil.shortShow("当前没有可上传的任务");
                }

            }
        } else {
            ToastUtil.shortShow("当前没有可上传的任务");
        }
    }

    /**
     * 上传线路节点信息
     *
     * @param context
     * @param bean
     */
    public static void uploadLineNode(final Context context, final ArriveNodeReqBean bean) {
        ArriveNodeReqBean req = new ArriveNodeReqBean();
        req.setArrive_time(bean.getArrive_time());
        req.setLine_id(bean.getLine_id());
        req.setNode_type(bean.getNode_type());

        NetService.getInstance().arriveNode(req, new ResultCallback<ResponseBean>() {
            @Override
            public void onSuccess(ResponseBean resp) {
                ToastUtil.shortShow(context.getResources().getString(R.string.scan_success));
                bean.setLocalStatus(LocalStatusConstant.UPLOADED);
                LineNodeBusinessUtil.getInstance().createOrUpdate(bean);
            }

            @Override
            public void onError(ErrorMsg errorMsg) {

            }

            @Override
            public void onPre(Call call) {

            }
        });
    }

}

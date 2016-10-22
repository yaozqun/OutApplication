package com.grgbanking.baselib.util;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;

import com.grgbanking.baselib.web.bean.UpgradeData;
import com.grgbanking.baselib.config.AppConfig;
import com.grgbanking.baselib.config.WebConfig;
import com.grgbanking.baselib.core.SystemService;
import com.grgbanking.baselib.core.callback.ResultCallback;
import com.grgbanking.baselib.receiver.DownloadCompleteReceiver;
import com.grgbanking.baselib.ui.view.dialog.CustomDialog;
import com.grgbanking.baselib.ui.view.loading.ShapeLoadingDialog;
import com.grgbanking.baselib.web.bean.req.UpgradeReqBean;
import com.grgbanking.baselib.web.entity.ErrorMsg;

import java.io.File;
import java.util.List;

import okhttp3.Call;

/**
 * 版本更新工具类
 */
public class UpgradeUtil {

    private static CustomDialog customDialog;
    private static ShapeLoadingDialog loadingDialog;
    private static String url;
    private static DownloadCompleteReceiver downloadCompleteReceiver;
    private static Context context;

    private static boolean isForcedToUpdate = false;//是否强制更新

    private static boolean installApk = true;

    private static final int PERSENT_TOTAL = 100;
    private static final int SLEEP_TIME = 500;

    /**
     * 初始化
     *
     * @param cxt         上下文
     * @param requestUrl  版本二更新接口请求地址
     * @param req         请求参数
     * @param title       下载标题
     * @param description 下载描述
     */
    public static void init(Context cxt, String requestUrl, UpgradeReqBean req, final String title, final String description) {
        context = cxt;
        initView(title, description);
        checkVersionUpdate(requestUrl, req);
    }

    /**
     * 初始化
     *
     * @param cxt         上下文
     * @param req         请求参数
     * @param title       下载标题
     * @param description 下载描述
     */
    public static void init(Context cxt, UpgradeReqBean req, String packageName, final String title, final String description) {
        context = cxt;
        initView(title, description);
//        if (isNewApkAvilible(packageName,req.version)) {
//
//        }
        checkVersionUpdate(WebConfig.VERSION_UPGRADE, req);
    }

    /**
     * 初始化
     *
     * @param cxt         上下文
     * @param req         请求参数
     * @param title       下载标题
     * @param description 下载描述
     */
    public static void init(Context cxt, UpgradeReqBean req, final String title, final String description) {
        context = cxt;
        initView(title, description);
        checkVersionUpdate(WebConfig.VERSION_UPGRADE, req);
    }


    public static void initView(final String title, final String description) {
        customDialog = DialogUtil.getDialog(context);
        customDialog.setCancelable(false);//不能手动取消

        loadingDialog = new ShapeLoadingDialog(context);
        loadingDialog.setCancelable(false);
        loadingDialog.setButtonText("拼命下载中");
        loadingDialog.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        customDialog.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                showLoadingDialog("下载中...", true);
                DownloadUtil.downLoadFileByDownloadManager(url, title, description, new DownloadUtil.DownloadProgressListener() {
                    @Override
                    public void getProgress(int progress) {
                        loadingDialog.setProgress(progress);
                        if (progress == PERSENT_TOTAL) {
                            loadingDialog.dismiss();
                            if (installApk) {
                                installApk = false;
                                try {
                                    Thread.sleep(SLEEP_TIME);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                installApk(context, new File(AppConfig.APK_FILE_PATH));//安装aPk
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 检查更新
     */
    public static void checkVersionUpdate(final String requestUrl, final UpgradeReqBean req) {

        //检查更新
        SystemService.getInstance()
                .checkUpgrade(requestUrl, req, new ResultCallback<UpgradeData>() {

                    @Override
                    public void onSuccess(UpgradeData resp) {
                        String currentVersion = req.getVersion();
                        if (resp != null && resp.getVersion() != null && !"".equals(resp.getVersion())) {
                            if (currentVersion.compareTo(resp.getVersion()) < 0) {
                                url = resp.getUrl();
                                if (resp.getForced_update().equals("1")) {//强制更新
                                    customDialog.isCancelableOnTouchOutside(false);
                                    customDialog.setCancelable(false);
                                    customDialog.isCancelable(false);
                                    customDialog.withMessage("检测有新版本啦V" + resp.getVersion() + "，需要强制更新,更新内容如下:\n" + resp.getContent()).withButton1Text("更 新").withButton2Text(null).show();
                                } else {
                                    customDialog.withMessage("检测有新版本啦V" + resp.getVersion() + "，更新内容如下：\n" + resp.getContent()).withButton1Text("更 新")
                                            .withButton2Text("取 消").show();
                                }
                            } else {
                                ToastUtil.shortShow("您的已经是最新版本，不需要更新！");
                            }
                        }
                    }

                    @Override
                    public void onError(ErrorMsg errorMsg) {
                        ToastUtil.shortShow(errorMsg.message);
                    }

                    @Override
                    public void onPre(Call call) {

                    }
                });
    }

    //安装apk
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void registerDownloadReceiver() {
        downloadCompleteReceiver = new DownloadCompleteReceiver();
        //注册下载完成广播
        IntentFilter filter = new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(downloadCompleteReceiver, filter);
    }


    /**
     * 注销广播
     */
    public static void unregisterDownloadReceiver() {
        if (downloadCompleteReceiver != null) {
            context.unregisterReceiver(downloadCompleteReceiver);
        }
    }

    // 显示 loading 对话框
    private static void showLoadingDialog(String msg, boolean showProgressBar) {
        loadingDialog.setLoadingText(msg);
        loadingDialog.showProgressBar(showProgressBar);
        loadingDialog.setProgress(0);
        loadingDialog.showButton(showProgressBar);
        loadingDialog.show();
    }

    /**
     * 判断相对应的APP是否存在
     *
     * @param packageName(包名)(若想判断QQ，则改为com.tencent.mobileqq，若想判断微信，则改为com.tencent.mm)
     * @return
     */
    public static boolean isNewApkAvilible(String packageName, String currentVersion) {
        PackageManager packageManager = context.getPackageManager();

        //获取手机系统的所有APP包名，然后进行一一比较
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            //判断本地是否已经有版本号更高的包
            if (((PackageInfo) pinfo.get(i)).packageName.equalsIgnoreCase(packageName) && currentVersion.compareTo(((PackageInfo) pinfo.get(i)).versionName) < 0)
                return true;
        }
        return false;
    }

}
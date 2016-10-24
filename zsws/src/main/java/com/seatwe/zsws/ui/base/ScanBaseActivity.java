package com.seatwe.zsws.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.os.Vibrator;

import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.seatwe.zsws.R;

public class ScanBaseActivity extends BaseActivity {

    //扫描相关
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    //    private SoundPool soundpool = null;
    private int soundid;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action

    @Override
    protected void onResume() {
        super.onResume();
//        initScan();
    }

    //扫描成功广播
    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
//            soundpool.play(soundid, 1, 1, 0, 0, 1);

            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            android.util.Log.i("debug", "----codetype--" + temp);
            String barcodeStr = new String(barcode, 0, barcodelen);
            onScanResult(barcodeStr);
        }

    };

    //初始化扫描
    private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        if (mScanManager.openScanner()) {//如果开启成功
            mScanManager.openScanner();
            mScanManager.switchOutputMode(0);
//        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
//        soundid = soundpool.load("/etc/Scan_new.ogg", 1);

            IntentFilter filter = new IntentFilter();
            int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
            String[] value_buf = mScanManager.getParameterString(idbuf);
            if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
                filter.addAction(value_buf[0]);
            } else {
                filter.addAction(SCAN_ACTION);
            }

            registerReceiver(mScanReceiver, filter);
        }else{
            ToastUtil.shortShow(getResources().getString(R.string.not_support_scan));
        }
    }

    /**
     * 扫描返回结果
     *
     * @param barcodeStr
     */
    public void onScanResult(String barcodeStr) {
        //测试提交
        LogUtil.e("条形码BASE", barcodeStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销扫描广播
        if (mScanManager != null) {
            unregisterReceiver(mScanReceiver);
        }

    }
}

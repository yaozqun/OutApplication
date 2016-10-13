package com.grgbanking.baselib.scan.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;

import com.grgbanking.baselib.scan.IScanModel;
import com.grgbanking.baselib.scan.ODSListener;
import com.grgbanking.baselib.util.ToastUtil;

/**
 * Created by Administrator on 2016/10/13.
 */
public class ScanModel implements IScanModel {
    private static ScanModel model;
    private static Object lock = new Object();

    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private boolean isScanning = false;
    private ODSListener listener;

    public static ScanModel getInstance() {
        if (model == null) {
            synchronized (lock) {
                if (model == null) {
                    model = new ScanModel();
                }
            }
        }
        return model;
    }

    @Override
    public void init(Context context, ODSListener odsL, boolean isContinuous) {
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode(0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }
        context.registerReceiver(mScanReceiver, filter);
    }

    @Override
    public void startOrStop() {
        if (isScanning) {
            isScanning = false;
            mScanManager.closeScanner();
        } else {
            isScanning = true;
            mScanManager.startDecode();

        }

//        mScanManager.stopDecode();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        mScanManager.startDecode();
    }

    @Override
    public void pause() {
        mScanManager.stopDecode();
    }

    @Override
    public void goOn() {

    }

    @Override
    public void close() {
        mScanManager.stopDecode();
    }

    @Override
    public boolean isScaning() {
        return isScanning;
    }

    @Override
    public void setContinuous(boolean isContinuous) {

    }

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            soundpool.play(soundid, 1, 1, 0, 0, 1);
            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            android.util.Log.i("debug", "----codetype--" + temp);
            String barcodeStr = new String(barcode, 0, barcodelen);

            ToastUtil.shortShow(barcodeStr);


        }
    };

    private class ODSWorkThread extends Thread {
        public boolean run;

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (run) {
                if (isScanning) {
                    isScanning = false;
                    mScanManager.closeScanner();
                } else {
                    isScanning = true;
                    mScanManager.startDecode();

                }

            }

        }
    }
}

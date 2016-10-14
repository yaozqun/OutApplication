package com.seatwe.zsws.ui.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.util.ActivityManagerUtil;
import com.grgbanking.baselib.util.ToastUtil;
import com.grgbanking.baselib.util.log.LogUtil;
import com.seatwe.zsws.R;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BaseActivity extends Activity {

    protected boolean isShowTitle = true;

    protected Button btnTitleLeft;

    protected Button btnTitleRight;

    protected TextView tvTitleSubject;

    protected TextView tvTiltleUserName;

    private Vibrator mVibrator;
    private ScanManager mScanManager;
    //    private SoundPool soundpool = null;
    private int soundid;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action


    @Override
    public void setContentView(int layoutResID) {
        if (!isShowTitle || 0 == getTitleLayout()) {
            super.setContentView(layoutResID);
        } else {
            requestCustomTitle();
            super.setContentView(layoutResID);
            setCustomTitle(getTitleLayout());
            findViews();
        }
        initTitle();
    }

    protected final void requestCustomTitle() {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    }

    protected final void hideTitle() {
        isShowTitle = false;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected final void setCustomTitle(int layoutId) {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, layoutId);
    }

    protected int getTitleLayout() {
        return R.layout.comm_layout_title;
    }

    private final void findViews() {
        btnTitleRight = (Button) findViewById(R.id.btn_title_right);
        tvTitleSubject = (TextView) findViewById(R.id.tv_title_subject);
        btnTitleLeft = (Button) findViewById(R.id.btn_title_left);
        tvTiltleUserName = (TextView) findViewById(R.id.tv_title_userName);
        tvTiltleUserName.setVisibility(View.GONE);// 隐藏
        btnTitleLeft.setText("返回");
        btnTitleLeft.setOnClickListener(titleLeftListener);
        btnTitleRight.setOnClickListener(titleRightListener);
    }


    /**
     * 通过自定义标题，标题的布局不添加在内容区，通过 {@link #setCustomTitle(int)}
     */
    protected void initTitle() {
    }

    protected View.OnClickListener titleLeftListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();// 默认
        }
    };

    protected View.OnClickListener titleRightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagerUtil.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initScan();
        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }

        registerReceiver(mScanReceiver, filter);
    }

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
            LogUtil.e("条形码  ：", barcodeStr);
        }

    };

    private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode(0);
//        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
//        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    /**
     * 扫描返回结果
     *
     * @param barcodeStr
     */
    public void onScanResult(String barcodeStr) {



        //测试提交

    }
}

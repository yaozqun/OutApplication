package com.seatwe.zsws.ui.base;

import android.app.Activity;
import android.os.Bundle;

import com.seatwe.zsws.R;

public class ScanBaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_base);
    }


}

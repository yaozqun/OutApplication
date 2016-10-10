package com.seatwe.zsws.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.grgbanking.baselib.util.ActivityManagerUtil;
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
  }

}

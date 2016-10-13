package com.grgbanking.baselib.scan;

import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface IScanModel {
    /**
     *
     * @Title: open
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param context
     * @param odsL
     * @param isContinuous
     * @return
     */
    public void init(Context context, ODSListener odsL, boolean isContinuous);

    /**
     *
     * @Title: startOrStop
     * @Description: TODO(当ods在工作时，调用方法停止工作，当ods停止时，调用方法开始工作)
     */
    public void startOrStop();

    /**
     *
     * @Title: pause
     * @Description: TODO(ods暂停工作)
     */
    public void pause();

    /**
     *
     * @Title: goOn
     * @Description: TODO(ods继续工作)
     */
    public void goOn();

    /**
     *
     *
     * @Title: close
     * @Description: TODO(关闭ods模块)
     */
    public void close();

    /**
     * @Title: isScaning
     * @Description: TODO(获取扫描状态)
     * @return
     */
    public boolean isScaning();

    /**
     * @Title: setContinuous
     * @Description: TODO(设置是否连续扫描)
     * @param isContinuous
     */
    public void setContinuous(boolean isContinuous);
}

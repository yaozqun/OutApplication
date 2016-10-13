package com.grgbanking.baselib.scan;

public interface ODSListener {
    /**
     * 成功扫描到东西后返回数据
     * @param barcodeStr
     * @param codeType
     * @return
     */
    public boolean onODSResult(String barcodeStr,byte codeType);

    /**
     * 开始扫描的时候会调用
     * @Title: onStartScan
     * @Description: TODO(扫描结束的时候会调用)
     */
    public void onStartScan();

    /**
     * 
     * 扫描结束的时候会调用
     * @Title: onStopScan
     * @Description: TODO(扫描结束的时候会调用)
     */
    public void onStopScan();

    /**
     * 扫描过程出错了会调用
     * @Title: onError
     * @Description: TODO(扫描过程出错了会调用)
     * @param str 错误信息
     */
    public void onError(String str);
}

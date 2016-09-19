package com.joyfulmath.networkutils;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface NetworkRequestManager {
    int METHOD_GET = 0x1;
    int METHOD_POST = 0x2;

    void initManager(NetWorkResponse response);
    void release();
    void requestHttp(int method,String hostUrl,String methodUrl);
}

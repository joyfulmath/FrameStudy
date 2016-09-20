package com.joyfulmath.framestudy.network;

import com.joyfulmath.framestudy.utils.TraceLog;
import com.joyfulmath.networkutils.NetWorkResponse;
import com.joyfulmath.networkutils.NetworkRequestManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class NetworkRequestSample {

    public static void startSample()
    {
        final String hostUrl = "https://api.github.com/";
        final String methodUrl = "gists/c2a7c39532239ff261be";
        NetworkRequestManager manager = NetworkFactory.Create("okhttp");
        manager.initManager(new NetWorkResponse() {
            @Override
            public void onFailure(String error) {
                TraceLog.i(error);
            }

            @Override
            public void onResponse(String response) {
                TraceLog.i(response);
            }
        });
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        manager.requestHttp(NetworkRequestManager.METHOD_POST,
                hostUrl,
                methodUrl,
                map);
    }
}

package com.joyfulmath.framestudy.network;


import com.joyfulmath.networkutils.NetworkRequestManager;
import com.joyfulmath.networkutils.OkhttpRequestManagerImpl;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class NetworkFactory {

    public static NetworkRequestManager Create(String networkClient)
    {
        NetworkRequestManager manager = null;
        switch (networkClient)
        {
            case "okhttp":
                manager = new OkhttpRequestManagerImpl();
                break;
            default:
                throw new RuntimeException("no match client");
        }

        return manager;
    }
}

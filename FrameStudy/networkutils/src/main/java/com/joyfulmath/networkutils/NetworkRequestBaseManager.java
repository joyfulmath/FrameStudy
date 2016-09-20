package com.joyfulmath.networkutils;


import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public abstract class NetworkRequestBaseManager implements NetworkRequestManager {
    public static final int FAILUER = 1;
    public static final int SUCCESS = 2;

    private NetWorkResponse response;
    private Handler mHandler = null;

    @Override
    public void initManager(NetWorkResponse response) {
        this.response = response;
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what)
                {
                    case FAILUER:
                        deliverFailure((String) msg.obj);
                        break;
                    case SUCCESS:
                        deliverSuccess((String) msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public void release() {
        response = null;
        mHandler = null;
    }

    public void deliverFailure(String error)
    {
        if(response!=null)
        {
            response.onFailure(error);
        }
    }

    public void deliverSuccess(String result)
    {
        if(response!=null)
        {
            response.onResponse(result);
        }
    }

    public void deliverFailureInUi(String error)
    {
        Message msg = mHandler.obtainMessage(FAILUER);
        msg.obj = error;
        mHandler.sendMessage(msg);
    }

    public void deliverSuccessInUi(String result)
    {
        Message msg = mHandler.obtainMessage(SUCCESS);
        msg.obj = result;
        mHandler.sendMessage(msg);
    }
}

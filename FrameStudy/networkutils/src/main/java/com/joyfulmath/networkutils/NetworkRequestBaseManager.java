package com.joyfulmath.networkutils;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public abstract class NetworkRequestBaseManager implements NetworkRequestManager {
    private NetWorkResponse response;

    @Override
    public void initManager(NetWorkResponse response) {
        this.response = response;
    }

    @Override
    public void release() {
        response = null;
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
}

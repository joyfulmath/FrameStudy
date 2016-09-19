package com.joyfulmath.networkutils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class OkhttpRequestManagerImpl extends NetworkRequestBaseManager {
    OkHttpClient client = null;
    CallBackListener callBackListener = null;
    @Override
    public void initManager(NetWorkResponse response) {
        super.initManager(response);
        client = new OkHttpClient();
        callBackListener = new CallBackListener();
    }

    @Override
    public void release() {
        super.release();
        callBackListener = null;
        client = null;
    }

    @Override
    public void requestHttp(int method, String hostUrl, String methodUrl) {
        String url = hostUrl+methodUrl;
        final Request request = new okhttp3.Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callBackListener);
    }

    class CallBackListener implements okhttp3.Callback{

        @Override
        public void onFailure(Call call, IOException e) {
            deliverFailure(e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            deliverSuccess(response.body().toString());
        }
    }
}

package com.joyfulmath.networkutils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class OkhttpRequestManagerImpl extends NetworkRequestBaseManager {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = null;
    CallBackListener callBackListener = null;
    @Override
    public void initManager(NetWorkResponse response) {
        super.initManager(response);
        client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
        callBackListener = new CallBackListener();
    }

    @Override
    public void release() {
        super.release();
        callBackListener = null;
        client = null;
    }

    @Override
    public void requestHttp(int method, String hostUrl, String methodUrl, Map<String,String> map) {
        switch(method)
        {
            case METHOD_GET:
                requestGet(hostUrl, methodUrl);
                break;
            case METHOD_POST:
                requestPost(hostUrl,methodUrl,map);
                break;
        }

    }

    private void requestPost(String hostUrl, String methodUrl, Map<String, String> map) {
        try {
            String url = hostUrl + methodUrl;
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : map.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(map.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            final Request request = new Request.Builder().url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callBackListener);
        }catch (Exception e)
        {
            deliverFailure(e.getMessage());
        }
    }

    private void requestGet(String hostUrl, String methodUrl) {
        String url = hostUrl+methodUrl;
        final Request request = new Request.Builder().url(url)
                .addHeader("Accept", "application/json")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(callBackListener);
    }

    @Override
    public String requestHttpSync(int method, String hostUrl, String methodUrl, Map<String, String> map) {
        return null;
    }

    class CallBackListener implements okhttp3.Callback{

        @Override
        public void onFailure(Call call, IOException e) {
            deliverFailure(e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
            deliverSuccess(response.body().string());
        }
    }
}

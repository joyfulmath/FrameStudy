package com.joyfulmath.networkutils;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface NetWorkResponse {
    void onFailure(String error);
    void onResponse(final String response);
}

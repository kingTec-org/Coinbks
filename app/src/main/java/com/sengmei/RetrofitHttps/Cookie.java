package com.sengmei.RetrofitHttps;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * Created by chengwenlong on 2016/12/19.
 */

public class Cookie {
    public static OkHttpClient.Builder getClient(Context context) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.interceptors().add(new ReceivedCookiesInterceptor(context));
        client.interceptors().add(new AddCookiesInterceptor(context));

        return client;
    }
}

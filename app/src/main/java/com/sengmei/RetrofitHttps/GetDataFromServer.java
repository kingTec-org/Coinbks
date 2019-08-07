package com.sengmei.RetrofitHttps;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chengwenlong on 2016/10/21.
 */

public class GetDataFromServer {
    private static GetDataFromServer instance;
    private GetDataFromServerInterface service;

    public static GetDataFromServer getInstance(Context context){
        if(instance==null){
            instance=new GetDataFromServer(context);
        }
        return instance;
    }

    public GetDataFromServerInterface getService(){
        return service;
    }
    private GetDataFromServer(Context context){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        File httpCacheDirectory = new File(context.getExternalCacheDir(), "responses");
        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (Exception e) {
            Logger.e("OKHttp", "Could not create http cache", e);
        }
        if (cache != null) {
            httpClient.cache(cache);
        }
        httpClient.connectTimeout(600, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new MyIntercept(context));
        /*httpClient.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);*/
        Converter.Factory factory= GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                //配置cookie
                .client(Cookie.getClient(context).build())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(GetDataFromServerInterface.class);
    }
    private class MyIntercept implements Interceptor {
        private Context context;
        public MyIntercept(Context context) {
            this.context=context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isConnected(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);

            if (NetUtils.isConnected(context)){
                int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
                Logger.i("has network maxAge="+maxAge);
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            }else {
                Logger.i("network error");
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                Logger.i("has maxStale="+maxStale);
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
                Logger.i("response build maxStale="+maxStale);
            }
            return response;
        }
    }
}

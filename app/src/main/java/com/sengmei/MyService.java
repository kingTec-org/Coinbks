package com.sengmei;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.RetrofitHttps.IndexData;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.StringUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        // handler类接收数据
         SS();Log.e("hhhh","MyService="+UserBean.titleKid);
    }


    private void SS() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!StringUtil.isBlank(UserBean.titleKid))
                UserBean.KLineShow(MyService.this,UserBean.titleKid);
                Log.e("hhhh","UserBean.titleKid="+UserBean.titleKid);
            }
        }, 5000);    //延时1s执行
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

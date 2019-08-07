package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.SuoAdapter;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.SuoBean;
import com.sengmei.meililian.Utils.MyListView;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinancialActivity extends BaseActivity {

    private MyListView listview;
    private List<SuoBean.MessageBean> list=new ArrayList<>();
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.financial_activity);
        listview=findViewById(R.id.listview);
        my_lockShow();
        findViewById(R.id.suobi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinancialActivity.this,FinancialActivity1.class));
            }
        });
    }

    @Override
    public void initViews() {

    }

    @Override
    public void ReinitViews() {

    }

    @Override
    public void initData() {

    }

    public void back(View view) {
        finish();
    }

    private void my_lockShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(this).getService();
        Call<JSONObject> indexdata = mFromServerInterface.URLmy_lock();
        indexdata.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.body() == null) {

                    return;
                }
                if (response.body().getString("type").equals("ok")){
                    SuoBean bean= JSON.parseObject(response.body().toString(),SuoBean.class);

                    list.addAll(bean.getMessage());
                    SuoAdapter adapter=new SuoAdapter(FinancialActivity.this,list);
                    listview.setAdapter(adapter);
                }

                Log.e("锁锁锁锁", "dsfdfs" + response.body());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e("锁锁锁锁", "dsfdfs" + t.toString());
            }
        });
    }
}

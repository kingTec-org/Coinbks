package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.SuoAdapter;
import com.sengmei.meililian.Adapter.suo1Adapter;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.Suo1Bean;
import com.sengmei.meililian.Bean.SuoBean;
import com.sengmei.meililian.Utils.MyListView;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinancialActivity1 extends BaseActivity {

    private ListView listview;
    private TextView yue;
    private List<Suo1Bean.MessageBean> list = new ArrayList<>();
    private String configId = "", count;
    private suo1Adapter adapter;
    private EditText nums;

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.financial_activity1);
        listview = findViewById(R.id.listview);
        yue = findViewById(R.id.yue);
        nums = findViewById(R.id.nums);
        my_lockShow();
        balanceShow();
        findViewById(R.id.suobi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = nums.getText().toString().trim();
                if (StringUtil.isBlank(count)) {
                    StringUtil.ToastShow(FinancialActivity1.this,"数量不能为空");
                } else {
                    if (list.size() == 0) {
                        return;
                    }
                    lockShow();
                }
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                configId = "" + list.get(position).getId();
                adapter.setPont(position);
                adapter.notifyDataSetChanged();
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

    /*利率*/
    private void my_lockShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(this).getService();
        Call<JSONObject> indexdata = mFromServerInterface.URLconfigList();
        indexdata.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.body() == null) {

                    return;
                }

                Log.e("锁锁锁锁", "dsfdfs" + response.body());
                if (response.body().getString("type").equals("ok")) {
                    Suo1Bean bean = JSON.parseObject(response.body().toString(), Suo1Bean.class);

                    list.addAll(bean.getMessage());
                    adapter = new suo1Adapter(FinancialActivity1.this, list);
                    listview.setAdapter(adapter);
                    if (bean.getMessage().size() > 0) {
                        configId = "0";
                        adapter.setPont(0);
                    }
                }
                Log.e("锁锁锁锁", "dsfdfs" + response.body());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e("锁锁锁锁", "dsfdfs" + t.toString());
            }
        });
    }

    /*余额*/
    private void balanceShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(this).getService();
        Call<JSONObject> indexdata = mFromServerInterface.URLbalance();
        indexdata.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.body() == null) {

                    return;
                }
                if (response.body().getString("type").equals("ok")) {
                    count = "" + response.body().getString("message");
                    yue.setText("" + response.body().getString("message"));
                }
                Log.e("锁锁锁锁", "dsfdfs" + response.body());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e("锁锁锁锁", "dsfdfs" + t.toString());
            }
        });
    }

    /*确定*/
    private void lockShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(this).getService();
        Call<JSONObject> indexdata = mFromServerInterface.URLmylock(count, configId);
        indexdata.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.body() == null) {

                    return;
                }
                if (response.body().getString("type").equals("ok")) {
                    startActivity(new Intent(FinancialActivity1.this, FinancialActivity1.class));
                } else {
                    StringUtil.ToastShow(FinancialActivity1.this, response.body().getString("message"));
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

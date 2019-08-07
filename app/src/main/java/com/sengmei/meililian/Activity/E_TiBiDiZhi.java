package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.sengmei.RetrofitHttps.Beans.FaBiTiTledizhiBean;
import com.sengmei.RetrofitHttps.Beans.YinSiBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.E_TiBiAdapter;
import com.sengmei.meililian.Adapter.E_TiBiDiZhiAdapter;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.DataBean;
import com.sengmei.meililian.Bean.E_TiBiBean;
import com.sengmei.meililian.Bean.E_TiBiDiZhiBean;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.HttpUtils;
import com.sengmei.meililian.Utils.SharedPreferencesUtil;
import com.sengmei.meililian.Utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class E_TiBiDiZhi extends BaseActivity {

    private E_TiBiAdapter adapter;
    private ListView listView;
    private List<FaBiTiTledizhiBean.ObjectBean> list=new ArrayList<>();
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.e_tibidizhi);
        TiBiListShow();
    }

    @Override
    public void initViews() {

        listView=(ListView)findViewById(R.id.listview);
    }

    @Override
    public void ReinitViews() {
        TiBiListShow();
    }

    @Override
    public void initData() {

    }

    //填充list数据
    private void setList(final List<FaBiTiTledizhiBean.ObjectBean> news) {
        if (list != null) {
            list.clear();
        }
        list.addAll(news);
        adapter=new E_TiBiAdapter(E_TiBiDiZhi.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(E_TiBiDiZhi.this,E_TiBiActivity.class)
                        .putExtra("name",list.get(i).getName())
                        .putExtra("ids",list.get(i).getId()));
            }
        });
    }
    //返佣列表
    private void TiBiListShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_TiBiDiZhi.this).getService();
        Call<FaBiTiTledizhiBean> indexdata = mFromServerInterface.getcurrencylist();
        indexdata.enqueue(new Callback<FaBiTiTledizhiBean>() {

            @Override
            public void onResponse(Call<FaBiTiTledizhiBean> call, Response<FaBiTiTledizhiBean> response) {
                if (response.body() == null) {
                    StringUtil.ToastShow(E_TiBiDiZhi.this,"请先登录");
                    return;
                }
                if (response.body().getType().equals("ok")) {

                   setList(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<FaBiTiTledizhiBean> call, Throwable t) {
                StringUtil.ToastShow(E_TiBiDiZhi.this,"请先登录");
            }
        });
    }

    public void back(View v) {
        finish();
    }
}

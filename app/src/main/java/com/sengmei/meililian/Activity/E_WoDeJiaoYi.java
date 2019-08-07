package com.sengmei.meililian.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.util.LogUtils;
import com.sengmei.RetrofitHttps.Beans.JYLBBean;
import com.sengmei.RetrofitHttps.Beans.WDJY1Bean;
import com.sengmei.RetrofitHttps.Beans.WDJYBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.E_WoDeJiaoYiAdapter;
import com.sengmei.meililian.Adapter.E_WoDeJiaoYiAdapter1;
import com.sengmei.meililian.Adapter.E_WoDeJiaoYioutAdapter;
import com.sengmei.meililian.Adapter.JiaoYiListAdapter;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.DataBean;
import com.sengmei.meililian.Bean.E_WoDeJiaoYiBean;
import com.sengmei.meililian.Bean.E_WoDeJiaoYiBean1;
import com.sengmei.meililian.InterFaces.PiLiangSanChu;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.HttpUtils;
import com.sengmei.meililian.Utils.MyListView;
import com.sengmei.meililian.Utils.RefreshDialog;
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

public class E_WoDeJiaoYi extends BaseActivity implements View.OnClickListener, PiLiangSanChu {
    private View v1, v2, v3;
    private TextView maichu, mairu, wancheng, wu, dangqian;

    private E_WoDeJiaoYiAdapter adapter;
    private ListView listView;
    private List<E_WoDeJiaoYiBean> list = new ArrayList<>();
    private E_WoDeJiaoYiAdapter1 adapter1;
    private List<E_WoDeJiaoYiBean1> list1 = new ArrayList<>();
    private RefreshDialog dialog;
    private E_WoDeJiaoYioutAdapter adapter2;
    private LinearLayout lldang;
    private String MM="maichu";

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.e_wodejiaoyi);
    }

    @Override
    public void initViews() {
        dialog = new RefreshDialog(E_WoDeJiaoYi.this);
        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        wu = (TextView) findViewById(R.id.wu);
        maichu = (TextView) findViewById(R.id.maichu);
        mairu = (TextView) findViewById(R.id.mairu);
        wancheng = (TextView) findViewById(R.id.wancheng);
        lldang = (LinearLayout) findViewById(R.id.lldang);

        maichu.setOnClickListener(this);
        mairu.setOnClickListener(this);
        wancheng.setOnClickListener(this);

        dangqian = (TextView) findViewById(R.id.dangqian);
        dangqian.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);

    }

    @Override
    public void ReinitViews() {
    }

    @Override
    public void initData() {
        JiaoYioutShow();
    }

    public void back(View v) {
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.maichu:
                MM="maichu";
                StarColor();
                maichu.setTextColor(getResources().getColor(R.color.blue));
                v1.setBackgroundResource(R.color.blue);
                JiaoYioutShow();
                break;
            case R.id.mairu:
                MM="mairu";
                StarColor();
                mairu.setTextColor(getResources().getColor(R.color.blue));
                v2.setBackgroundResource(R.color.blue);
                JiaoYiinShow();
                break;
            case R.id.dangqian:
                lldang.setVisibility(View.VISIBLE);
                dangqian.setBackgroundResource(R.drawable.button_wt1);
                wancheng.setBackgroundResource(R.drawable.button_wt22);
                if (MM.equals("maichu")){
                    maichu.setTextColor(getResources().getColor(R.color.blue));
                    v1.setBackgroundResource(R.color.blue);
                    JiaoYioutShow();
                }else {
                    mairu.setTextColor(getResources().getColor(R.color.blue));
                    v2.setBackgroundResource(R.color.blue);
                    JiaoYiinShow();
                }
                break;
            case R.id.wancheng:
                lldang.setVisibility(View.GONE);
                dangqian.setBackgroundResource(R.drawable.button_wt);
                wancheng.setBackgroundResource(R.drawable.button_wt2);
                FaBiGetShow();
                break;
            default:
                break;
        }
    }

    private void StarColor() {
        maichu.setTextColor(getResources().getColor(R.color.color_text_black));
        mairu.setTextColor(getResources().getColor(R.color.color_text_black));
        v2.setBackgroundResource(R.color.white);
        wancheng.setTextColor(getResources().getColor(R.color.color_text_black));
        v1.setBackgroundResource(R.color.white);

    }

    private void JiaoYiinShow() {
        dialog.showLoading();
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_WoDeJiaoYi.this).getService();
        Call<WDJYBean> indexdata = mFromServerInterface.getLins();
        indexdata.enqueue(new Callback<WDJYBean>() {

            @Override
            public void onResponse(Call<WDJYBean> call, Response<WDJYBean> response) {
                if (response.body() == null) {
                    Toast.makeText(E_WoDeJiaoYi.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.hideLoading();
                if (response.body().getType().equals("ok")) {
                    listView.setVisibility(View.VISIBLE);
                    wu.setVisibility(View.GONE);
                    adapter = new E_WoDeJiaoYiAdapter(E_WoDeJiaoYi.this, response.body().getMessage().getList());
                    listView.setAdapter(adapter);

                    adapter.setPiLiangSanChu(E_WoDeJiaoYi.this);
                    if (response.body().getMessage().getList().size() == 0) {
                        listView.setVisibility(View.GONE);
                        wu.setVisibility(View.VISIBLE);
                    }
                } else {
                    listView.setVisibility(View.GONE);
                    wu.setVisibility(View.VISIBLE);
                }
                Log.e("交易", "=" + response.body().getType());
                Log.e("交易", "=" + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<WDJYBean> call, Throwable t) {
                Log.e("AAAAA", "@@11=" + t.getMessage());dialog.hideLoading();
                // Toast.makeText(E_WoDeJiaoYi.this, "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void JiaoYioutShow() {
        dialog.showLoading();
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_WoDeJiaoYi.this).getService();
        Call<WDJYBean> indexdata = mFromServerInterface.getouts();
        indexdata.enqueue(new Callback<WDJYBean>() {

            @Override
            public void onResponse(Call<WDJYBean> call, Response<WDJYBean> response) {
                dialog.hideLoading();
                if (response.body() == null) {
                    StringUtil.ToastShow(E_WoDeJiaoYi.this, "请先登录");
                    return;
                }
                Log.e("交易", "=" + response.body().getType());
                Log.e("交易", "=" + response.body().getMessage());
                if (response.body().getType().equals("ok")) {
                    listView.setVisibility(View.VISIBLE);
                    wu.setVisibility(View.GONE);
                    Log.e("AAAAA", "@@11=" + response.body().getType());
                    Log.e("AAAAA", "@@11=" + response.body().getMessage().getList().size());
                    adapter2 = new E_WoDeJiaoYioutAdapter(E_WoDeJiaoYi.this, response.body().getMessage().getList());

                    adapter2.setPiLiangSanChu(E_WoDeJiaoYi.this);
                    listView.setAdapter(adapter2);
                    if (response.body().getMessage().getList().size() == 0) {
                        listView.setVisibility(View.GONE);
                        wu.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<WDJYBean> call, Throwable t) {
                dialog.hideLoading();
                StringUtil.ToastShow(E_WoDeJiaoYi.this, "请先登录");
                //  Toast.makeText(E_WoDeJiaoYi.this, "请求数据失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void FaBiGetShow() {
        dialog.showLoading();
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_WoDeJiaoYi.this).getService();
        Call<WDJY1Bean> indexdata = mFromServerInterface.getcomplete();
        indexdata.enqueue(new Callback<WDJY1Bean>() {

            @Override
            public void onResponse(Call<WDJY1Bean> call, Response<WDJY1Bean> response) {
                dialog.hideLoading();
                if (response.body() == null) {
                    Toast.makeText(E_WoDeJiaoYi.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("交易", "=" + response.body().getType());
                Log.e("交易", "=" + response.body().getMessage());
                if (response.body().getType().equals("ok")) {
                    listView.setVisibility(View.VISIBLE);
                    wu.setVisibility(View.GONE);
                    Log.e("AAAAA", "@@11=" + response.body().getType());
                    Log.e("AAAAA", "@@11=" + response.body().getMessage().getList().size());
                    adapter1 = new E_WoDeJiaoYiAdapter1(E_WoDeJiaoYi.this, response.body().getMessage().getList());
                    listView.setAdapter(adapter1);
                    if (response.body().getMessage().getList().size() == 0) {
                        listView.setVisibility(View.GONE);
                        wu.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<WDJY1Bean> call, Throwable t) {
                dialog.hideLoading();
                Log.e("AAAAA", "@@11=" + t.getMessage());
                // Toast.makeText(E_WoDeJiaoYi.this, "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void IsClick(String s, boolean b) {
        if (s.equals("0")) {
            JiaoYioutShow();
        } else {
            JiaoYiinShow();
        }
    }
}

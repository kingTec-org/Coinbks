package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sengmei.RetrofitHttps.Adapter.E_TiBiDiZhiAdapter;
import com.sengmei.RetrofitHttps.Beans.TiBidiZhiBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.E_TiBiDiZhiBean;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class E_TiBiActivity extends BaseActivity {
   // private E_TiBiDiZhiAdapter adapter;
    private ListView listView;
    private List<E_TiBiDiZhiBean> list=new ArrayList<>();
    private TextView wu;
    private String Ids;
    private List<TiBidiZhiBean.ObjectBean> listD = new ArrayList<>();
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.e_tibiactivity);
        String names=getIntent().getStringExtra("name");
        Ids=getIntent().getStringExtra("ids");

        Log.e("AAAAA","@@E_TiBiActivity="+Ids);
        TextView dizhi=(TextView)findViewById(R.id.dizhi);
        dizhi.setText(names+"  地址");
    }

    @Override
    public void initViews() {
        wu=(TextView)findViewById(R.id.wu);
        listView=(ListView)findViewById(R.id.listview);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AAAAA","@@listView="+Ids);
          startActivity(new Intent(E_TiBiActivity.this,E_TiBiTianJiaActivity.class).putExtra("Ids",Ids));
            }
        });
    }

    @Override
    public void ReinitViews() {
        if (list.size()>0){
            list.clear();
        }
        TiBiListShow();

    }

    @Override
    public void initData() {

    }
    public void back(View v) {
        finish();
    }

    //地址
   /* private void TiBiListShow() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    String s = msg.obj.toString().trim();
                    Log.e("WoDeShow1", "1111s=" + s);
                    //  Toast.makeText(getActivity(),"s="+s,Toast.LENGTH_LONG).show();
                    try {
                        JSONObject objectt = new JSONObject(s);
                        String type = objectt.getString("type");
                        DataBean.Types=type;
                        String message = objectt.getString("message");
                        LogUtils.e("FanYongListShow=" + message);
                        if (type.equals("ok")) {
                            JSONArray array = new JSONArray(message);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject objec0 = array.getJSONObject(i);
                                int ids = objec0.getInt("id");
                                String notes = objec0.getString("notes");
                                String address = objec0.getString("address");
                                E_TiBiDiZhiBean Bean=new E_TiBiDiZhiBean();
                                Bean.setNames(notes);
                                Bean.setNums1(address);
                                Bean.setNums2(""+ids);
                                list.add(Bean);
                            }
                            if (list.size()==0){
                                wu.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }else {
                                wu.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                adapter=new E_TiBiDiZhiAdapter(E_TiBiActivity.this,list);
                                listView.setAdapter(adapter);
                            }

                        } else {
                            StringUtil.ToastShow1(E_TiBiActivity.this, message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // 启动线程来执行任务
        new Thread() {
            public void run() {
                // 请求网络
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("currency", Ids);
                String result = HttpUtils.newpost(map,UserBean.URLget_address);
                Message m = new Message();
                m.what = 1;
                m.obj = result;
                // 发送消息到Handler
                handler.sendMessage(m);
            }
        }.start();
    }*/

    //填充list数据
    private void setList(final List<TiBidiZhiBean.ObjectBean> news) {
        if (listD != null) {
            listD.clear();
        }
        listD.addAll(news);
        Log.e("提笔=", "@@11=" + listD.size());
        if (listD.size()==0){
             wu.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
             wu.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            E_TiBiDiZhiAdapter adapter=new  E_TiBiDiZhiAdapter(E_TiBiActivity.this,listD);
            listView.setAdapter(adapter);
        }
    }
    private void TiBiListShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_TiBiActivity.this).getService();
        Call<TiBidiZhiBean> indexdata = mFromServerInterface.getURL_address(Ids);
        indexdata.enqueue(new Callback<TiBidiZhiBean>() {

            @Override
            public void onResponse(Call<TiBidiZhiBean> call, Response<TiBidiZhiBean> response) {

                if (response.body() == null) {
                    StringUtil.ToastShow(E_TiBiActivity.this,"请先登录");
                    return;
                }
                Log.e("KeYongMaichuShow卖出=", "@@11=" + response.body().getType());
                if (response.body().getType().equals("ok")) {
                    setList(response.body().getMessage());

                }else {

                    wu.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                Log.e("KeYongMaichuShow卖出=", "@@11=" + response.body().getMessage());

            }

            @Override
            public void onFailure(Call<TiBidiZhiBean> call, Throwable t) {
                StringUtil.ToastShow(E_TiBiActivity.this,"请先登录");
            }
        });
    }
}

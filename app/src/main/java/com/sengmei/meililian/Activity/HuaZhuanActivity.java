package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sengmei.RetrofitHttps.Adapter.FaBiTitleAdapter;
import com.sengmei.RetrofitHttps.Adapter.ZhuanHuanAdapter;
import com.sengmei.RetrofitHttps.Beans.FaBiTiTleBean;
import com.sengmei.RetrofitHttps.Beans.HuanZhuanBean;
import com.sengmei.RetrofitHttps.Beans.JYLBBean;
import com.sengmei.RetrofitHttps.Beans.KeYongBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.Beans.ZhuanHuanBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.JiaoYiListAdapter;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.HorizontalListView;
import com.sengmei.meililian.Utils.MyListView;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HuaZhuanActivity extends BaseActivity implements View.OnClickListener {
    private HorizontalListView hlistview;
    private ZhuanHuanAdapter mAdapter;
    private TextView tit, tv1, tv2,bt;
    private boolean tt = true;
    private EditText et;
    private String str;
    private TextView name1,name2,name3,wu;
    private TextView mon1,mon2,mon3;
    private List<FaBiTiTleBean.ObjectBean> hlist = new ArrayList<>();
    private List<JYLBBean.ObjectBean> Hlistbt=new ArrayList<>();
    private String currencys;
    private int jobId;
    private String types="1";
    private MyListView listv;
    private String keyong, dongjie, zhehe;
    private String keyong2, dongjie2, zhehe2;
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.huazhuanactivity);

        currencys=getIntent().getStringExtra("currencys");
        DataView();
    }

    @Override
    public void initViews() {
        wu = (TextView) findViewById(R.id.wu);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        mon1 = (TextView) findViewById(R.id.mon1);
        mon2 = (TextView) findViewById(R.id.mon2);
        mon3 = (TextView) findViewById(R.id.mon3);
        hlistview = (HorizontalListView) findViewById(R.id.hlistview);
        et = (EditText) findViewById(R.id.et);
        tit = (TextView) findViewById(R.id.tit);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        bt = (TextView) findViewById(R.id.bt);
        findViewById(R.id.zhuan).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);

        listv = (MyListView) findViewById(R.id.listv);
    }

    @Override
    public void ReinitViews() {

    }

    @Override
    public void initData() {

    }

    //法币币种标题
    private void DataView() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(HuaZhuanActivity.this).getService();
        Call<FaBiTiTleBean> indexdata = mFromServerInterface.getFaBiTiTle1();
        indexdata.enqueue(new Callback<FaBiTiTleBean>() {

            @Override
            public void onResponse(Call<FaBiTiTleBean> call, Response<FaBiTiTleBean> response) {
                if (response.body() == null) {
                    StringUtil.ToastShow(HuaZhuanActivity.this,"请先登录");
                    return;
                }
                List<FaBiTiTleBean.ObjectBean> object = response.body().getMessage();
                setList(object);
                Log.e("AAAAAB", "FaBiTitle=" + response.body().getMessage());


            }

            @Override
            public void onFailure(Call<FaBiTiTleBean> call, Throwable t) {
                Log.e("AAAAA", "@@11=" + t.getMessage());
                StringUtil.ToastShow(HuaZhuanActivity.this,"请先登录");
            }
        });
    }

    //填充list数据
    private void setList(final List<FaBiTiTleBean.ObjectBean> news) {
        if (hlist != null) {
            hlist.clear();
        }
        hlist.addAll(news);
        mAdapter = new ZhuanHuanAdapter(HuaZhuanActivity.this, hlist);
        hlistview.setAdapter(mAdapter);

        jobId = hlist.get(0).getId();
        KeYongMaichuShow(jobId+"");
        JiaoYiShow(jobId+"");
        hlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jobId = hlist.get(position).getId();
                /*Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id",jobId+"");
                startActivity(intent);*/
                mAdapter.setSelectedPosition(position);
                mAdapter.notifyDataSetInvalidated();
                KeYongMaichuShow(jobId+"");
                JiaoYiShow(jobId+"");

            }
        });
    }

    public void back(View v) {
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuan:
                if (tt == true) {
                    types="2";
                    tt = false;
                    tv1.setText("交易账户");
                    tv2.setText("法币账户");
                    mon1.setText(keyong2);
                    mon2.setText(dongjie2);
                    mon3.setText(zhehe2);
                } else {
                    types="1";
                    tt = true;
                    tv1.setText("法币账户");
                    tv2.setText("交易账户");
                    mon1.setText(keyong);
                    mon2.setText(dongjie);
                    mon3.setText(zhehe);
                }
                break;
            case R.id.next:
                str=et.getText().toString().trim();
                if (str.length()>0){
                    ChangeShow();
                }else {
                    StringUtil.ToastShow1(HuaZhuanActivity.this, "数据不能为空");
                }
                break;
            default:
                break;
        }
    }

    private void ChangeShow(){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(this).getService();
        Call<ZhuCeBean> indexdata=mFromServerInterface.Getchange(str,currencys,types);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {
                    Toast.makeText(HuaZhuanActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("AAAAA",response.body().getType()+"@@="+response.body().getMessage() );
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow1(HuaZhuanActivity.this, response.body().getMessage());
                    DataView();
                } else {
                    StringUtil.ToastShow1(HuaZhuanActivity.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAA","@@11="+t.getMessage() );
            }
        });
    }

    /**
     * 卖出可用
     */
    private void KeYongMaichuShow(final String id) {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(HuaZhuanActivity.this).getService();
        Call<HuanZhuanBean> indexdata = mFromServerInterface.getURL_walletHZ("legal",id);
        indexdata.enqueue(new Callback<HuanZhuanBean>() {

            @Override
            public void onResponse(Call<HuanZhuanBean> call, Response<HuanZhuanBean> response) {

                if (response.body() == null) {
                    return;
                }
                if (response.body().getType().equals("ok")) {
                    String Names=response.body().getMessage().getCurrency_name();
                    Log.e("keyong=",keyong+"="+dongjie+"zhehe"+zhehe+"=="+Names);
                    tit.setText(Names);
                    name1.setText("可用("+Names+")");
                    name2.setText("冻结("+Names+")");
                    name3.setText("折合(CNY)");
                    keyong=response.body().getMessage().getLegal_balance();
                    dongjie=response.body().getMessage().getLock_legal_balance();
                    zhehe=response.body().getMessage().getLegal_cny_price();
                    keyong2=response.body().getMessage().getChange_balance();
                    dongjie2=response.body().getMessage().getLock_change_balance();
                    zhehe2=response.body().getMessage().getChange_price();
                    if("1".equals(types)){
                        mon1.setText(keyong);
                        mon2.setText(dongjie);
                        mon3.setText(zhehe);
                    }else {
                        mon1.setText(keyong2);
                        mon2.setText(dongjie2);
                        mon3.setText(zhehe2);

                    }

                }


            }

            @Override
            public void onFailure(Call<HuanZhuanBean> call, Throwable t) {
                Log.e("KeYongMaichuShow卖出=", "@@11=" + t.getMessage());
                UserBean.idbutown="0";
            }
        });
    }

    private void setHListbt(final List<JYLBBean.ObjectBean> news) {
        if (Hlistbt != null) {
            Hlistbt.clear();
        }
        Log.e("AAASDADSAD", "news.size()=" + news.size());
        Hlistbt.addAll(news);
        if (Hlistbt.size()==0){

            wu.setVisibility(View.VISIBLE);listv.setVisibility(View.GONE);
        }else {

            wu.setVisibility(View.GONE);listv.setVisibility(View.VISIBLE);
        }
        JiaoYiListAdapter adapter=new JiaoYiListAdapter(HuaZhuanActivity.this,Hlistbt);
        listv.setAdapter(adapter);
        /*hListAdapter=new HListAdapter(getActivity(),Hlist);
        hlistview.setAdapter(hListAdapter);*/
    }
    private void JiaoYiShow(String str){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(HuaZhuanActivity.this).getService();
        Call<JYLBBean> indexdata=mFromServerInterface.getlegal_log(str,"legal");
        indexdata.enqueue(new Callback<JYLBBean>() {

            @Override
            public void onResponse(Call<JYLBBean> call, Response<JYLBBean> response) {
                if (response.body() == null) {
                    StringUtil.ToastShow(HuaZhuanActivity.this,"请先登录");
                    return;
                }
                if (response.body().getType().equals("ok")) {
                    setHListbt(response.body().getMessage().getList());
                  /*  wu.setVisibility(View.GONE);
                    if (response.body().getMessage().getList().size()==0){
                        wu.setVisibility(View.VISIBLE);listv.setVisibility(View.GONE);
                    }else {

                        wu.setVisibility(View.GONE);listv.setVisibility(View.VISIBLE);
                        JiaoYiListAdapter adapter=new JiaoYiListAdapter(HuaZhuanActivity.this,response.body().getMessage().getList());
                        listv.setAdapter(adapter);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<JYLBBean> call, Throwable t) {
                Log.e("AAAAA","@@11="+t.getMessage() );
                StringUtil.ToastShow(HuaZhuanActivity.this,"请先登录");
            }
        });
    }
}

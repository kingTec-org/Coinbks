package com.sengmei.meililian.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.RetrofitHttps.IndexData;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Bean.DataBean;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.HttpUtils;
import com.sengmei.meililian.Utils.SharedPreferencesUtil;
import com.sengmei.meililian.Utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class E_TiBiTianJiaActivity extends BaseActivity implements View.OnClickListener {
    private EditText addrss, beizhu;
    private TextView next;
    private String Ids, Addrsss, Beizhus;

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.e_tibitianjiaactivity);
        Ids = getIntent().getStringExtra("Ids");
        Log.e("AAAAA","@@E_TiBiTianJiaActivity="+Ids);
    }

    @Override
    public void initViews() {
        addrss = (EditText) findViewById(R.id.addrss);
        beizhu = (EditText) findViewById(R.id.beizhu);
        next = (TextView) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void ReinitViews() {

    }

    @Override
    public void initData() {
        findViewById(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
            }
        });
    }

    public void back(View v) {
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                Beizhus= beizhu.getText().toString().trim();
                Addrsss = addrss.getText().toString().trim();
                if (Beizhus.length() > 0 & Addrsss.length() > 0) {
                    TianJiaShow();
                } else {
                    Toast.makeText(E_TiBiTianJiaActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    //添加地址
    private void TianJiaShow(){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(this).getService();
        Call<IndexData> indexdata=mFromServerInterface.getTBdz(Ids,Addrsss,Beizhus);
        indexdata.enqueue(new Callback<IndexData>() {

            @Override
            public void onResponse(Call<IndexData> call, Response<IndexData> response) {
                if (response.body() == null) {
                    StringUtil.ToastShow(E_TiBiTianJiaActivity.this,"请先登录");
                    return;
                }
                Log.e("AAAAA","@@11="+Ids+"##"+response.body().getMessage());
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow1(E_TiBiTianJiaActivity.this, response.body().getMessage());
                    finish();
                } else {
                    StringUtil.ToastShow1(E_TiBiTianJiaActivity.this,  response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<IndexData> call, Throwable t) {
                StringUtil.ToastShow(E_TiBiTianJiaActivity.this,"请先登录");
            }
        });
    }
}

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
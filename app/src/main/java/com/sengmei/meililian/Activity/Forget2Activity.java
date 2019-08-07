package com.sengmei.meililian.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Utils.StringUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forget2Activity extends BaseActivity implements View.OnClickListener{
    private EditText password,passwords,yanzheng;
    private TextView next;
    private String PassWord,PassWords;
    private String zhanghao,yanzhengma;

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.forget);
        zhanghao=getIntent().getStringExtra("zhanghao");
    }

    @Override
    public void initViews() {
        password = (EditText) findViewById(R.id.password);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwords = (EditText) findViewById(R.id.passwords);
        passwords.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        yanzheng = (EditText) findViewById(R.id.yanzheng);
        next=(TextView)findViewById(R.id.next);
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
        switch (view.getId()){
            case R.id.next:
                PassWord=password.getText().toString().trim();
                PassWords=passwords.getText().toString().trim();
                yanzhengma=yanzheng.getText().toString().trim();
                if (StringUtil.isBlank(PassWord)){
                    StringUtil.ToastShow(Forget2Activity.this,"密码不能为空");

                }else if (!PassWord.equals(PassWords)){
                    StringUtil.ToastShow(Forget2Activity.this,"密码不一致");
                }else if (StringUtil.isBlank(yanzhengma)){
                    StringUtil.ToastShow(Forget2Activity.this,"验证码不能为空");
                }else {
                    next.setClickable(false);
                    ChongZhiMiMaShow();
                }
                break;
                default:
        }
    }

    //重置密码
    private void ChongZhiMiMaShow(){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(this).getService();
        Call<ZhuCeBean> indexdata=mFromServerInterface.Loginforget(zhanghao,PassWord,PassWords,
                yanzhengma);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {next.setClickable(true);
                if (response.body() == null) {
                    Toast.makeText(Forget2Activity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("AAAAA",response.body().getType()+"@@="+response.body().getMessage() );
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow1(Forget2Activity.this, response.body().getMessage());
                    startActivity(new Intent(Forget2Activity.this, LoginActivity.class));
                } else {
                    StringUtil.ToastShow1(Forget2Activity.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAA","@@11="+t.getMessage() );next.setClickable(true);
            }
        });
    }
    //设置密码
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
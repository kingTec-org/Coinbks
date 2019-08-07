package com.sengmei.meililian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;
import com.sengmei.RetrofitHttps.Beans.AnQuanBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Utils.StringUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaBiMiMaActivity extends BaseActivity {
    private String titles,Passws,Passwords,Passwordss;
    private TextView title,next,forgot_password;
    private EditText passw,password,passwords;
    private RelativeLayout rl;
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.fabimima);
        titles=getIntent().getStringExtra("titles");

    }

    @Override
    public void initViews() {
        title=(TextView)findViewById(R.id.title);
        next=(TextView)findViewById(R.id.next);
        passw=(EditText)findViewById(R.id.passw);
        passw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password=(EditText)findViewById(R.id.password);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwords=(EditText)findViewById(R.id.passwords);
        passwords.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        rl=(RelativeLayout)findViewById(R.id.rl);
        findViewById(R.id.forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FaBiMiMaActivity.this,JiaoYiChongZhiMiMa.class));
            }
        });
        if (titles.equals("0")){
            title.setText("");
            rl.setVisibility(View.GONE);
        }else {
            title.setText("重新设置密码");
            rl.setVisibility(View.VISIBLE);

        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passws=passw.getText().toString().trim();
                Passwords=password.getText().toString().trim();
                Passwordss=passwords.getText().toString().trim();
                if (StringUtil.isBlank(Passwords)){
                    StringUtil.ToastShow(FaBiMiMaActivity.this,"密码不能为空");
                    return;
                }
                if (StringUtil.isNumeric(Passwords)){
                    StringUtil.ToastShow(FaBiMiMaActivity.this,"密码不能是纯数字");
                    return;
                }


                if (!Passwords.equals(Passwordss)){
                    StringUtil.ToastShow(FaBiMiMaActivity.this,"密码不一致");
                }
                if (titles.equals("0")){
                    MiMaShow();
                }else {
                    if (StringUtil.isBlank(Passws)){
                        StringUtil.ToastShow(FaBiMiMaActivity.this,"旧密码不能为空");
                    }else {
                        ChongZhiShow();
                    }

                }

            }
        });
    }

    @Override
    public void ReinitViews() {

    }

    @Override
    public void initData() {

    }
    public void back(View v) {
        finish();
    }

    //重置密码
    private void ChongZhiShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(FaBiMiMaActivity.this).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.getupdate(Passws,Passwords,Passwordss);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {

                if (response.body() == null) {
                    return;
                }
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow(FaBiMiMaActivity.this,response.body().getMessage());
                    finish();
                }else {
                    StringUtil.ToastShow(FaBiMiMaActivity.this,response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAA", "@@11=" + t.getMessage());
              //  StringUtil.ToastShow(FaBiMiMaActivity.this,"设置失败");
            }
        });
    }
    private void MiMaShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(FaBiMiMaActivity.this).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.getmima(Passwords,Passwordss);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {

                if (response.body() == null) {
                    return;
                }
                Log.e("AAAAA", "@@11=" + response.body().getType());
                if (response.body().getType().equals("ok")) {

                    Log.e("AAAAA", "@@11=" + response.body().getMessage());
                    StringUtil.ToastShow(FaBiMiMaActivity.this,response.body().getMessage());
                    finish();
                }else {
                    StringUtil.ToastShow(FaBiMiMaActivity.this,response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAA", "@@11=" + t.getMessage());
               // StringUtil.ToastShow(FaBiMiMaActivity.this,"设置失败");
            }
        });
    }
}

package com.sengmei.meililian.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.util.LogUtils;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.HttpUtils;
import com.sengmei.meililian.Utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends BaseActivity implements View.OnClickListener{
    private EditText phone,rt_code;
    private TextView next;
    private String Phones;
    private String codes;
    private String ZhuanHuan="Sj";

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.forgetactivity);
    }

    @Override
    public void initViews() {
        phone = (EditText) findViewById(R.id.phone);
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
        switch (view.getId()){
            case R.id.next:
                Phones=phone.getText().toString().trim();
                if (StringUtil.isBlank(Phones)){
                    Toast.makeText(ForgetActivity.this,"请输入手机号或邮箱",Toast.LENGTH_SHORT).show();
                    return;
                }
               if (StringUtil.isMobile(Phones)){
                    next.setClickable(false);
                   FSJYanZhengShow();
               }else if (StringUtil.isEmail(Phones)){
                    next.setClickable(false);
                   FYXYanZhengShow();
               }else {
                   Toast.makeText(ForgetActivity.this,"请输入正确手机号或邮箱",Toast.LENGTH_SHORT).show();
               }
               // showDialog1();  //弹窗
                break;
                default:
                    break;
        }
    }

  /*  private void showDialog1() {
        final Dialog bottomDialog = new Dialog(ZhuCeActivity.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(ZhuCeActivity.this).inflate(R.layout.shoujiyanzheng_dialog, null);
        TextView quxiao=(TextView) contentView.findViewById(R.id.quxiao);
        TextView fasong=(TextView) contentView.findViewById(R.id.fasong);
        TextView next=(TextView) contentView.findViewById(R.id.next);
        rt_code=(EditText)contentView.findViewById(R.id.rt_code);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.cancel();
            }
        });
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FSJYanZhengShow();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codes=rt_code.getText().toString().trim();
                PSJYanZhengShow(codes);
            }
        });
    }*/

    //发送手机验证码
    private void FSJYanZhengShow(){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(this).getService();
        Call<ZhuCeBean> indexdata=mFromServerInterface.getyanzhengma(Phones,"forget");
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                next.setClickable(true);
                if (response.body() == null) {

                    return;
                }
                Log.e("AAAAA","注册="+response.body().getMessage() );
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow1(ForgetActivity.this, response.body().getMessage());
                    startActivity(new Intent(ForgetActivity.this,Forget2Activity.class)
                            .putExtra("zhanghao",Phones)
                            .putExtra("types","mobile"));
                }
            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAA","@@11="+t.getMessage() );next.setClickable(true);
            }
        });
    }
    //发送邮箱验证码
    private void FYXYanZhengShow(){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(this).getService();
        Call<ZhuCeBean> indexdata=mFromServerInterface.getyouxiangyanzheng(Phones,"forget");
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                next.setClickable(true);
                if (response.body() == null) {

                    return;
                }
                Log.e("AAAAA","注册="+response.body().getMessage() );
                if (response.body().getType().equals("ok")) {
                    StringUtil.ToastShow1(ForgetActivity.this, response.body().getMessage());
                    startActivity(new Intent(ForgetActivity.this,Forget2Activity.class)
                            .putExtra("zhanghao",Phones)
                            .putExtra("types","email"));
                }
            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                next.setClickable(true);
                Log.e("AAAAA","@@11="+t.getMessage() );
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
package com.sengmei.meililian.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;

public class E_YouXiangZheng extends BaseActivity implements View.OnClickListener{
    private TextView tv_youxiang;
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.e_youxiangyanzheng);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void initViews() {
        tv_youxiang=(TextView)findViewById(R.id.tv_youxiang);
        tv_youxiang.setText(E_AnQuanZhongXin.youxiangs);
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

    @Override
    public void onClick(View view) {

    }
}

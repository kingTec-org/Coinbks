package com.sengmei.meililian.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sengmei.RetrofitHttps.Beans.GengXinBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Adapter.BangDanAdapter;
import com.sengmei.meililian.Fragment.E_ZCA;
import com.sengmei.meililian.Fragment.E_ZCAA;
import com.sengmei.meililian.Fragment.E_ZCB;
import com.sengmei.meililian.Fragment.E_ZCBB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class E_WodeZiChan extends FragmentActivity implements View.OnClickListener {
    private ViewPager viewpager,viewp;
    private int vie;
    private TextView fabi,jiaoyi;
    private View v1,v2;
    private LinearLayout tit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_wodezichan);
        initView();
        refreshShow();
        setStatusBar();
    }

    public void initView() {

        tit = (LinearLayout) findViewById(R.id.tit);
        tit.setBackgroundResource(R.color.main_blue1);
        viewp = (ViewPager) findViewById(R.id.viewp);
        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        fabi=(TextView)findViewById(R.id.fabi);
        fabi.setOnClickListener(this);
        jiaoyi=(TextView)findViewById(R.id.jiaoyi);
        jiaoyi.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> list = new ArrayList<Fragment>();
        E_ZCA eZca = new E_ZCA();
        E_ZCB eZcb = new E_ZCB();
        list.add(eZca);
        list.add(eZcb);
      //  list.add(eZcc);
        BangDanAdapter adapter = new BangDanAdapter(fm, list);
        viewpager.setAdapter(adapter);
        FragmentManager fm1 = getSupportFragmentManager();
        List<Fragment> list1 = new ArrayList<Fragment>();
        E_ZCAA eZcaa = new E_ZCAA();
        E_ZCBB eZcbb = new E_ZCBB();
        list1.add(eZcaa);
        list1.add(eZcbb);
        BangDanAdapter adapter1 = new BangDanAdapter(fm1, list1);
        viewp.setAdapter(adapter1);
        viewp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int ve, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                StartInt1(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int ve, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                StartInt(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void StartInt(int i){
        switch (i) {
            case 0:
                viewp.setCurrentItem(0);
                tit.setBackgroundResource(R.color.main_blue1);
                break;
            case 1:
                viewp.setCurrentItem(1);
                tit.setBackgroundResource(R.color.text_blue);

                break;
            case 2:
                viewp.setCurrentItem(2);

                break;
            case 3:
                viewp.setCurrentItem(3);

                break;

            default:
                break;
        }
    }
    private void StartInt1(int i){
        switch (i) {
            case 0:
                 viewpager .setCurrentItem(0);
                jiaoyi.setTextColor(getResources().getColor(R.color.blue));
                fabi.setTextColor(getResources().getColor(R.color.black));
                v1.setBackgroundResource(R.color.blue);
                v2.setBackgroundResource(R.color.transparent);

                break;
            case 1:
                viewpager .setCurrentItem(1);
                fabi.setTextColor(getResources().getColor(R.color.blue));
                jiaoyi.setTextColor(getResources().getColor(R.color.black));
                v2.setBackgroundResource(R.color.blue);
                v1.setBackgroundResource(R.color.transparent);
                break;
            case 2:
                viewpager .setCurrentItem(2);

                break;
            case 3:
                viewpager .setCurrentItem(3);

                break;

            default:
                break;
        }
    }

    public void back(View v) {
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jiaoyi:
                viewpager .setCurrentItem(0);
                jiaoyi.setTextColor(getResources().getColor(R.color.blue));
                fabi.setTextColor(getResources().getColor(R.color.black));
                v1.setBackgroundResource(R.color.blue);
                v2.setBackgroundResource(R.color.transparent);
                break;
            case R.id.fabi:
                fabi.setTextColor(getResources().getColor(R.color.blue));
                jiaoyi.setTextColor(getResources().getColor(R.color.black));
                v2.setBackgroundResource(R.color.blue);
                v1.setBackgroundResource(R.color.transparent);
                viewpager .setCurrentItem(1);
                break;
                default:
                    break;
        }
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (true) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.transparentblack));
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && false) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void  refreshShow() {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(E_WodeZiChan.this).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.URLURLrefresh();
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {

                    return;
                }
                Log.e("dsfdsf","dsfdfs"+response.body().getMessage() );
            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("dsfdsf","dsfdfs"+t.toString() );
            }
        });
    }
}

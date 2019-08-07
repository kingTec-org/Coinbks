package com.sengmei.meililian.Activity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.sengmei.RetrofitHttps.Beans.XiangQingBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.BaseActivity;
import com.sengmei.meililian.Utils.StringUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuangGaoActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,name,cont;
    private WebView webview;
    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.yinsixieyiactivity);
        String str=getIntent().getStringExtra("titles");
        String Ids=getIntent().getStringExtra("Ids");
        YinSiShow(Ids);
    }

    @Override
    public void initViews() {
        title=(TextView)findViewById(R.id.title);
        name=(TextView)findViewById(R.id.name);
        cont=(TextView)findViewById(R.id.cont);
        webview=(WebView)findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                mWebview.loadUrl("javascript:function getSub(){alert(\"Welcome\");" + "document.forms[0].submit();};getSub();");
                webview.loadUrl("javascript:function getSub(){" +
                        "document.getElementsByTagName('body')[0].style.background='#501C1734'" +
                        "};getSub();");

            }
        });
    }

    @Override
    public void ReinitViews() {

    }

    @Override
    public void initData() {

    }
    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                break;
        }
    }

    //我的
    private void YinSiShow(final String ss){
        GetDataFromServerInterface mFromServerInterface= GetDataFromServer.getInstance(GuangGaoActivity.this).getService();
        Call<XiangQingBean> indexdata=mFromServerInterface.get_detail(ss);
        indexdata.enqueue(new Callback<XiangQingBean>() {

            @Override
            public void onResponse(Call<XiangQingBean> call, Response<XiangQingBean> response) {
                if (response.body() == null) {
                    StringUtil.ToastShow(GuangGaoActivity.this,"请先登录");
                    return;
                }
                if (response.body().getType().equals("ok")) {
                    CharSequence charSequence;
                    if (!StringUtil.isBlank(response.body().getMessage().getTitle())){
                        title.setText(response.body().getMessage().getTitle());
                        name.setText(response.body().getMessage().getTitle());
                    }
                    if (!StringUtil.isBlank(response.body().getMessage().getContent())){
                        webview.loadData(getHtmlData(response.body().getMessage().getContent()), "text/html; charset=UTF-8", null);

                    } /*cont.setText(
                            Html.fromHtml(
                                    response.body().getMessage().getContent()));*/
/*
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        charSequence = Html.fromHtml(response.body().getMessage().getContent(),Html.FROM_HTML_MODE_LEGACY);
                    } else {
                        charSequence = Html.fromHtml(response.body().getMessage().getContent());
                    }
                    cont.setText(charSequence);*/
                }
            }

            @Override
            public void onFailure(Call<XiangQingBean> call, Throwable t) {
                Log.e("AAAAA","@@11="+t.getMessage().toString() );
                StringUtil.ToastShow(GuangGaoActivity.this,"请先登录");
            }
        });
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}body{background-color:#501C1734!important}</style></head>";
        return "<html>" + head + "<body style='background:#501C1734 !important'>" + bodyHTML + "</body></html>";
    }
}

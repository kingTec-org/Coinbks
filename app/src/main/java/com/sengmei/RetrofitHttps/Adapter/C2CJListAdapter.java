package com.sengmei.RetrofitHttps.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sengmei.RetrofitHttps.Beans.C2CJListBean;
import com.sengmei.RetrofitHttps.Beans.C2CListBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.Activity.C2C_XiangQing;
import com.sengmei.meililian.Activity.C2C_XiangQing1;
import com.sengmei.meililian.InterFaces.MenuChooseBack;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.CustomDialog;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class C2CJListAdapter extends BaseAdapter {
    private Context context;
    private List<C2CJListBean.dataBean> list;
    private TextView title, title1, danjia, xiane, nums, all_tv, next, jiaoyi, shuliang;
    private View va, va1;
    private EditText num;
    private String LeiXing = "money";
    private String DanJia, maxJia;
    private int point;
    private String MM = "全部买入";
    private CustomDialog di, d2;
    private MenuChooseBack menuChooseBack;
    private double Tota = 0;
    private EditText et_dialogContent;
    private String Str;

    public C2CJListAdapter(Context context, List<C2CJListBean.dataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setMenuChooseBack(MenuChooseBack menuChooseBack) {
        this.menuChooseBack = menuChooseBack;
    }

    @Override
    public int getCount() {
        if (list != null)

            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHodler holder;
        if (view == null) {
            holder = new ViewHodler();
            view = LayoutInflater.from(context).inflate(R.layout.c2cjlist_item, null);
            holder.titl = (LinearLayout) view.findViewById(R.id.titl);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.edu = (TextView) view.findViewById(R.id.edu);
            holder.name1 = (TextView) view.findViewById(R.id.name1);
            holder.num = (TextView) view.findViewById(R.id.num);
            holder.num1 = (TextView) view.findViewById(R.id.num1);
            holder.danjia = (TextView) view.findViewById(R.id.danjia);
            holder.bt = (TextView) view.findViewById(R.id.bt);
            holder.bt1 = (TextView) view.findViewById(R.id.bt1);
            holder.bt2 = (TextView) view.findViewById(R.id.bt2);
            holder.tu = (ImageView) view.findViewById(R.id.tu);
            view.setTag(holder);
        } else {

            holder = (ViewHodler) view.getTag();
        }


        final C2CJListBean.dataBean bean = list.get(i);
        holder.num1.setText(bean.getCreate_time());
        Log.e("取消订单", "bean.getStatus()=" + bean.getStatus());
        if (bean.getStatus().equals("1")) {
            holder.bt.setText("取消订单" + bean.getStatus());
            holder.bt.setVisibility(View.GONE);
            holder.bt1.setText("确认付款");
            if (bean.getType().equals("sell")) {
                holder.bt1.setVisibility(View.GONE);
            } else {
                holder.bt1.setVisibility(View.VISIBLE);
            }
        } else if (bean.getStatus().equals("2")) {
            holder.bt.setText("取消订单");
            holder.bt.setVisibility(View.GONE);
            holder.bt1.setVisibility(View.VISIBLE);
            if (bean.getType().equals("buy")) {
                holder.bt1.setText("已付款");
            } else {
                holder.bt1.setText("确认收款");
            }
        } else if (bean.getStatus().equals("3")) {
            holder.bt.setText("已完成");
            holder.bt1.setVisibility(View.GONE);
        } else if (bean.getStatus().equals("4")) {
            holder.bt.setVisibility(View.VISIBLE);
            holder.bt1.setVisibility(View.GONE);
            holder.bt.setText("已取消");
        }

        holder.num.setText("数量  " + bean.getNumber());
        holder.danjia.setText("￥  " + bean.getPrice());
        if (!StringUtil.isBlank(bean.getNumber())) {

            Tota = Double.valueOf(bean.getNumber());
        }

        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getStatus().equals("1")) {
                    Str = "quxiao";
                    point = i;
                    di = new CustomDialog(context, R.style.customDialog, R.layout.c2cfabudialog_item);
                    di.show();
                    dia(view);
                }
            }
        });
        holder.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getStatus().equals("1")) {
                    //StringUtil.ToastShow1(context,"bean.getStatus()="+bean.getStatus());
                    C2CPayShow(bean.getId());
                    return;
                }
                if (bean.getType().equals("sell") & bean.getStatus().equals("2")) {
                //    StringUtil.ToastShow1(context, bean.getType() + "bean.getStatus()=" + bean.getStatus());

                    Str = "shoukuan";
                    point = i;
                    di = new CustomDialog(context, R.style.customDialog, R.layout.c2cfabudialog_item);
                    di.show();
                    dia(view);
                }
            }
        });
        holder.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserBean.C2CFABU.equals("购买")) {
                    context.startActivity(new Intent(context, C2C_XiangQing.class).putExtra("Ids", bean.getId())
                            .putExtra("typs", bean.getType()));

                } else {
                    context.startActivity(new Intent(context, C2C_XiangQing1.class).putExtra("Ids", bean.getId())
                            .putExtra("typs", bean.getType()));

                }
            }
        });
        return view;
    }

    class ViewHodler {
        TextView name1, name, num, num1, danjia, edu;
        LinearLayout titl;
        ImageView tu;
        TextView bt, bt1, bt2;
    }

    public void dia(View v) {
        LayoutInflater in = LayoutInflater.from(context);
        View view = in.inflate(R.layout.c2cfabudialog_item, null);
        di.setContentView(view);
        et_dialogContent = (EditText) view.findViewById(R.id.et_dialogContent);
        et_dialogContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        TextView queren = (TextView) view.findViewById(R.id.queren);
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isBlank(et_dialogContent.getText().toString().trim())){
                    StringUtil.ToastShow1(context,"密码不能为空");
                    return;
                }
                if (Str.equals("quxiao")) {
                    QuXiaoShow(list.get(point).getId(), et_dialogContent.getText().toString().trim());
                }
                if (Str.equals("shoukuan")) {
                    ShouKuanShow(list.get(point).getId(), et_dialogContent.getText().toString().trim());
                }
                di.dismiss();
            }
        });
    }

    //取消订单
    private void QuXiaoShow(final String ids, final String ps) {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(context).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.URLC2Ccancel(ids, ps);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {
                    return;
                }
                Log.e("AAAAAss", "@@11=" + response.body().getType());
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                menuChooseBack.Choose("1");

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAAss", "@@11=" + call.toString() + "@@##" + t.getMessage().toString());
            }
        });
    }

    //确认收款
    private void ShouKuanShow(final String ids, final String ps) {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(context).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.URLC2Cconfirm(ids, ps);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {
                    return;
                }
                Log.e("AAAAAss", "@@11=" + response.body().getType());
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                menuChooseBack.Choose("1");

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAAss", "@@11=" + call.toString() + "@@##" + t.getMessage().toString());
            }
        });
    }

    //确认付款
    private void C2CPayShow(final String ss) {
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(context).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.URLC2Cpay(ss);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {
                    return;
                }
                Log.e("AAAAAss", "@@11=" + response.body().getType());
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                menuChooseBack.Choose("1");

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAAss", "@@11=" + call.toString() + "@@##" + t.getMessage().toString());
            }
        });
    }

}
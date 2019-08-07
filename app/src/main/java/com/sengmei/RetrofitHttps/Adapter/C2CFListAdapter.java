package com.sengmei.RetrofitHttps.Adapter;

import android.app.Dialog;
import android.content.Context;
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

import com.sengmei.RetrofitHttps.Beans.C2CFListBean;
import com.sengmei.RetrofitHttps.Beans.C2CListBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.GetDataFromServer;
import com.sengmei.RetrofitHttps.GetDataFromServerInterface;
import com.sengmei.kline.R;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class C2CFListAdapter extends BaseAdapter {
    private Context context;
    private List<C2CFListBean.dataBean> list;
    private TextView title, title1, danjia, xiane, nums, all_tv, next, jiaoyi, shuliang;
    private View va, va1;
    private EditText num;
    private String LeiXing = "money";
    private String DanJia, maxJia;
    private int point;
    private String MM="全部买入";
    private Dialog bottomDialog;
    private double Tota = 0;
    public C2CFListAdapter(Context context, List<C2CFListBean.dataBean> list) {
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.c2cflist_item, null);
            holder.titl = (LinearLayout) view.findViewById(R.id.titl);
            holder.num = (TextView) view.findViewById(R.id.num);
            holder.minnum = (TextView) view.findViewById(R.id.minnum);
            holder.danjia = (TextView) view.findViewById(R.id.danjia);
            holder.times = (TextView) view.findViewById(R.id.times);
            holder.tu = (ImageView) view.findViewById(R.id.tu);
            view.setTag(holder);
        } else {

            holder = (ViewHodler) view.getTag();
        }


        C2CFListBean.dataBean bean = list.get(i);
        holder.num.setText(bean.getNumber());
        holder.minnum.setText(bean.getMin_number());
        holder.danjia.setText(bean.getPrice());
        holder.times.setText(bean.getCreate_time());


        return view;
    }


    class ViewHodler {
        TextView  num,minnum, danjia,times;
        LinearLayout titl;
        ImageView tu;
    }



    //out
    private void GouMaioutShow(final String ss) {
        Log.e("AAAAAss", num.getText().toString().trim() + "=LeiXing=" + LeiXing);
        GetDataFromServerInterface mFromServerInterface = GetDataFromServer.getInstance(context).getService();
        Call<ZhuCeBean> indexdata = mFromServerInterface.getURLC2CGouMai(ss,  num.getText().toString().trim(),LeiXing);
        indexdata.enqueue(new Callback<ZhuCeBean>() {

            @Override
            public void onResponse(Call<ZhuCeBean> call, Response<ZhuCeBean> response) {
                if (response.body() == null) {
                    return;
                }
                Log.e("AAAAAss", "@@11=" + response.body().getType());
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ZhuCeBean> call, Throwable t) {
                Log.e("AAAAAss", "@@11=" + call.toString() + "@@##" + t.getMessage().toString());
            }
        });
    }

}
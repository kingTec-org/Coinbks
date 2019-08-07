package com.sengmei.RetrofitHttps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sengmei.RetrofitHttps.Beans.BJiaoYiBean;
import com.sengmei.kline.R;
import com.sengmei.meililian.Bean.ThreeListBean;
import com.sengmei.meililian.Utils.CustomDialog;

import java.util.List;

public class BJiaoYiButtonAdapter1 extends BaseAdapter {
    private Context context;
    private List<BJiaoYiBean.ObjectBean> list;
    private int NN;

    private CustomDialog customDialog;
    public BJiaoYiButtonAdapter1(Context context, List<BJiaoYiBean.ObjectBean> list) {
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

        ViewHodler holder;
        if (view == null) {
            holder = new ViewHodler();
            view = LayoutInflater.from(context).inflate(R.layout.threelist_item, null);
            holder.A1 = (TextView) view.findViewById(R.id.tv1);
            holder.A2 = (TextView) view.findViewById(R.id.tv2);
            holder.A3 = (TextView) view.findViewById(R.id.tv3);
            view.setTag(holder);
        } else {

            holder = (ViewHodler) view.getTag();
        }
        BJiaoYiBean.ObjectBean bean = list.get(i);
        holder.A1.setText(bean.getTime());
        holder.A2.setText(bean.getPrice());
        holder.A3.setText(bean.getNumber());
        return view;
    }


    class ViewHodler {
        TextView A1, A2, A3;


    }

}

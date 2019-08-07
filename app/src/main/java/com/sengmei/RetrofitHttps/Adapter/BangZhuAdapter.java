package com.sengmei.RetrofitHttps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sengmei.RetrofitHttps.Beans.LunBoBean;
import com.sengmei.kline.R;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.List;

public class BangZhuAdapter extends BaseAdapter {
    private Context context;
    private List<LunBoBean.Bean> list;

    public BangZhuAdapter(Context context, List<LunBoBean.Bean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.bangzhu_item, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.times = (TextView) view.findViewById(R.id.times);

            view.setTag(holder);
        } else {

            holder = (ViewHodler) view.getTag();
        }
        LunBoBean.Bean bean = list.get(i);
        holder.name.setText(bean.getTitle());
        holder.times.setText(StringUtil.date(bean.getTime()));
        notifyDataSetChanged();

        return view;
    }


    class ViewHodler {
        TextView name,times;


    }
}

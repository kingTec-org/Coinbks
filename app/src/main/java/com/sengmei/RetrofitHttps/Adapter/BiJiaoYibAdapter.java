package com.sengmei.RetrofitHttps.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sengmei.RetrofitHttps.Beans.BJiaoYiBean;
import com.sengmei.kline.R;
import com.sengmei.meililian.Utils.StringUtil;

import java.util.List;

public class BiJiaoYibAdapter extends BaseAdapter {
    private Context context;
    private List<BJiaoYiBean.ObjectBean> list;

    public BiJiaoYibAdapter(Context context, List<BJiaoYiBean.ObjectBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.threelisttop_item, null);
            holder.A1 = (TextView) view.findViewById(R.id.tv1);
            holder.A2 = (TextView) view.findViewById(R.id.tv2);
            holder.A3 = (TextView) view.findViewById(R.id.tv3);
            holder.A2.setTextColor(context.getResources().getColor(R.color.green));
            holder.textView = (TextView) view.findViewById(R.id.textView);
            holder.textView.setBackgroundResource(R.color.bjtb);

            view.setTag(holder);
        } else {

            holder = (ViewHodler) view.getTag();
        }
        BJiaoYiBean.ObjectBean bean = list.get(i);
        holder.A1.setText("" + (1 + i));
        holder.A2.setText(bean.getPrice());
        holder.A3.setText(bean.getNumber());
        notifyDataSetChanged();

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams)  holder.textView.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

        double aA=0;
        if (StringUtil.isBlank(bean.getPercentage())){

             aA = 0;
        }else {
             aA = Double.parseDouble(bean.getPercentage());
        }

        int AA= (int) (150*aA);
        linearParams.width = AA;// 控件的宽强制设成30

        linearParams.height = 40;// 控件的宽强制设成30
         holder.textView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        return view;
    }


    class ViewHodler {
        TextView A1, A2, A3;
        TextView textView;


    }
}

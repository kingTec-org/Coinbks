package com.sengmei.kline;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.sengmei.RetrofitHttps.Beans.BJiaoYiBean;
import com.sengmei.kline.bean.KLineBean;
import com.sengmei.kline.bean.KLineHeaderBean;
import com.sengmei.meililian.Bean.QuanZanBean;
import com.sengmei.meililian.Bean.ThreeListBean;
import com.sengmei.meililian.UserBean;
import com.sengmei.meililian.Utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/*import com.sengmei.kline.bean.KLineBean;
import com.sengmei.kline.bean.KLineHeaderBean;*/

public class KLineManager {

    private static final String TAG = "KLineManager";

    public static final String SOCKET_URL = "http://www.coinbkb.net";//2220
    public static final String EVENT_KLINE = "kline";
    public static final String EVENT_KLINE_HEADER = "daymarket";
    public static final String EVENT_KLINE_TRANSACTION_LIST = "market_trade";
    List<QuanZanBean.Databean> list=new ArrayList<>();
    public static volatile KLineManager mInstance;
    private Socket mSocket;

    public String mSymbolName = "";
    public String mCurrencyId, mLegalId;

    public Map<String, Integer> mTypeMap = new HashMap<>();

    private KLineManager() {
        initSocket();
        initKLineTypeMap();
    }

    public static KLineManager getInstance() {
        if (mInstance == null) {
            synchronized (KLineManager.class) {
                if (mInstance == null) {
                    mInstance = new KLineManager();
                }
            }
        }
        return mInstance;
    }

    private void initSocket() {
        try {
            mSocket = IO.socket(SOCKET_URL);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void initKLineTypeMap() {
        mTypeMap.put(KLineType.TYPE_1MINUTE, 0);
        mTypeMap.put(KLineType.TYPE_MINUTE_HOURS, 1);
        mTypeMap.put(KLineType.TYPE_5MINUTE, 2);
        mTypeMap.put(KLineType.TYPE_30MINUTE, 3);
        mTypeMap.put(KLineType.TYPE_1HOURS, 4);
        mTypeMap.put(KLineType.TYPE_1DAY, 5);
        mTypeMap.put(KLineType.TYPE_1WEEK, 6);
        mTypeMap.put(KLineType.TYPE_1MONTH, 7);
    }

    public void loadKLineData(String symbol, final KLineDataCallback callback) {
        judgeSocketStatus();

        mSocket.emit("Login", "123");
        mSocket.on(EVENT_KLINE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args.length <= 0) {
                    return;
                }
                Log.e(TAG, "KLine: " + args[0]);

                String jsonStr = args[0].toString();

                try {
                    KLineBean kLineBean = new Gson().fromJson(jsonStr, KLineBean.class);

                    if (kLineBean == null) {
                        return;
                    }

                    if (TextUtils.isEmpty(mSymbolName) || !mSymbolName.equals(kLineBean.getSymbol())) {
                        return;
                    }
                    String kLineBeanSybmol = kLineBean.getSymbol();
                    Log.e(TAG, "kLineBeanSybmol: ！！！" + kLineBeanSybmol);
                    Log.e(TAG, "kLineBeanSybmol: ！！！" + kLineBean);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Close", kLineBean.getClose());
                    jsonObject.put("Date", StringUtil.date(String.valueOf(kLineBean.getTime())));
                    jsonObject.put("Time", kLineBean.getTime());
                    jsonObject.put("High", kLineBean.getHigh());
                    jsonObject.put("Low", kLineBean.getLow());
                    jsonObject.put("Open", kLineBean.getOpen());
                    jsonObject.put("Volume", kLineBean.getVolume());

                    dealKLineData(kLineBean, jsonObject, callback);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Exception: " + e.getMessage());
                }

            }
        });
    }

    private synchronized void dealKLineData(KLineBean kLineBean, JSONObject jsonObject, KLineDataCallback callback) throws JSONException {
        switch (kLineBean.getPeriod()) {
            case KLineType.TYPE_1MINUTE:
                JSONObject lastObj = getLastData(UserBean.jsonArray);

                if (lastObj != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj.get("Time")));
                    long dateMinute = StringUtil.getDateMinute(timeStr);
                    long currentMinute = StringUtil.getDateMinute(kLineBean.getTime());

                    Log.e(TAG, "dateMinute: " + dateMinute + ", currentMinute: " + currentMinute);
                    if (currentMinute - dateMinute <= 0) {
                        updateLastData(UserBean.jsonArray, jsonObject);
                    } else {
                        UserBean.jsonArray.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray.put(jsonObject);
                }
                break;
            case KLineType.TYPE_5MINUTE:
                JSONObject lastObj5 = getLastData(UserBean.jsonArray5);

                if (lastObj5 != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj5.get("Time")));
                    long dateMinute = StringUtil.getDateMinute(timeStr);

                    int minute = (int) (dateMinute / 5 * 5);

                    long currentMinute = StringUtil.getDateMinute(kLineBean.getTime());

                    Log.e(TAG, "dateMinute: " + dateMinute + ", currentMinute: " + currentMinute);
                    if (currentMinute - minute < 5) {
                        updateLastData(UserBean.jsonArray5, jsonObject);
                    } else {
                        UserBean.jsonArray5.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray5.put(jsonObject);
                }
                break;
            case KLineType.TYPE_30MINUTE:
                JSONObject lastObj30 = getLastData(UserBean.jsonArray30);

                if (lastObj30 != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj30.get("Time")));
                    long dateMinute = StringUtil.getDateMinute(timeStr);

                    int minute = (int) (dateMinute / 30 * 30);

                    long currentMinute = StringUtil.getDateMinute(kLineBean.getTime());

                    Log.e(TAG, "dateMinute: " + dateMinute + ", currentMinute: " + currentMinute);
                    if (currentMinute - minute < 30) {
                        updateLastData(UserBean.jsonArray30, jsonObject);
                    } else {
                        UserBean.jsonArray30.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray30.put(jsonObject);
                }
                break;
            case KLineType.TYPE_1HOURS:
                JSONObject lastObj1h = getLastData(UserBean.jsonArray1h);

                if (lastObj1h != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj1h.get("Time")));
                    long dateMinute = StringUtil.getDateMinute(timeStr);

                    int minute = (int) (dateMinute / 60 * 60);

                    long currentMinute = StringUtil.getDateMinute(kLineBean.getTime());

                    Log.e(TAG, "dateMinute: " + dateMinute + ", currentMinute: " + currentMinute);
                    if (currentMinute - minute < 60) {
                        updateLastData(UserBean.jsonArray1h, jsonObject);
                    } else {
                        UserBean.jsonArray1h.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray1h.put(jsonObject);
                }
                break;
            case KLineType.TYPE_1DAY:
                JSONObject lastObj1d = getLastData(UserBean.jsonArray1d);

                if (lastObj1d != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj1d.get("Time")));
                    long dateHours = StringUtil.getDateHours(timeStr);

                    int hours = (int) (dateHours / 24 * 24);

                    long currentHours = StringUtil.getDateHours(kLineBean.getTime());

                    Log.e(TAG, "dateHours: " + dateHours + ", currentHours: " + currentHours);
                    if (currentHours - hours < 0) {
                        updateLastData(UserBean.jsonArray1d, jsonObject);
                    } else {
                        UserBean.jsonArray1d.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray1d.put(jsonObject);
                }
                break;
            case KLineType.TYPE_1WEEK:
                JSONObject lastObj1w = getLastData(UserBean.jsonArray1w);

                if (lastObj1w != null) {
                    long timeStr = Long.parseLong(String.valueOf(lastObj1w.get("Time")));
                    long dateHours = StringUtil.getDateHours(timeStr);

                    int hours = (int) (dateHours / (7 * 24) * (7 * 24));

                    long currentHours = StringUtil.getDateHours(kLineBean.getTime());

                    Log.e(TAG, "dateHours: " + dateHours + ", currentHours: " + currentHours);
                    if (currentHours - hours < 0) {
                        updateLastData(UserBean.jsonArray1w, jsonObject);
                    } else {
                        UserBean.jsonArray1w.put(jsonObject);
                    }
                } else {
                    UserBean.jsonArray1w.put(jsonObject);
                }

                break;
            case KLineType.TYPE_1MONTH:
                UserBean.jsonArray1m.put(jsonObject);
                break;
        }

        if (callback != null) {
            callback.onSuccess(kLineBean.getPeriod());
        }
    }

    private void updateLastData(JSONArray jsonArray, JSONObject jsonObject) {
        if (jsonArray == null || jsonArray.length() <= 0) {
            return;
        }
        try {
            jsonArray.put(jsonArray.length() - 1, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getLastData(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() <= 0) {
            return null;
        }
        try {
            return (JSONObject) jsonArray.get(jsonArray.length() - 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadKLineHeaderData(final KLineHeaderDataCallback callback) {
        judgeSocketStatus();

        mSocket.emit("Login", "123");
        mSocket.on(EVENT_KLINE_HEADER, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args.length <= 0) {
                    return;
                }
                Log.e(TAG, "Header: " + args[0]);

                String jsonStr = args[0].toString();

                try {
                    KLineHeaderBean kLineHeaderBean = new Gson().fromJson(jsonStr, KLineHeaderBean.class);

                    if (kLineHeaderBean == null) {
                        return;
                    }

                    if (TextUtils.isEmpty(mCurrencyId) || !mCurrencyId.equals(String.valueOf(kLineHeaderBean.getCurrency_id()))) {
                        return;
                    }

                    if (TextUtils.isEmpty(mLegalId) || !mLegalId.equals(String.valueOf(kLineHeaderBean.getLegal_id()))) {
                        return;
                    }

                    if (callback != null) {
                        callback.onSuccess(kLineHeaderBean);
                    }

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
    }

    public void loadKLineTransactionList(final KLineTransactionDataCallback callback) {
        judgeSocketStatus();

        mSocket.emit("Login", "123");
        mSocket.on(EVENT_KLINE_TRANSACTION_LIST, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args.length <= 0) {
                    return;
                }
                Log.e(TAG, "Transaction: " + args[0]);

                String jsonStr = args[0].toString();

                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    int legal_id = jsonObject.getInt("legal_id");
                    int currency_id = jsonObject.getInt("currency_id");
                    String transactionJson = jsonObject.getString("data");

                    Log.e(TAG, "currency_id: " + currency_id + ", legal_id: " + legal_id);
                    Log.e(TAG, "mCurrencyId: " + mCurrencyId + ", mLegalId: " + mLegalId);

                    if (TextUtils.isEmpty(mCurrencyId) || !mCurrencyId.equals(String.valueOf(currency_id))) {
                        return;
                    }

                    if (TextUtils.isEmpty(mLegalId) || !mLegalId.equals(String.valueOf(legal_id))) {
                        return;
                    }
                    JSONArray jsonArray=new JSONArray(transactionJson);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject obje = jsonArray.getJSONObject(i);
                        String price = obje.getString("price");
                        double amount = obje.getDouble("amount");
                        String ts = obje.getString("ts");
                        // aaa.setText(price+number+account_number);
                        QuanZanBean.Databean buttonBean = new QuanZanBean.Databean();
                        String str=StringUtil.date1(ts);
                        buttonBean.setTs(str);
                        buttonBean.setPrice(price);
                        buttonBean.setAmount(""+amount);
                        list.add(buttonBean);

                    }
                    if (list != null && callback != null) {

                        callback.onSuccess(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Transaction exception: " + e.getMessage());
                }
            }
        });
    }

    private void judgeSocketStatus() {
        if (mSocket == null) {
            initSocket();
        }
        if (mSocket != null && !mSocket.connected()) {
            mSocket.connect();
        }
    }

    public void disconnect() {
        if (mSocket != null && mSocket.connected()) {
            mSocket.disconnect();
        }
        mSocket = null;
    }

    public static class KLineType {
        public static final String TYPE_1MINUTE = "1min";
        public static final String TYPE_MINUTE_HOURS = "--";
        public static final String TYPE_5MINUTE = "5min";
        public static final String TYPE_30MINUTE = "30min";
        public static final String TYPE_1HOURS = "60min";
        public static final String TYPE_1DAY = "1day";
        public static final String TYPE_1WEEK = "1week";
        public static final String TYPE_1MONTH = "1mon";
    }

    public interface KLineDataCallback {

        void onSuccess(String kLineType);

    }

    public interface KLineHeaderDataCallback {

        void onSuccess(KLineHeaderBean kLineHeaderBean);

    }

    public interface KLineTransactionDataCallback {

        void onSuccess(List<QuanZanBean.Databean> list);

    }

}

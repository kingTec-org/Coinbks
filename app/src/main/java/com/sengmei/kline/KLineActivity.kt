package com.sengmei.kline

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import com.sengmei.Chating.DataHelper
import com.sengmei.Chating.KLineChartAdapter
import com.sengmei.Chating.KLineEntity
import com.sengmei.Chating.draw.Status
import com.sengmei.Chating.formatter.DateFormatter
import com.sengmei.RetrofitHttps.Adapter.BJiaoYiButtonAdapter
import com.sengmei.RetrofitHttps.Beans.BJiaoYiBean
import com.sengmei.kline.bean.KLineHeaderBean
import com.sengmei.meililian.Activity.E_ShouJiYanZheng
import com.sengmei.meililian.Bean.DataBean
import com.sengmei.meililian.Bean.QuanZanBean
import com.sengmei.meililian.Fragment.A_FragmentBiJiaoYi
import com.sengmei.meililian.MainActivity
import com.sengmei.meililian.UserBean
import com.sengmei.meililian.Utils.KTititlesBean
import com.sengmei.meililian.Utils.StringUtil
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.textColor
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*
import kotlin.math.log

class KLineActivity : AppCompatActivity() {

    private val TAG = "KLineActivity"

    private var nn = 1
    private lateinit var datas: MutableList<KLineEntity>

    private val adapter by lazy { KLineChartAdapter() }

    private val subTexts: ArrayList<TextView> by lazy { arrayListOf(macdText, kdjText, rsiText, wrText) }
    // 主图指标下标
    private var subIndex = 0
    // 副图指标下标
    private var mainIndex = -1
    private var DD: Int = 0

    private lateinit var mAdapter: BJiaoYiButtonAdapter
    private var mList: ArrayList<QuanZanBean.Databean> = ArrayList()

    var mStartVideoHandler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initData()
        kLineChartView.adapter = adapter
        kLineChartView.dateTimeFormatter = DateFormatter()
        kLineChartView.setGridRows(4)
        kLineChartView.setGridColumns(4)
        initListener()
        //   SSS()

        loadKLineData()
    }

    // TODO --------------start----------------------
    private fun loadKLineData() {
        UserBean.loadKLine1minData(this@KLineActivity, KLineManager.getInstance().mSymbolName) {
            runOnUiThread {
                initData()
            }
        }
        val biJiaoYiAdapter = BJiaoYiButtonAdapter(this@KLineActivity,UserBean.Hlistbt)
        listv.adapter = biJiaoYiAdapter

        KLineManager.getInstance().loadKLineData("") {
            refreshKLineView(it)
        }

        KLineManager.getInstance().loadKLineHeaderData {
            refreshHeaderView(it)
        }

        KLineManager.getInstance().loadKLineTransactionList {
            refreshTransactionListView(it)
        }

    }

    private fun refreshKLineView(klineType: String) {
        if (DD != KLineManager.getInstance().mTypeMap[klineType]) {
            return
        }
        datas = when (klineType) {
            KLineManager.KLineType.TYPE_1MINUTE ->
                DataRequest.getALL(this@KLineActivity)
            KLineManager.KLineType.TYPE_MINUTE_HOURS ->
                DataRequest.getALL0(this@KLineActivity)
            KLineManager.KLineType.TYPE_5MINUTE ->
                DataRequest.getALL5m(this@KLineActivity)
            KLineManager.KLineType.TYPE_30MINUTE ->
                DataRequest.getALL30(this@KLineActivity)
            KLineManager.KLineType.TYPE_1HOURS ->
                DataRequest.getALL1h(this@KLineActivity)
            KLineManager.KLineType.TYPE_1DAY ->
                DataRequest.getALL1d(this@KLineActivity)
            KLineManager.KLineType.TYPE_1WEEK ->
                DataRequest.getALL1w(this@KLineActivity)
            KLineManager.KLineType.TYPE_1MONTH ->
                DataRequest.getALL1m(this@KLineActivity)
            else -> {
                DataRequest.getALL(this@KLineActivity)
            }
        }

        DataHelper.calculate(datas)
        runOnUiThread {
            adapter.addFooterData(datas)
            adapter.notifyDataSetChanged()
        }

    }

    private fun refreshHeaderView(bean: KLineHeaderBean) {
        runOnUiThread {
            Log.e("KKKKKKK=","str="+bean)

            new_price.text = bean.now_price
            change.text = String.format("%.2f",StringUtil.strToDouble(bean.change))+"%"
            cnychange.text = "≈"+StringUtil.strToDouble( bean.now_price)*UserBean.CNY_RETA+"CNY"
            high.text = bean.high
            low.text = bean.low
            volume.text = bean.volume.toString()

        }
    }

    private fun refreshTransactionListView(list: MutableList<QuanZanBean.Databean>) {
        runOnUiThread {
            mList.clear()
            mList.addAll(list)

            Collections.reverse(mList)
            mAdapter = BJiaoYiButtonAdapter(this@KLineActivity, mList)
            listv.adapter = mAdapter
          /*  if (mAdapter == null) {
            } else {
                mAdapter.notifyDataSetChanged()
            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        KLineManager.getInstance().disconnect()
    }
    companion object {
        @JvmStatic
        fun open(context: Context) {
            var intent = Intent(context, KLineActivity::class.java)
            context.startActivity(intent)
        }
    }
    // TODO -----------------end-------------------

    private fun initData() {

        titles.setText(KTititlesBean.Titles)
        new_price.setText(KTititlesBean.Now_priceS)
        change.setText(KTititlesBean.Changes)
        cnychange.setText(KTititlesBean.CNYChanges)
        high.setText(KTititlesBean.Highs)
        low.setText(KTititlesBean.Lows)
        volume.setText(KTititlesBean.Volumes)

        if (!StringUtil.isBlank(KTititlesBean.Changes)) {
            val str = KTititlesBean.Changes.substring(0, 1)
            Log.e("SDSDSSSD111=","str="+str)
            if (str == "-") {
                new_price.setTextColor(resources.getColor(R.color.color_text_FireBrick))
                change.setTextColor(resources.getColor(R.color.color_text_FireBrick))
            } else {
                change.setTextColor(resources.getColor(R.color.green))
                new_price.setTextColor(resources.getColor(R.color.green))
            }

        } else {

            change.setTextColor(resources.getColor(R.color.green))
            new_price.setTextColor(resources.getColor(R.color.green))
        }
        mList.clear()
        // mList.addAll(A_FragmentBiJiaoYi.Hlistbt)
        mList.addAll(UserBean.Hlistbt)
        mAdapter = BJiaoYiButtonAdapter(this@KLineActivity, mList)
        listv.adapter = mAdapter
        kLineChartView.justShowLoading()
        doAsync {
            // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
            datas = DataRequest.getALL(this@KLineActivity)
            DataHelper.calculate(datas)
            runOnUiThread {
                adapter.addFooterData(datas)
                adapter.notifyDataSetChanged()
                kLineChartView.startAnimation()
                kLineChartView.refreshEnd()
                kLineChartView.refreshComplete()
            }
        }
    }
    private fun initListener() {
        maText.setOnClickListener {
            if (mainIndex != 0) {
                mainIndex = 0
                maText.textColor = Color.parseColor("#5a8de0")
                bollText.textColor = Color.WHITE
                kLineChartView.changeMainDrawType(Status.MA)
            }
        }
        bollText.setOnClickListener {
            if (mainIndex != 1) {
                mainIndex = 1
                bollText.textColor = Color.parseColor("#5a8de0")
                maText.textColor = Color.WHITE
                kLineChartView.changeMainDrawType(Status.BOLL)
            }
        }
        for ((index, text) in subTexts.withIndex()) {
            text.setOnClickListener {
                if (subIndex != index) {
                    if (subIndex != -1) {
                        subTexts[subIndex].textColor = Color.WHITE
                    }
                    subIndex = index
                    text.textColor = Color.parseColor("#5a8de0")
                    kLineChartView.setChildDraw(subIndex)
                }
            }
        }
        subHide.setOnClickListener {
            subTexts[subIndex].textColor = Color.WHITE
            subIndex = -1
            kLineChartView.hideChildDraw()
        }
        fenText.setOnClickListener {
            DD=1;
            fenText.textColor = Color.parseColor("#5a8de0")
            kText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(true)
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL0(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    //kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
        }
        kText.setOnClickListener {
            DD=0;
            kText.textColor = Color.parseColor("#5a8de0")
            fenText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    //kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
        }
        kText5m.setOnClickListener {
            DD=2;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL5m(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
            kText5m.textColor = Color.parseColor("#5a8de0")
            kText.textColor = Color.WHITE
            fenText.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }
        kText30.setOnClickListener {

            DD=3;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL30(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
            kText30.textColor = Color.parseColor("#5a8de0")
            kText.textColor = Color.WHITE
            fenText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }
        kText1h.setOnClickListener {
            DD=4;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL1h(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }

            kText1h.textColor = Color.parseColor("#5a8de0")
            fenText.textColor = Color.WHITE
            kText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }
        kText1d.setOnClickListener {
            DD=5;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL1d(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
            kText1d.textColor = Color.parseColor("#5a8de0")
            fenText.textColor = Color.WHITE
            kText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }
        kText1w.setOnClickListener {
            DD=6;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL1w(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
            kText1w.textColor = Color.parseColor("#5a8de0")
            fenText.textColor = Color.WHITE
            kText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kText1m.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }
        kText1m.setOnClickListener {
            DD=7;
            kLineChartView.justShowLoading()
            doAsync {
                // datas = DataRequest.getALL(this@KLineActivity).subList(0, 10)
                datas = DataRequest.getALL1m(this@KLineActivity)
                DataHelper.calculate(datas)
                runOnUiThread {
                    adapter.addFooterData(datas)
                    adapter.notifyDataSetChanged()
                    // kLineChartView.startAnimation()
                    kLineChartView.refreshEnd()
                    kLineChartView.refreshComplete()
                }
            }
            kText1m.textColor = Color.parseColor("#5a8de0")
            fenText.textColor = Color.WHITE
            kText.textColor = Color.WHITE
            kText5m.textColor = Color.WHITE
            kText30.textColor = Color.WHITE
            kText1d.textColor = Color.WHITE
            kText1w.textColor = Color.WHITE
            kText1h.textColor = Color.WHITE
            kLineChartView.setMainDrawLine(false)
        }

        back.setOnClickListener {
            finish()
            UserBean.JiaoYIMM=1
        }
        guan.setOnClickListener {
            h1.setVisibility(VISIBLE);
            h2.setVisibility(GONE);
        }
        guan1.setOnClickListener {
            h1.setVisibility(VISIBLE);
            h2.setVisibility(GONE);
        }
        zhibiao.setOnClickListener {
            h1.setVisibility(GONE);
            h2.setVisibility(VISIBLE);
        }
        mairu.setOnClickListener {
            DataBean.MunuS = 1
            DataBean.MM = 0
            UserBean.JiaoYIMM=1
            finish()
        }
        maichu.setOnClickListener {
            DataBean.MunuS = 1
            DataBean.MM = 1
            UserBean.JiaoYIMM=1
            finish()
        }
        quanping.setOnClickListener {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                //切换竖屏
                this@KLineActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //toast("nnnn")
                lltitl.setVisibility(VISIBLE)
                ll.setVisibility(VISIBLE)
                llbt.setVisibility(VISIBLE)
                hengoing.setVisibility(GONE)
                quanping.setVisibility(VISIBLE)
                listv.setVisibility(VISIBLE)
                titl.setVisibility(VISIBLE)
            } else {
                //切换横屏
                this@KLineActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //toast("mmmm")
                lltitl.setVisibility(GONE)
                ll.setVisibility(GONE)
                llbt.setVisibility(GONE)
                hengoing.setVisibility(VISIBLE)
                quanping.setVisibility(GONE)
                listv.setVisibility(GONE)
                titl.setVisibility(GONE)
            }
            hengoing.setOnClickListener {
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    //切换竖屏
                    this@KLineActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //toast("nnnn")
                    lltitl.setVisibility(VISIBLE)
                    ll.setVisibility(VISIBLE)
                    llbt.setVisibility(VISIBLE)
                    hengoing.setVisibility(GONE)
                    quanping.setVisibility(VISIBLE)
                    listv.setVisibility(VISIBLE)
                    titl.setVisibility(VISIBLE)
                } else {
                    //切换横屏
                    this@KLineActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //toast("mmmm")
                    lltitl.setVisibility(GONE)
                    ll.setVisibility(GONE)
                    llbt.setVisibility(GONE)
                    hengoing.setVisibility(VISIBLE)
                    quanping.setVisibility(GONE)
                    listv.setVisibility(GONE)
                    titl.setVisibility(GONE)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
}

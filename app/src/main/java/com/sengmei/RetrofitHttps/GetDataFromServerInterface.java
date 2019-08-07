package com.sengmei.RetrofitHttps;

import com.alibaba.fastjson.JSONObject;
import com.sengmei.RetrofitHttps.Beans.AnQuanBean;
import com.sengmei.RetrofitHttps.Beans.BJiaoYiBean;
import com.sengmei.RetrofitHttps.Beans.C2CFListBean;
import com.sengmei.RetrofitHttps.Beans.C2CJListBean;
import com.sengmei.RetrofitHttps.Beans.C2CListBean;
import com.sengmei.RetrofitHttps.Beans.C2CTiTleBean;
import com.sengmei.RetrofitHttps.Beans.C2C_FaBuBean;
import com.sengmei.RetrofitHttps.Beans.C2C_QuXiaoFaBu_Bean;
import com.sengmei.RetrofitHttps.Beans.C2C_XiangQingBean;
import com.sengmei.RetrofitHttps.Beans.DanShowBean;
import com.sengmei.RetrofitHttps.Beans.DingDanJiLuBean;
import com.sengmei.RetrofitHttps.Beans.DingDanXiangQingBean;
import com.sengmei.RetrofitHttps.Beans.ErWeiMaBean;
import com.sengmei.RetrofitHttps.Beans.FaBiGouMaiBean;
import com.sengmei.RetrofitHttps.Beans.FaBiGuanLiBean;
import com.sengmei.RetrofitHttps.Beans.FaBiTiTleBean;
import com.sengmei.RetrofitHttps.Beans.FaBiTiTledizhiBean;
import com.sengmei.RetrofitHttps.Beans.FaBiXiaLa;
import com.sengmei.RetrofitHttps.Beans.FormBTCBean;
import com.sengmei.RetrofitHttps.Beans.GeRenZhongXinBean;
import com.sengmei.RetrofitHttps.Beans.GengXinBean;
import com.sengmei.RetrofitHttps.Beans.GetMaiBean;
import com.sengmei.RetrofitHttps.Beans.HangQingBean;
import com.sengmei.RetrofitHttps.Beans.HangQingZiXuanBean;
import com.sengmei.RetrofitHttps.Beans.HuaZhuanBean;
import com.sengmei.RetrofitHttps.Beans.HuanZhuanBean;
import com.sengmei.RetrofitHttps.Beans.HuoQuShouKuanBean;
import com.sengmei.RetrofitHttps.Beans.JYLBBean;
import com.sengmei.RetrofitHttps.Beans.KLineBean;
import com.sengmei.RetrofitHttps.Beans.KTitleBean;
import com.sengmei.RetrofitHttps.Beans.KeYong1Bean;
import com.sengmei.RetrofitHttps.Beans.KeYongBean;
import com.sengmei.RetrofitHttps.Beans.LunBoBean;
import com.sengmei.RetrofitHttps.Beans.LunBoTopBean;
import com.sengmei.RetrofitHttps.Beans.PanDuan;
import com.sengmei.RetrofitHttps.Beans.ShangPuBean;
import com.sengmei.RetrofitHttps.Beans.SuoCBean;
import com.sengmei.RetrofitHttps.Beans.TiBiBean;
import com.sengmei.RetrofitHttps.Beans.TiBiXiaLaBean;
import com.sengmei.RetrofitHttps.Beans.TiBidiZhiBean;
import com.sengmei.RetrofitHttps.Beans.WDJY1Bean;
import com.sengmei.RetrofitHttps.Beans.WDJYBean;
import com.sengmei.RetrofitHttps.Beans.WoDeShangPuBean;
import com.sengmei.RetrofitHttps.Beans.WoDeShangPuListBean;
import com.sengmei.RetrofitHttps.Beans.WoDeTangGuoBean;
import com.sengmei.RetrofitHttps.Beans.XiangQingBean;
import com.sengmei.RetrofitHttps.Beans.YinSiBean;
import com.sengmei.RetrofitHttps.Beans.ZhangFuBean;
import com.sengmei.RetrofitHttps.Beans.ZhuCeBean;
import com.sengmei.RetrofitHttps.Beans.ZhuanHuanBean;
import com.sengmei.RetrofitHttps.Beans.ZiChanBean;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GetDataFromServerInterface {

    /**
     * 登录数据
     */
    @POST(Urls.Login)
    Call<ZhuCeBean> Login(@Query("user_string") String name, @Query("password") String password,
                          @Query("type") String type);

    /**
     * 注册2页面注册密码账号
     */
    @POST(Urls.URLregister)
    Call<ZhuCeBean> getZhuCe(@Query("user_string") String name, @Query("password") String pass,
                             @Query("re_password") String re_password, @Query("code") String code,
                             @Query("extension_code") String extension_code, @Query("type") String types
            , @Query("nationality") String nationality);

    /**
     * 注册1页面获取手机验证码数据
     */
    @POST(Urls.URLsms_send)
    Call<ZhuCeBean> getyanzhengma(@Query("user_string") String name,@Query("type") String type);

    /**
     * 注册1页面获邮箱取验证码数据
     */
    @POST(Urls.URLsms_mail)
    Call<ZhuCeBean> getyouxiangyanzheng(@Query("user_string") String name,@Query("type") String type);

    /**
     * 注册1页面获邮箱取验证码数据
     */
    @POST(Urls.URLcheck_mail)
    Call<ZhuCeBean> youxiangyanzheng(@Query("email_code") String email_code,@Query("number") String number);

    /**
     * 手机验证码判断
     */
    @POST(Urls.URLcheck)
    Call<ZhuCeBean> shoujiyanzheng(@Query("mobile_code") String email_code);

    /**
     * 绑定邮箱验证
     */
    @POST(Urls.URLemail)
    Call<ZhuCeBean> get_email(@Query("email") String email, @Query("code") String code);

    /**
     * 绑定手机证
     */
    @POST(Urls.URLmobile)
    Call<ZhuCeBean> get_mobile(@Query("mobile") String email, @Query("code") String code);

    /**
     * 注册1页面获邮箱取验证码数据
     */
    @GET(Urls.URLlegal_list)
    Call<HuaZhuanBean> HUaZhuan();

    @GET(Urls.URLlegal_list)
    Call<FaBiTiTleBean> getFaBiTiTle();

    @GET(Urls.URLlegal_list)
    Call<FaBiTiTleBean> getFaBiTiTle1();
    @POST(Urls.URL_wallet)
    Call<HuanZhuanBean> getURL_walletHZ(@Query("type") String type, @Query("currency") String currency);

    /**
     * 版本号
     */
    @GET(Urls.URLversion)
    Call<GengXinBean> URLversion();
    /**
     *  刷新法币
     */
    @GET(Urls.URLrefresh)
    Call<ZhuCeBean> URLURLrefresh();
    /**
     * 个人中心 发布工单
     */
    @POST(Urls.addGongDan)
    Call<IndexData> getaddGongDan(@Query("content") String name);

    /**
     * 个人中心 工单列表
     */
    @POST(Urls.GongDan)
    Call<DanShowBean> GongDan();

    /**
     * 添加提币地址
     */
    @POST(Urls.URLTBaddaddress)
    Call<IndexData> getTBdz(@Query("currency_id") String currency_id, @Query("address") String address, @Query("notes") String notes);

    /**
     * 删除提币地址
     */
    @POST(Urls.URLTBdeladdress)
    Call<IndexData> getTBdzd(@Query("address_id") String currency_id);

    /**
     * 个人中心
     **/
    @GET(Urls.URLcenter)
    Call<GeRenZhongXinBean> gernzx();
    /**
     * 个人中心
     **/
    @GET(Urls.URLcenter)
    Call<JSONObject> gernzx1();

    /**
     * 退出登录
     **/
    @GET(Urls.URLlogout)
    Call<ZhuCeBean> logout();

    /**
     * 忘记密码
     */
    @POST(Urls.URLforget)
    Call<ZhuCeBean> Loginforget(@Query("account") String account, @Query("password") String password,
                                @Query("repassword") String repassword, @Query("code") String code);

    /**
     * 划转
     */
    @POST(Urls.URLchange)
    Call<ZhuCeBean> Getchange(@Query("number") String number, @Query("currency_id") String currency_id,
                              @Query("type") String type);

    /**
     * 添加收款地址
     */
    @POST(Urls.URLcash_save)
    Call<IndexData> getTBSKFS(@Query("real_name") String real_name, @Query("bank_name") String bank_name
            , @Query("bank_account") String bank_account, @Query("alipay_account") String alipay_account,
                              @Query("wechat_nickname") String wechat_nickname, @Query("wechat_account") String wechat_account);

    /**
     * 添加收款地址
     */
    @POST(Urls.URLcash_info)
    Call<HuoQuShouKuanBean> getTBSKFSinfo();

    /**
     * 提币
     */
    @POST(Urls.URLget_info)
    Call<TiBiBean> get_info(@Query("currency") String currency);

    /**
     * 提币提交
     */
    @POST(Urls.URLget_out)
    Call<ZhuCeBean> get_out(@Query("currency") String currency, @Query("number") String number,
                            @Query("rate") String rate, @Query("address") String address, @Query("password") String password);

    /**
     * 广告详情
     */
    @POST(Urls.URLget_detail)
    Call<XiangQingBean> get_detail(@Query("id") String id);


    /**
     * 购买进
     */
    @GET(Urls.URLinfo)
    Call<FaBiGouMaiBean> getMaiinfo(@Query("id") String id);

    /**
     * 购买提交 判断
     */
    @POST(Urls.URLout)
    Call<PanDuan> getMaiout(@Query("id") String id, @Query("means") String means, @Query("value") String value);
    @POST(Urls.URLout)
    Call<ZhuCeBean> getMaiout1(@Query("id") String id, @Query("means") String means, @Query("value") String value);
    @POST(Urls.URLout)
    Call<GetMaiBean> getMaiout2(@Query("id") String id, @Query("means") String means, @Query("value") String value);


    /**
     * 我的资产
     */
    @POST(Urls.URLlist)
    Call<ZiChanBean> getZiChan();
    /**
     * 我的资产
     */
    @POST(Urls.URLlist)
    Call<JSONObject> getZiChan1();

    /**
     * 轮播下的广告
     */
    @POST(Urls.URLnews)
    Call<LunBoBean> getnews(@Query("c_id") String c_id);
    /**
     * 轮播下的广告
     @FormUrlEncoded
     */
    @POST(Urls.URLnews)
    Call<JSONObject> getnews1(@Query("c_id") String c_id);
    @POST(Urls.URLnews)
    Call<LunBoBean> getnewsBangZhu();
    /**
     * 轮播
     */
    @GET(Urls.URLappPic)
    Call<LunBoTopBean> getappPic();

    /**
     * 行情
     */
    @GET(Urls.URLquotation)
    Call<HangQingBean> getquotation();
    /**
     * 行情自选区
     */
    @GET(Urls.URLquotationlist)
    Call<HangQingZiXuanBean> getZxuanQu();

    /**
     * 行情收藏判断
     */
    @GET(Urls.URLoutslegalCurrency)
    Call<ZhuCeBean> legalCurrency(@Query("legal_id") String legal_id,@Query("currency_id") String currency_id);

    /**
     * 行情自选区添加
     */
    @POST(Urls.URLoutadd)
    Call<ZhuCeBean> legalCurrencyadd(@Query("id") String id);
    /**
     * 行情自选区添加
     */
    @POST(Urls.URLoutdelete)
    Call<ZhuCeBean> legalCurrencydelete(@Query("id") String id);

    /**
     * 行情
     */
    @GET(Urls.URLplatformBTC1)
    Call<FormBTCBean> getplatformBTC1(@Query("type") String type, @Query("page") String page, @Query("currency_id") String currency_id);

    /**
     * C2C购买行情
     */
    @POST(Urls.URLC2CGouMaiList)
    Call<C2CListBean> getC2CGouMaiList(@Query("type") String type, @Query("currency_id") String currency_id);

    /**
     * 币交易买入
     */
    @POST(Urls.URKLin)
    Call<ZhuCeBean> getKLin(@QueryMap HashMap<String, String> map);

    /**
     * 币交易买入
     */
    @POST(Urls.URKLout)
    Call<ZhuCeBean> getKLout(@QueryMap HashMap<String, String> map);

    /**
     * 账户交易记录
     */
    @POST(Urls.UELlegal_log)
    Call<JYLBBean> getlegal_log(@Query("currency") String currency, @Query("type") String type);


    /**
     * 账户交易交易已完成
     */
    @POST(Urls.URLcomplete)
    Call<WDJY1Bean> getcomplete();

    /**
     * 账户交易交易正在买
     */
    @POST(Urls.URLins)
    Call<WDJYBean> getLins();

    /**
     * 账户交易交易正在卖
     */
    @POST(Urls.URLouts)
    Call<WDJYBean> getouts();

    /**
     * 账户交易交易 卖买删除
     */
    @POST(Urls.URLdels)
    Call<ZhuCeBean> getdels(@Query("id") String id, @Query("type") String type);
    /**
     * 账户交易交易 卖买批量删除
     */
    @POST(Urls.URLdels1)
    Call<ZhuCeBean> getdels1(@Query("id") String id, @Query("type") String type);

    /**
     * 订单详情 与 筛选
     */
    @GET(Urls.URLuser_deal)
    Call<FaBiGuanLiBean> getuser_deal();

    @GET(Urls.URLuser_deal)
    Call<FaBiGuanLiBean> getuser_deal1(@Query("is_sure") String is_sure, @Query("type") String type);

    /**
     * 添加订单详情 与 筛选
     */
    @GET(Urls.URLsetaccount)
    Call<FaBiGuanLiBean> getsetaccount(@Query("is_sure") String is_sure, @Query("type") String type);

    /**
     * 隐私信息
     */
    @GET(Urls.UTLprivate)
    Call<YinSiBean> getprivate();
    /**
     * 注册隐私协议信息
     */
    @GET(Urls.UTLprivate1)
    Call<YinSiBean> getprivate1();

    /**
     * 提币地址列表信息
     */
    @GET(Urls.URLcurrencylist)
    Call<FaBiTiTledizhiBean> getcurrencylist();

    /**
     * 涨幅
     */
    @GET(Urls.URLquotation_sort)
    Call<ZhangFuBean> getquotation_sort();

    /**
     * K线顶部
     */
    @POST(Urls.URLtoday_detail)
    Call<KTitleBean> getKdetail(@Query("currency_id") String currency_id, @Query("legal_id") String legal_id);

    /**
     * K线顶部
     */
    @POST(Urls.URLtoday_detail)
    Call<JSONObject> getKdetail1(@Query("currency_id") String currency_id, @Query("legal_id") String legal_id);

    /**
     * K线信息
     */
    @GET(Urls.UELK)
    Call<KLineBean> getK(@Query("symbol") String symbol, @Query("period") String period);


    /**
     * 法币订单待付款
     */
    @GET(Urls.URLlegal_deal)
    Call<DingDanXiangQingBean> getlegal_deal(@Query("id") String id);
    /**
     * 法币订单待付款 取消
     */
    @POST(Urls.URLlegal_dealquxiao)
    Call<ZhuCeBean> getlegal_dealquxiao(@Query("id") String id);
    /**
     * 商家确认收款
     */
    @POST(Urls.URLlegal_dealsure)
    Call<ZhuCeBean> getlegal_dealsure(@Query("id") String id,@Query("pay_password") String pay_password);
    /**
     * 法币订单待付款 确认付款
     */
    @POST(Urls.URLlegal_dealqueren)
    Call<ZhuCeBean> getlegal_dealqueren(@Query("id") String id);

    /**
     * 商铺信息 URLsellerinfo
     */
    @GET(Urls.URLsellerinfo)
    Call<ShangPuBean> getseller(@Query("id") String id, @Query("page") String page);

    /**
     * 我的商铺信息 URLsellerinfo
     */
    @GET(Urls.URLsellerinfo)
    Call<ShangPuBean> getseller1(@Query("id") String id, @Query("was_done") String was_done);

    /**
     * 我的店铺
     */
    @GET(Urls.URLseller)
    Call<WoDeShangPuBean> getseller();

    /**
     * 我的店铺详情
     */
    @GET(Urls.URLtrade)
    Call<WoDeShangPuListBean> gettrade(@Query("id") String id, @Query("type") String type, @Query("was_done") String was_done);

    /**
     * 我的店铺详情 撤回
     */
    @POST(Urls.URLsend)
    Call<ZhuCeBean> gettrade(@Query("id") String id);

    /**
     * 我的店铺详情 异常
     */
    @POST(Urls.URLerror)
    Call<ZhuCeBean> URLerror(@Query("id") String id);

    /**
     * 我的店铺详情 订单管理
     */
    @GET(Urls.URLlegal)
    Call<DingDanJiLuBean> getlegal(@Query("id") String id, @Query("page") String page);
     /**
     * 我的店铺详情 发布
     */
    @POST(Urls.URLlegal_send)
    Call<ZhuCeBean> getlegal_send(@Query("type") String type,@Query("way") String way,
                                  @Query("price") String price, @Query("total_number") String total_number,
                                  @Query("min_number") String min_number, @Query("currency_id") String currency_id);

    /**
     * 安全中心 换成个人中心
     */
   /* @POST(Urls.URLsafe_center)
    Call<AnQuanBean> getcenter();*/
    @GET(Urls.URLcenter)
    Call<AnQuanBean> getcenter();

    /**
     * 法币重置密码
     */
    @POST(Urls.URLsafe_update)
    Call<ZhuCeBean> getupdate(@Query("oldpassword") String oldpassword, @Query("password") String password, @Query("re_password") String re_password);

    /**
     * 法币设置密码
     */
    @POST(Urls.URLsetaccounts)
    Call<ZhuCeBean> getmima(@Query("password") String password, @Query("repassword") String repassword);
    /**
     * 首次进入币交易数据
     */
    @POST(Urls.URL_dels)
    Call<BJiaoYiBean> getURL_dels(@Query("legal_id") String legal_id, @Query("currency_id") String currency_id);
    /**
     * 买入可用
     */
    @POST(Urls.URL_wallet)
    Call<KeYongBean> getURL_wallet1(@Query("type") String type, @Query("currency") String currency);
    /**
     * 卖出可用
     */
    @POST(Urls.URL_wallet)
    Call<KeYongBean> getURL_wallet(@Query("type") String type, @Query("currency") String currency);
    /**
     * 提币
     */
    @POST(Urls.URLget_address)
    Call<TiBidiZhiBean> getURL_address(@Query("currency") String type);
    /**
     * 充币
     */
    @POST(Urls.URLaddress)
    Call<ZhuCeBean> getaddress(@Query("currency") String type);
    /**
     * 身份认证
     */
    @POST(Urls.URLreal_name)
    Call<ZhuCeBean> getURLupload(@QueryMap HashMap<String, String> map);
    /**
     * 上传头像
     */
    @Multipart
    @POST(Urls.URLupload)
    Call<ZhuCeBean> uploadImage(@Part MultipartBody.Part file);

    /**
     * 提币下拉地址
     */
    @POST(Urls.URLaddres)
    Call<TiBiXiaLaBean> getTiBiaddress(@Query("currency") String type);
    /**
     * C@C购买
     */
    @POST(Urls.URLC2CGouMai)
    Call<ZhuCeBean> getURLC2CGouMai(@Query("id") String id,@Query("number") String number,@Query("type") String type);
    /**
     * C@C我的交易
     */
    @POST(Urls.URLC2Cmy_ctoc)
    Call<C2CJListBean> getURLC2Cmy_ctoc(@Query("type") String type, @Query("currency_id") String currency_id);
    /**
     * C@C我的发布
     */
    @POST(Urls.URLC2Cmy_list)
    Call<C2CFListBean> getURLC2Cmy_list(@Query("type") String type, @Query("currency_id") String currency_id);
    /**
     * C@C发布选择
     */
    @GET(Urls.URLC2CHlist)
    Call<C2CTiTleBean> getURLC2Clist();


    /**
     * C2C横向标题
     */
    @GET(Urls.URLC2CHlist)
    Call<FaBiTiTleBean> getC2CHlist();
    /**
     * tgewm
     */
    @GET(Urls.URLuserinfo)
    Call<ErWeiMaBean> getURLuserinfo();
    /**
     * sc
     */
    @GET(Urls.URLmy_lock)
    Call<SuoCBean> getURLmy_lock();
    @GET(Urls.URLbalance)
    Call<ZhuCeBean> getURLbalance();
    @POST(Urls.URLlock)
    Call<ZhuCeBean> getURLlock(@Query("number") String number);

    /**
     * wk
     */
    @POST(Urls.UELlegal_log)
    Call<JYLBBean> getlegal_logwk(@Query("page") String page, @Query("type") String type);
    /**
     * wdtg
     */
    @GET(Urls.URLdetail)
    Call<JSONObject> getURLdetail();
    /**
     * tgdh
     */
    @GET(Urls.URLexchange)
    Call<JSONObject> getURLexchange();

    /**
     * C2C取消订单
     */
    @POST(Urls.URLC2Ccancel)
    Call<ZhuCeBean> URLC2Ccancel(@Query("id") String id,@Query("pay_password") String pay_password);
    /**
     * C2C取消fabu
     */
    @POST(Urls.URLC2Crevoke)
    Call<ZhuCeBean> URLC2CCrevoke(@Query("id") String id);
    /**
     * C2C取消发布详情
     */
    @POST(Urls.URLC2Cdetail)
    Call<C2C_QuXiaoFaBu_Bean> URLC2Ccanceldetail(@Query("id") String id);
    /**
     * C2C确认收款
     */
    @POST(Urls.URLC2Cconfirm)
    Call<ZhuCeBean> URLC2Cconfirm(@Query("id") String id,@Query("pay_password") String pay_password);
    /**
     * C2C确认付款
     */
    @POST(Urls.URLC2Cpay)
    Call<ZhuCeBean> URLC2Cpay(@Query("id") String id);
    /**
     * C2C详情
     */
    @POST(Urls.URLC2Corder_detail)
    Call<C2C_XiangQingBean> URLC2Corder_detail(@Query("id") String id);

    /**
     * 交易密码从新修改
     */
    @POST(Urls.URLupPaypassword)
    Call<ZhuCeBean> URLupPaypassword(@Query("account") String account,@Query("payPassword") String payPassword,
                                             @Query("repayPassword") String repayPassword,@Query("code") String code);

    /**
     * 我的店铺详情 发布
     */
    @POST(Urls.URLC2Cpublish)
    Call<ZhuCeBean> getpublish(@Query("type") String type,
                                  @Query("price") String price, @Query("number") String total_number,
                               @Query("min_number") String min_number,@Query("way") String way,
                               @Query("currency_id") String currency_id);


    /**
     *  锁仓记录接口
     */
    @GET(Urls.my_lock)
    Call<JSONObject> URLmy_lock();
    /**
     *  锁仓
     */
    @POST(Urls.mylock)
    Call<JSONObject> URLmylock(@Query("count") String count ,@Query("configId") String configId  );
    /**
     *  锁仓
     */
    @GET(Urls.configList)
    Call<JSONObject> URLconfigList();
    /**
     *  锁仓
     */
    @GET(Urls.balance)
    Call<JSONObject> URLbalance();
}
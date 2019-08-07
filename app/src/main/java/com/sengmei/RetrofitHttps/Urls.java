package com.sengmei.RetrofitHttps;

public class Urls {
    public static final String BASE_URL ="http://www.coinbkb.net/api/";//2正式接口 Rodxhttp://www.rodx.cn/api/
//http://biqian.wanzhantong.cn/
    // public static final String BASE_URL="https://www.beltandroad.io/api/";//1正式接口

    public static final String Login = "user/pc_login";//登录接口
    public static final String URLsms_send = "sms_send";//手机发送验证码
    public static final String URLsms_mail = "sms_mail";//邮箱发送验证码
    public static final String URLcheck_mail = "user/check_email";//判断邮箱验证码
    public static final String URLcheck = "user/check_mobile";//判断验证码
    public static final String URLemail = "safe/email"; //绑定邮箱
    public static final String URLmobile = "safe/mobile"; //绑定手机号
    public static final String URLregister = "user/register";//注册账号设置密码
    public static final String URLlegal_list = "currency/legal_list";//法币币总种标题
    public static final String addGongDan = "feedback/add";//个人中心发布工单
    public static final String GongDan = "feedback/list";//我的工单  //没有数据字段有改变内部字段未知 2018.11.07

    public static final String URLTBaddaddress = "wallet/addaddress";//添加提币地址
    public static final String URLTBdeladdress = "wallet/deladdress";//删除提币地址
    public static final String URLcash_save = "user/cash_save";//添加收款方式
    public static final String URLcash_info = "user/cash_info";//添加收款方式
    public static final String URLget_info = "wallet/get_info";//提币率
    public static final String URLget_out = "wallet/out";//提币率提交
    public static final String URLget_detail = "news/detail";//详情
    public static final String URLlogout = "user/logout"; //退出登录
    public static final String URLforget = "user/forget";//忘记密码

    public static final String URLchange = "wallet/change";//划转
    public static final String URLinfo = "legal_deal_info";//购买进
    public static final String URLout = "do_legal_deal";//购买提交
    public static final String URLsellerinfo = "seller_info";//商铺
    public static final String URLlist = "wallet/list";//我的资产
    public static final String URLnews = "news/list";//首页新闻
    public static final String URLappPic = "news/appPic";//首页轮播
    public static final String URLquotation = "currency/quotation";//交易
    public static final String URLquotationlist = "user_match/list";//交易自选区
    public static final String URLoutslegalCurrency = "user_match/legalCurrency";//交易收藏判断
    public static final String URLoutadd="user_match/add";//交易自选区添加
    public static final String URLoutdelete="user_match/delete";//交易自选区删除
    public static final String URLplatformBTC1 = "legal_deal_platform";//ETH 3 ,CNY 19,BTC 2,USDT 4

    public static final String URLtoday_detail = "currency/today_detail";//K顶部
    public static final String URKLin = "transaction/in";//买入
    public static final String URKLout = "transaction/out";//卖出
    public static final String UELK = "currency/new_timeshar";
    public static final String UELlegal_log = "wallet/legal_log";//账户交易记录

    public static final String URLcomplete = "transaction_complete";//交易已完成
    public static final String URLins = "transaction_in";//交易正在买
    public static final String URLouts = "transaction_out";//交易正在卖
    public static final String URLdels = "transaction_del";//交易买卖删除
    public static final String URLdels1 = "transaction_dels";//交易买卖删除
    public static final String URLuser_deal = "legal_user_deal";//订单详情 与 筛选
    public static final String URLsetaccount = "user/setaccount";//提交订单筛选
    public static final String UTLprivate = "news/private";//隐私
    public static final String UTLprivate1 = "news/agreement";//注册协议
    public static final String URLcurrencylist = "wallet/currencylist";//提币地址
    public static final String URLquotation_sort = "currency/quotation_sort";//涨幅
    public static final String URLlegal_deal = "legal_deal"; //待付款
    public static final String URLlegal_dealsure = "legal_deal_user_sure"; //商家确认收款
    public static final String URLlegal_dealquxiao = "user_legal_pay_cancel"; //待付款 取消
    public static final String URLlegal_dealqueren = "user_legal_pay"; //待付款 确定付款 user_legal_pay
    public static final String URLseller = "my_seller"; //我的店铺
    public static final String URLtrade = "seller_trade"; //我的店铺
    public static final String URLsend = "back_send"; //我的店铺撤回
    public static final String URLerror = "error_send"; //我的店铺撤回
    public static final String URLlegal = "legal_send_deal_list";//订单记录
    public static final String URLlegal_send = "legal_send";//发布
    public static final String URLcenter = "user/info";//个人中心
    public static final String URLsafe_center = "safe/safe_center";//安全中心 换成个人中心
    public static final String URLsafe_update = "safe/update_password";//法币重置密码
    public static final String URLsetaccounts = "user/setaccount";//法币设置密码

    public static final String URL_dels = "transaction/deal";//首次进入
    public static final String URL_wallet = "wallet/detail";//可用
    public static final String URLget_address = "wallet/get_address";//提币地址

    public static final String URLaddress = "wallet/get_in_address";//充值管理 //传字段修改  返回结果（未知错误） 2018.11.07
    public static final String URLupload = "upload";//上传图片地址
    public static final String URLreal_name = "user/real_name";//身份认证
    public static final String URLaddres = "wallet/get_address";//提币地址下拉
    public static final String URLC2Clist = "currency/list";//C2C币总选择
    public static final String URLC2CGouMai = "ctoc/order";//购买
    public static final String URLC2Cmy_ctoc = "ctoc/my_ctoc";//我的交易
    public static final String URLC2Cmy_list = "ctoc/my_list";//我的发布
    public static final String URLC2Cpublish = "ctoc/publish";//我的发布
    public static final String URLC2CHlist = "currency/ctoc_list";//C2C标题
    public static final String URLC2CGouMaiList = "ctoc/list";//C2C标题下的内容
    public static final String URLC2Ccancel = "ctoc/cancel";//C2C取消订单
    public static final String URLC2Crevoke = "ctoc/revoke";//C2C取消发布
    public static final String URLC2Cdetail ="ctoc/detail";//C2C取消发布详情
    public static final String URLC2Cconfirm = "ctoc/confirm";//C2C确认收款
    public static final String URLC2Cpay = "ctoc/pay";//C2C确认付款
    public static final String URLdetail ="candy/detail";//wdtg
    public static final String URLexchange ="candy/exchange";//tgdh
    public static final String URLuserinfo ="user/info";//ewm
    public static final String URLmy_lock ="lock/my_lock";//sc
    public static final String URLlock ="lock/lock";//sc
    public static final String URLbalance ="lock/balance";//sc
    public static final String URLC2Corder_detail = "ctoc/order_detail";//C2C详情
    public static final String URLupPaypassword = "user/upPaypassword";//交易密码从新修改
    public static final String URLversion = "version/android";//版本号
    public static final String URLrefresh = "wallet/refresh";//更新法币
    public static final String my_lock = "lock/my_lock";//锁仓记录接口
    public static final String mylock = "lock/lock";//锁仓
    public static final String configList = "lock/configList";//锁仓
    public static final String balance = "lock/balance";//锁仓

}

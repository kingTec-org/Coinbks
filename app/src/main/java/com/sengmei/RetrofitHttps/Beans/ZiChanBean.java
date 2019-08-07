package com.sengmei.RetrofitHttps.Beans;

import java.util.List;

public class ZiChanBean {

    private String type;

    private ObjectBean message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectBean getMessage() {
        return message;
    }

    public void setMessage(ObjectBean message) {
        this.message = message;
    }

    public static class ObjectBean {
        private changeBean change_wallet;
        private legalBean legal_wallet;

        public changeBean getChange_wallet() {
            return change_wallet;
        }

        public void setChange_wallet(changeBean change_wallet) {
            this.change_wallet = change_wallet;
        }

        public legalBean getLegal_wallet() {
            return legal_wallet;
        }

        public void setLegal_wallet(legalBean legal_wallet) {
            this.legal_wallet = legal_wallet;
        }
    }

    public static class changeBean {
        private String total;
        private String totalCNY;
        private List<changebalanceBean> balance;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotalCNY() {
            return totalCNY;
        }

        public void setTotalCNY(String totalCNY) {
            this.totalCNY = totalCNY;
        }

        public List<changebalanceBean> getBalance() {
            return balance;
        }

        public void setBalance(List<changebalanceBean> balance) {
            this.balance = balance;
        }
    }

    public static class legalBean {

        private String total;
        private String totalCNY;
        private List<legalbalanceBean> balance;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotalCNY() {
            return totalCNY;
        }

        public void setTotalCNY(String totalCNY) {
            this.totalCNY = totalCNY;
        }

        public List<legalbalanceBean> getBalance() {
            return balance;
        }

        public void setBalance(List<legalbalanceBean> balance) {
            this.balance = balance;
        }
    }

    public static class changebalanceBean {
        private String is_legal;
        private String currency_name;
        private String is_lever;
        private String change_balance;
        private String currency;
        private String id;
        private String lock_change_balance;
        private String legal_price;
        private String cny_price;
        private String is_pick_up;
        private String is_recharge;

        public String getIs_pick_up() {
            return is_pick_up;
        }

        public void setIs_pick_up(String is_pick_up) {
            this.is_pick_up = is_pick_up;
        }

        public String getIs_recharge() {
            return is_recharge;
        }

        public void setIs_recharge(String is_recharge) {
            this.is_recharge = is_recharge;
        }

        public String getCny_price() {
            return cny_price;
        }

        public void setCny_price(String cny_price) {
            this.cny_price = cny_price;
        }

        public String getLegal_price() {
            return legal_price;
        }

        public void setLegal_price(String legal_price) {
            this.legal_price = legal_price;
        }

        public String getIs_legal() {
            return is_legal;
        }

        public void setIs_legal(String is_legal) {
            this.is_legal = is_legal;
        }

        public String getCurrency_name() {
            return currency_name;
        }

        public void setCurrency_name(String currency_name) {
            this.currency_name = currency_name;
        }

        public String getIs_lever() {
            return is_lever;
        }

        public void setIs_lever(String is_lever) {
            this.is_lever = is_lever;
        }

        public String getChange_balance() {
            return change_balance;
        }

        public void setChange_balance(String change_balance) {
            this.change_balance = change_balance;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLock_change_balance() {
            return lock_change_balance;
        }

        public void setLock_change_balance(String lock_change_balance) {
            this.lock_change_balance = lock_change_balance;
        }

    }
    public static class legalbalanceBean {
        private String is_legal;
        private String currency_name;
        private String is_lever;
        private String lock_legal_balance;
        private String currency;
        private String id;
        private String usdt_price;
        private String legal_balance;
        private String cny_price;

        public String getCny_price() {
            return cny_price;
        }

        public void setCny_price(String cny_price) {
            this.cny_price = cny_price;
        }

        public String getIs_legal() {
            return is_legal;
        }

        public void setIs_legal(String is_legal) {
            this.is_legal = is_legal;
        }

        public String getCurrency_name() {
            return currency_name;
        }

        public void setCurrency_name(String currency_name) {
            this.currency_name = currency_name;
        }

        public String getIs_lever() {
            return is_lever;
        }

        public void setIs_lever(String is_lever) {
            this.is_lever = is_lever;
        }

        public String getLegal_balance() {
            return legal_balance;
        }

        public void setLegal_balance(String legal_balance) {
            this.legal_balance = legal_balance;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLock_legal_balance() {
            return lock_legal_balance;
        }

        public void setLock_legal_balance(String lock_legal_balance) {
            this.lock_legal_balance = lock_legal_balance;
        }

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }
    }
}
/**
 * {
 * "type":"ok",
 * "message":{
 * "lever_wallet":{
 * "total":0,
 * "balance":[
 * <p>
 * ]
 * },
 * "change_wallet":{
 * "total":"67446257.47000",
 * "balance":[
 * {
 * "is_legal":0,
 * "currency_name":"BTC",
 * "is_lever":0,
 * "change_balance":"10119.00000000",
 * "currency":3,
 * "id":8,
 * "lock_change_balance":"0.00000000",
 * "legal_price":"65258746.47000"
 * },
 * {
 * "is_legal":0,
 * "currency_name":"ETH",
 * "is_lever":0,
 * "change_balance":"9980.00000000",
 * "currency":4,
 * "id":9,
 * "lock_change_balance":"20.00000000",
 * "legal_price":"2067800.00000"
 * },
 * {
 * "is_legal":0,
 * "currency_name":"EOS",
 * "is_lever":0,
 * "change_balance":"10000.00000000",
 * "currency":5,
 * "id":10,
 * "lock_change_balance":"0.00000000",
 * "legal_price":"52483.00000"
 * }
 * ],
 * "totalCNY":"11241000.00000"
 * },
 * "legal_wallet":{
 * "total":"67228.00000",
 * "balance":[
 * {
 * "is_legal":1,
 * "currency_name":"CNY",
 * "is_lever":0,
 * "lock_legal_balance":"-100.00000000",
 * "currency":1,
 * "id":6,
 * "usdt_price":"57228.00000",
 * "legal_balance":"9638.00000000"
 * },
 * {
 * "is_legal":1,
 * "currency_name":"USDT",
 * "is_lever":0,
 * "lock_legal_balance":"1.00000000",
 * "currency":2,
 * "id":7,
 * "usdt_price":"10000.00000",
 * "legal_balance":"9999.00000000"
 * }
 * ],
 * "totalCNY":"11205.00000"
 * }
 * }
 * }
 */
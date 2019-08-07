package com.sengmei.RetrofitHttps.Beans;


import java.util.List;

public class ZhuanHuanBean {
    private String type;

    private ObjectBean message;

    public ObjectBean getMessage() {
        return message;
    }

    public void setMessage(ObjectBean message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static class ObjectBean {

        private List<walletBean> legal_wallet;

        public List<walletBean> getLegal_wallet() {
            return legal_wallet;
        }

        public void setLegal_wallet(List<walletBean> legal_wallet) {
            this.legal_wallet = legal_wallet;
        }
    }

    public static class walletBean {

        private int id;
        private String name;
        private String legal_balance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLegal_balance() {
            return legal_balance;
        }

        public void setLegal_balance(String legal_balance) {
            this.legal_balance = legal_balance;
        }
    }
}

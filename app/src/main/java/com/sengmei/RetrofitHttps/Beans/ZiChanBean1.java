package com.sengmei.RetrofitHttps.Beans;

import java.util.List;

public class ZiChanBean1 {

    /**
     * message : {"change_wallet":{"balance":[{"change_balance":"0.00000000","cny_price":"0.00000","currency":1,"currency_name":"BTC","id":571276,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":4,"currency_name":"BKBC","id":571279,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":5,"currency_name":"BCH","id":571280,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":6,"currency_name":"XRP","id":571281,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":7,"currency_name":"EOS","id":571282,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":8,"currency_name":"FOE","id":571283,"is_legal":0,"is_pick_up":1,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":9,"currency_name":"FDS","id":571284,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":15,"currency_name":"CNNS","id":571285,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":16,"currency_name":"VOLLAR","id":571286,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":17,"currency_name":"MGC","id":571287,"is_legal":0,"is_pick_up":0,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"}],"total":"0.00000","totalCNY":"0.00000"},"legal_wallet":{"balance":[{"cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"},{"cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"}],"total":"0.00000","totalCNY":"0.00000"},"min_balance":1.0E-4}
     * type : ok
     */

    private MessageBean message;
    private String type;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class MessageBean {
        /**
         * change_wallet : {"balance":[{"change_balance":"0.00000000","cny_price":"0.00000","currency":1,"currency_name":"BTC","id":571276,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":4,"currency_name":"BKBC","id":571279,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":5,"currency_name":"BCH","id":571280,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":6,"currency_name":"XRP","id":571281,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":7,"currency_name":"EOS","id":571282,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":8,"currency_name":"FOE","id":571283,"is_legal":0,"is_pick_up":1,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":9,"currency_name":"FDS","id":571284,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":15,"currency_name":"CNNS","id":571285,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":16,"currency_name":"VOLLAR","id":571286,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":17,"currency_name":"MGC","id":571287,"is_legal":0,"is_pick_up":0,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"}],"total":"0.00000","totalCNY":"0.00000"}
         * legal_wallet : {"balance":[{"cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"},{"cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"}],"total":"0.00000","totalCNY":"0.00000"}
         * min_balance : 1.0E-4
         */

        private ChangeWalletBean change_wallet;
        private LegalWalletBean legal_wallet;
        private double min_balance;

        public ChangeWalletBean getChange_wallet() {
            return change_wallet;
        }

        public void setChange_wallet(ChangeWalletBean change_wallet) {
            this.change_wallet = change_wallet;
        }

        public LegalWalletBean getLegal_wallet() {
            return legal_wallet;
        }

        public void setLegal_wallet(LegalWalletBean legal_wallet) {
            this.legal_wallet = legal_wallet;
        }

        public double getMin_balance() {
            return min_balance;
        }

        public void setMin_balance(double min_balance) {
            this.min_balance = min_balance;
        }

        public static class ChangeWalletBean {
            /**
             * balance : [{"change_balance":"0.00000000","cny_price":"0.00000","currency":1,"currency_name":"BTC","id":571276,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":4,"currency_name":"BKBC","id":571279,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":5,"currency_name":"BCH","id":571280,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":6,"currency_name":"XRP","id":571281,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":7,"currency_name":"EOS","id":571282,"is_legal":0,"is_pick_up":0,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":8,"currency_name":"FOE","id":571283,"is_legal":0,"is_pick_up":1,"is_recharge":0,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":9,"currency_name":"FDS","id":571284,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":15,"currency_name":"CNNS","id":571285,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":16,"currency_name":"VOLLAR","id":571286,"is_legal":0,"is_pick_up":1,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"},{"change_balance":"0.00000000","cny_price":"0.00000","currency":17,"currency_name":"MGC","id":571287,"is_legal":0,"is_pick_up":0,"is_recharge":1,"legal_price":"0.00000","lock_change_balance":"0.00000000"}]
             * total : 0.00000
             * totalCNY : 0.00000
             */

            private String total;
            private String totalCNY;
            private List<BalanceBean> balance;

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

            public List<BalanceBean> getBalance() {
                return balance;
            }

            public void setBalance(List<BalanceBean> balance) {
                this.balance = balance;
            }

            public static class BalanceBean {
                /**
                 * change_balance : 0.00000000
                 * cny_price : 0.00000
                 * currency : 1
                 * currency_name : BTC
                 * id : 571276
                 * is_legal : 0
                 * is_pick_up : 1
                 * is_recharge : 1
                 * legal_price : 0.00000
                 * lock_change_balance : 0.00000000
                 */

                private String change_balance;
                private String cny_price;
                private int currency;
                private String currency_name;
                private int id;
                private int is_legal;
                private int is_pick_up;
                private int is_recharge;
                private String legal_price;
                private String lock_change_balance;

                public String getChange_balance() {
                    return change_balance;
                }

                public void setChange_balance(String change_balance) {
                    this.change_balance = change_balance;
                }

                public String getCny_price() {
                    return cny_price;
                }

                public void setCny_price(String cny_price) {
                    this.cny_price = cny_price;
                }

                public int getCurrency() {
                    return currency;
                }

                public void setCurrency(int currency) {
                    this.currency = currency;
                }

                public String getCurrency_name() {
                    return currency_name;
                }

                public void setCurrency_name(String currency_name) {
                    this.currency_name = currency_name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIs_legal() {
                    return is_legal;
                }

                public void setIs_legal(int is_legal) {
                    this.is_legal = is_legal;
                }

                public int getIs_pick_up() {
                    return is_pick_up;
                }

                public void setIs_pick_up(int is_pick_up) {
                    this.is_pick_up = is_pick_up;
                }

                public int getIs_recharge() {
                    return is_recharge;
                }

                public void setIs_recharge(int is_recharge) {
                    this.is_recharge = is_recharge;
                }

                public String getLegal_price() {
                    return legal_price;
                }

                public void setLegal_price(String legal_price) {
                    this.legal_price = legal_price;
                }

                public String getLock_change_balance() {
                    return lock_change_balance;
                }

                public void setLock_change_balance(String lock_change_balance) {
                    this.lock_change_balance = lock_change_balance;
                }
            }
        }

        public static class LegalWalletBean {
            /**
             * balance : [{"cny_price":"0.00000","currency":2,"currency_name":"ETH","id":571277,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"},{"cny_price":"0.00000","currency":3,"currency_name":"USDT","id":571278,"is_legal":1,"is_pick_up":1,"is_recharge":1,"legal_balance":"0.00000000","legal_price":"0.00000","lock_legal_balance":"0.00000000","usdt_price":"0.00000"}]
             * total : 0.00000
             * totalCNY : 0.00000
             */

            private String total;
            private String totalCNY;
            private List<BalanceBeanX> balance;

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

            public List<BalanceBeanX> getBalance() {
                return balance;
            }

            public void setBalance(List<BalanceBeanX> balance) {
                this.balance = balance;
            }

            public static class BalanceBeanX {
                /**
                 * cny_price : 0.00000
                 * currency : 2
                 * currency_name : ETH
                 * id : 571277
                 * is_legal : 1
                 * is_pick_up : 1
                 * is_recharge : 1
                 * legal_balance : 0.00000000
                 * legal_price : 0.00000
                 * lock_legal_balance : 0.00000000
                 * usdt_price : 0.00000
                 */

                private String cny_price;
                private int currency;
                private String currency_name;
                private int id;
                private int is_legal;
                private int is_pick_up;
                private int is_recharge;
                private String legal_balance;
                private String legal_price;
                private String lock_legal_balance;
                private String usdt_price;

                public String getCny_price() {
                    return cny_price;
                }

                public void setCny_price(String cny_price) {
                    this.cny_price = cny_price;
                }

                public int getCurrency() {
                    return currency;
                }

                public void setCurrency(int currency) {
                    this.currency = currency;
                }

                public String getCurrency_name() {
                    return currency_name;
                }

                public void setCurrency_name(String currency_name) {
                    this.currency_name = currency_name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIs_legal() {
                    return is_legal;
                }

                public void setIs_legal(int is_legal) {
                    this.is_legal = is_legal;
                }

                public int getIs_pick_up() {
                    return is_pick_up;
                }

                public void setIs_pick_up(int is_pick_up) {
                    this.is_pick_up = is_pick_up;
                }

                public int getIs_recharge() {
                    return is_recharge;
                }

                public void setIs_recharge(int is_recharge) {
                    this.is_recharge = is_recharge;
                }

                public String getLegal_balance() {
                    return legal_balance;
                }

                public void setLegal_balance(String legal_balance) {
                    this.legal_balance = legal_balance;
                }

                public String getLegal_price() {
                    return legal_price;
                }

                public void setLegal_price(String legal_price) {
                    this.legal_price = legal_price;
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
    }
}

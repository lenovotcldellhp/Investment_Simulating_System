package com.example.demo;

public class recommend {
    String stockname;
    String stockcode;
    String newest_price;

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getNewest_price() {
        return newest_price;
    }

    public void setNewest_price(String newest_price) {
        this.newest_price = newest_price;
    }

    public String getChange_rate() {
        return change_rate;
    }

    public void setChange_rate(String change_rate) {
        this.change_rate = change_rate;
    }

    String change_rate;
}

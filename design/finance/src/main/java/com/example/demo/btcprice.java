package com.example.demo;

public class btcprice {
    //比特币价格信息实体类，莱特币价格也借用这个类
    private float price;
    private String time;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}

package com.example.demo;

public class btchistory {
    //比特币历史信息实体类
    private float lowest;
    private float highest;
    private float start_price;

    public float getLowest() {
        return lowest;
    }

    public void setLowest(float lowest) {
        this.lowest = lowest;
    }

    public float getHighest() {
        return highest;
    }

    public void setHighest(float highest) {
        this.highest = highest;
    }

    public float getStart_price() {
        return start_price;
    }

    public void setStart_price(float start_price) {
        this.start_price = start_price;
    }

    public float getFinal_price() {
        return final_price;
    }

    public void setFinal_price(float final_price) {
        this.final_price = final_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private float final_price;
    private String date;
}

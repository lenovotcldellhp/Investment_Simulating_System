package com.example.demo;

public class analyse {
    //盈亏分析实体类
    private String time;//记录产生时间

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    private float rate;//变化率

}

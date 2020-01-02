package com.example.demo;

public class buyResult {
    String status;
    float count;
    float money;
    float service_fee;//手续费
    String code;
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public float getService_fee(){
        return this.service_fee;
    }
    public void setService_fee(float fee){
        this.service_fee=fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    String comments;
}

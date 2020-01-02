package com.example.demo.controller;

import com.example.demo.*;
import com.example.demo.service.ltcBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ltcBuyController {
    @Autowired
    private ltcBuyService ltcbuyService;
    float exchange_rate;
    float newest_price;
    String username;
    @GetMapping("/ltcbuy")
    public String btcBuy(Model model){

        username=ltcbuyService.getUsername();
        System.out.println("现在登录的用户是"+username);
        if(username==null){
            System.out.println("您尚未登录或登录状态已失效。请登录后再操作。");

        }
        List<exchangeRate> list1=ltcbuyService.getUSDrate();
        for(int i=0;i<list1.size();i++){
            System.out.println("美元汇率是");
            exchange_rate=list1.get(i).getRate();
            System.out.println(list1.get(i).getRate());
        }
        List<btcprice> list2=ltcbuyService.getBTCNewestPrice();
        for(int i=0;i<list2.size();i++){
            System.out.println("比特币最新价格是");
            newest_price=list2.get(i).getPrice();
            System.out.println(list2.get(i).getPrice());
        }
        List<user> user1=ltcbuyService.getBalance(username);
        for(int i=0;i<user1.size();i++){
            System.out.println("余额是");
            System.out.println(user1.get(i).getBalance());
        }
        //    ltcbuyService.setBalance(username,150001);
        List<btcprice> count1=ltcbuyService.getHolding(username);
        for(int i=0;i<count1.size();i++){
            System.out.println("当前用户持有的比特币数量是");
            System.out.println(count1.get(i).getPrice());
        }
        trade trade1=new trade();
        model.addAttribute("trade1",trade1);

        return "ltcbuy";
    }

    @PostMapping("ltcbuy")
    public String btcbuyResult(Model model,@ModelAttribute trade trade2){
        float count=trade2.getCount();
        System.out.println("交易量为"+count);
        buyResult result=new buyResult();
        if(username==null){
            System.out.println("您尚未登录或登录状态已失效。请登录后再操作。");
            result.setStatus("交易失败");
            result.setComments("您尚未登录或登录状态已失效。请登录后再操作。");
            model.addAttribute("result",result);
            return "btcbuyresult";
        }
        float total_money=count*exchange_rate*newest_price;

        float fee_rate=(float)0.001;
        float service_fee=total_money*fee_rate;
        float new_balance=0;
        List<user> user1=ltcbuyService.getBalance(username);
        for(int i=0;i<user1.size();i++){
            new_balance=(user1.get(i).getBalance());
            new_balance=new_balance-total_money-service_fee;

        }
        result.setCount(count);
        result.setMoney(total_money);
        if(new_balance>0) {
            ltcbuyService.setBalance(username, new_balance);
            result.setStatus("交易成功");
            ltcbuyService.insertRecord(username,"ltc",count,newest_price,service_fee);
            result.setService_fee(service_fee);
            ltcbuyService.addLTC(username, count);
        }
        else{
            System.out.println("本金余额不足，无法完成购买。");
            result.setStatus("交易失败");
            result.setComments("本金余额不足，无法完成LTC购买。");
        }
        model.addAttribute("result",result);
        return "btcbuyresult";
    }


}

package com.example.demo.controller;

import com.example.demo.buyResult;
import com.example.demo.trade;
import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.service.stockbuyService;
import com.example.demo.service.ltcBuyService;

import java.util.List;

@Controller
public class stockBuyController {
    @Autowired
    private stockbuyService stockbuyService;
    @Autowired
    private ltcBuyService ltcbuyService;

    String username;
    @GetMapping("/stockbuy")
    public String stockBuy(Model model) {
        username=stockbuyService.getUsername();
        System.out.println(username);
       // ltcBuyService ltcbuyService=new ltcBuyService();

        trade trade1=new trade();
        model.addAttribute("trade1",trade1);
        return "stockbuy";
    }

    @PostMapping("stockbuy")
    public String stockBuyResult(Model model, @ModelAttribute trade trade2){
        buyResult result=new buyResult();
        if(username==null){
            result.setStatus("交易失败");
            result.setComments("您尚未登录，请登录后再进行交易。");
            model.addAttribute("result",result);
            return "stockbuyresult";
        }
        String stockcode=trade2.getType();
        result.setCode(stockcode);
        float count=trade2.getCount();
        System.out.println("#####################");

        float record_count=stockbuyService.get_user_stock_list(username,stockcode).size();
        //这只股票记录在此人的标的物表中有无记录，1表示有，0表示无
        float holding_count=0;//用户持有这只股票的数量
        if(record_count==1){
            holding_count=stockbuyService.get_user_stock_list(username,stockcode).get(0).getCount();
        }
        System.out.println(record_count);
        System.out.println(stockcode);
        System.out.println(count);
        float current_price=stockbuyService.getCurrentPrice(stockcode);
        System.out.println("当前价格"+current_price);
        List<user> user1=ltcbuyService.getBalance(username);
        float balance=0;
        for(int i=0;i<user1.size();i++){
            System.out.println("余额是");
            System.out.println(balance=user1.get(i).getBalance());
        }

        if(count<0){//卖出交易操作
            if(record_count==0){
                result.setStatus("交易失败");
                result.setComments("您尚未购买过这只股票，因此不能卖出。");
                model.addAttribute("result",result);
                return "stockbuyresult";
            }
            else if(holding_count+count<0){
                result.setStatus("交易失败");
                result.setComments("您持有的股票量不足，因此不能卖出。");

                System.out.println("股票持有量不足"+count);
                model.addAttribute("result",result);
                return "stockbuyresult";
            }
            else{//如果股票数足够，则执行操作
                float total_money=(0-count)*stockbuyService.getCurrentPrice(stockcode);
                float shouxu_fee=(float)0.005*total_money;
                ltcbuyService.setBalance(username,balance+total_money-shouxu_fee);
                stockbuyService.setHolding(username,holding_count+count,stockcode);
                result.setStatus("交易成功");
                result.setService_fee(shouxu_fee);
                result.setMoney(total_money);
                result.setCode(stockcode);
                result.setCount(count);
            }

        }
        else if(count>=0){//买入交易操作
            float total_money=(count)*stockbuyService.getCurrentPrice(stockcode);
            float shouxu_fee=(float)0.001*total_money;
            if(total_money+shouxu_fee>balance){
               // result.setStatus("交易失败");
                result.setStatus("交易失败");
                result.setComments("您的本金不足，无法购买该数量的这支股票。");
                System.out.println("本金不足"+count+"   "+balance);
                model.addAttribute("result",result);
                return "stockbuyresult";
            }
            else {
                System.out.println("***********total money"+total_money);
                ltcbuyService.setBalance(username, balance - total_money - shouxu_fee);
                if(record_count!=0) {//如果数据库里已有这只股票的记录，则更新
                    stockbuyService.setHolding(username, holding_count + count, stockcode);
                }
                else{
                    stockbuyService.insertHolding(username,holding_count+count,stockcode);
                }
                result.setStatus("交易成功");
                result.setService_fee(shouxu_fee);
                result.setMoney(total_money);
                result.setCode(stockcode);
                result.setCount(count);
                stockbuyService.insertRecord(username,stockcode,count,stockbuyService.getCurrentPrice(stockcode),shouxu_fee);
            }
        }

        model.addAttribute("result",result);
        return "stockbuyresult";
    }
}

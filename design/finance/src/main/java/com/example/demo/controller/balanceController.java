package com.example.demo.controller;

import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.service.btcBuyService;
import java.util.List;
import com.example.demo.registerResult;

@Controller
public class balanceController {
    @Autowired
    btcBuyService btcbuyService=new btcBuyService();
    @GetMapping("balance")
    public String Balance(Model model){


        String username=btcbuyService.getUsername();
        System.out.println("username======"+username);
        List<user> user1=btcbuyService.getBalance(username);
        float balance=0;
        for(int i=0;i<user1.size();i++){
            System.out.println("余额是");
            balance=user1.get(i).getBalance();
            System.out.println(balance);
        }
        registerResult result=new registerResult();
        result.setResult("余额查询");
        result.setUsername(username);
        result.setErrorMessage("您的余额为："+String.valueOf(balance));
        model.addAttribute("registerresult",result);
        return "balance";
    }
}

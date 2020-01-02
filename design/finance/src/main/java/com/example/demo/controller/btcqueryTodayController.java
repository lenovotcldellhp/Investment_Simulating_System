package com.example.demo.controller;

import com.example.demo.service.btcqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class btcqueryTodayController {
    @Autowired
    private btcqueryService btcqueryservice;


    @GetMapping("/btcquerytoday")
    public String getBtcprice(Model model){
        List pricelist=btcqueryservice.getBtcpriceListToday();
        int i = 0;
        model.addAttribute("pricelist",pricelist);

        return "btcquerytoday";
    }
}

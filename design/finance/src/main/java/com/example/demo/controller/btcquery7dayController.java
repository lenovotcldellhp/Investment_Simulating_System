package com.example.demo.controller;

import com.example.demo.service.btcqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class btcquery7dayController {
    @Autowired
    private btcqueryService btcqueryservice;


    @GetMapping("/btcquery7day")
    public String getBtcprice(Model model){
        List pricelist=btcqueryservice.getBtcpriceList7day();
        int i = 0;
        model.addAttribute("pricelist",pricelist);

        return "btcquery7day";
    }
}

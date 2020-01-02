package com.example.demo.controller;

import com.example.demo.service.ltcqueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ltcquery3dayController {
    @Autowired
    private ltcqueryService btcqueryservice;


    @GetMapping("/ltcquery3day")
    public String getBtcprice(Model model){
        List pricelist=btcqueryservice.getLtcpriceList3day();
        int i = 0;
        model.addAttribute("pricelist",pricelist);

        return "ltcquery3day";
    }
}

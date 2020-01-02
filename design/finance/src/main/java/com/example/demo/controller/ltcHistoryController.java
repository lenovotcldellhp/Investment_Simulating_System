package com.example.demo.controller;

import com.example.demo.service.ltchistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ltcHistoryController {

    @Autowired
    private ltchistoryService btcHistoryService;


    @GetMapping("/ltchistory")
    public String getBtcprice(Model model){
        List pricelist=btcHistoryService.getBtcpriceList();
        int i = 0;
        model.addAttribute("pricelist",pricelist);

        return "ltchistory";
    }
}

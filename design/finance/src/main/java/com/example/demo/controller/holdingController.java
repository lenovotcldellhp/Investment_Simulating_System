package com.example.demo.controller;

import com.example.demo.Stock;
import com.example.demo.record;
import com.example.demo.service.btcBuyService;
import com.example.demo.service.holdingService;
import com.example.demo.service.recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class holdingController {
    @Autowired
    holdingService holdingservice=new holdingService();
    @Autowired
    btcBuyService btcbuyService=new btcBuyService();


    @GetMapping("holding")
    public String Record(Model model){

        String username=btcbuyService.getUsername();
        List<Stock> records=holdingservice.getHolding(username);

        for(int i=0;i<records.size();i++){

            String code=records.get(i).getStockcode();
            float count=records.get(i).getCount();

              // System.out.println(username+"   "+code+"    "+count);
        }
        model.addAttribute("records",records);
        return "holding";
    }

}

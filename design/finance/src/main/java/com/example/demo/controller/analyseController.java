package com.example.demo.controller;

import com.example.demo.analyse;
import com.example.demo.service.analyseService;
import com.example.demo.service.btcBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class analyseController {
    @Autowired
    analyseService analyseservice=new analyseService();
    @Autowired
    btcBuyService btcbuyservice=new btcBuyService();
    @GetMapping("analyse")
    public String analysing(Model model){
        String username=btcbuyservice.getUsername();
        List<analyse> result=analyseservice.getAnalyse(username);
        System.out.println("analyse result length"+result.size());
        model.addAttribute("result",result);
        return "analyse";
    }
}

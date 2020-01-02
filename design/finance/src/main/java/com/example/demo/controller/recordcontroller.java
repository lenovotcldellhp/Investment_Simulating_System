package com.example.demo.controller;

import com.example.demo.record;
import com.example.demo.service.recordService;
import com.example.demo.service.btcBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class recordcontroller {

    @Autowired
    recordService recordservice=new recordService();
    @Autowired
    btcBuyService btcbuyService=new btcBuyService();


    @GetMapping("record")
    public String Record(Model model){

        String username=btcbuyService.getUsername();
        List<record> records=recordservice.getRecord(username);

        for(int i=0;i<records.size();i++){
            username=records.get(i).getUsername();
            String code=records.get(i).getCode();
            float count=records.get(i).getCount();
            float price=records.get(i).getPrice();
            float fee=records.get(i).getFee();
            String time=records.get(i).getTime();
         //   System.out.println(username+"   "+code+"    "+count+"   "+price+"   "+fee+" "+time);
        }
        model.addAttribute("records",records);
        return "record";
    }


}

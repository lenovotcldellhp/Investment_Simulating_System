package com.example.demo.controller;

import com.example.demo.recommend;
import com.example.demo.service.recommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class recommendController {
    @Autowired
    recommendService recommendservice=new recommendService();
    @GetMapping("recommend")
    public String recommend(Model model){
        List<recommend> recommends=recommendservice.getRecommend();
        model.addAttribute("recommends",recommends);
        return "recommend";
    }
}

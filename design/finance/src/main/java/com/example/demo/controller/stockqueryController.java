package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class stockqueryController {
    @GetMapping("/stockquery")
    public String stockquery(){
        return ("stockquery");
    }
}

package com.example.demo.controller;

import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.service.registerService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.registerResult;

@Controller
public class registerController {
    @Autowired
    private registerService registerService;

    @GetMapping("/register")
    public String register(Model model){//这个方法用来给页面加表单
        user user1=new user();
        model.addAttribute("user1",user1);
     //   System.out.println("************");
     //   registerService.addUser("test","passw0rd");
     //   System.out.println("%%%%%%%%%%%%%%%%");
     //   registerService.addHoldingTable("test");
     //   System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
      //  registerService.initalizeHoldingTable("user1");
      //  registerService.userExist("user1");
        return ("register");
    }

    @PostMapping("/register")
    public String registerSubmit(Model model, @ModelAttribute user user2){
        String username=user2.getUsername();
        String password=user2.getPassword();
      //  System.out.println("username="+username+"   password="+password);
        registerResult registerresult=new registerResult();
        try {
            registerService.addUser(username, password);//在User表里添加记录
            registerService.addHoldingTable(username);//设置标的物持有表
            registerService.initalizeHoldingTable(username);//初始化，增加BTC LTC信息（初值为0）
            registerresult.setErrorMessage("");//未出错，所以错误信息为空字符串
            registerresult.setResult("注册成功");
            registerresult.setUsername(username);
        }
        catch(Exception e){
            System.out.println("*******************");
            System.out.println(e);
            registerresult.setErrorMessage("请检查您的用户名是否已被占用。错误类型是："+e.toString());

            registerresult.setResult("注册失败");
            registerresult.setUsername(username);
        }
        model.addAttribute("registerresult",registerresult);
        return("registerresult");
    }
}

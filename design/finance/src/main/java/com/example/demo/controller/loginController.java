package com.example.demo.controller;

import com.example.demo.CookieUtils;
import com.example.demo.registerResult;
import com.example.demo.service.registerService;
import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    private com.example.demo.service.loginService loginservice;

    @GetMapping("login")
    public String register(Model model){//这个方法用来给页面加表单
        user user1=new user();
        model.addAttribute("user1",user1);



        return ("login");
    }

    @PostMapping("login")
    public String registerResult(Model model,@ModelAttribute user user2){
        String username=user2.getUsername();
        String password=user2.getPassword();
        List<user> list1=loginservice.getPassword(username);
        user user3=new user();
        boolean correct=false;
        for(int i=0;i<list1.size();i++){
            if (list1.get(i).getPassword().equals(password)){
                System.out.println(list1.get(i).getUsername());
                correct=true;
            }
        }
        registerResult registerresult=new registerResult();
        if(correct){
            System.out.println("密码正确");
            CookieUtils cookieUtils=new CookieUtils();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();

            cookieUtils.writeCookie(response,"username",username);
            String cookiestr=cookieUtils.getCookie(request,"username");
            System.out.println(cookiestr);
            registerresult.setUsername(username);
            registerresult.setResult("登录成功");
            registerresult.setErrorMessage("现在您可以进行交易操作了。请注意：登录状态有效时间为1小时。");
        }
        else{
            System.out.println("密码错误");
            registerresult.setUsername(username);
            registerresult.setResult("登录失败");
            registerresult.setErrorMessage("请确定您的用户名、密码是否正确。");
        }
        model.addAttribute("registerresult",registerresult);
        return("registerresult");
    }
}

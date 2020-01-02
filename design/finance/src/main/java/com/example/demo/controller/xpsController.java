package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.xpsService;
import com.example.demo.xps;
import com.example.demo.CookieUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping
public class xpsController {
    @Autowired
    //  IStockService stockService;
    private xpsService xpsservice;
    @RequestMapping(value="/xps",method=RequestMethod.GET)
    public String index(){
        xps ps=new xps();
      //   ps=xpsservice.getXPS();

        CookieUtils cookieUtils=new CookieUtils();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        cookieUtils.writeCookie(response,"TEST","123456");
        String cookiestr=cookieUtils.getCookie(request,"TEST");
        System.out.println(cookiestr);

        return "<h1>Hello world!</h1><h2>你已连接到投资演练平台，请选择其他功能页面来操作。 </h2>";



    }

}

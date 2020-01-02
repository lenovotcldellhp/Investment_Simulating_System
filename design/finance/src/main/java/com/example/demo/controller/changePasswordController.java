package com.example.demo.controller;

import com.example.demo.registerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.demo.service.changePasswordService;
import com.example.demo.service.ltcBuyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.changepassword;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class changePasswordController {
    String username;
    String old_password;
    @Autowired
    private changePasswordService changePasswordservice;
    @Autowired
    private ltcBuyService btcbuyService;
    @GetMapping("changepassword")
    public String changePassword(Model model){
        username=btcbuyService.getUsername();
        System.out.println("现在登录的用户是"+username);
        if(username==null){
            System.out.println("您尚未登录或登录状态已失效。请登录后再操作。");
        }
        old_password=changePasswordservice.getpassword(username);
        System.out.println(old_password);
        changepassword pass1=new changepassword();
        model.addAttribute("pass1",pass1);
        return "changepassword";
    }

    @PostMapping("changepassword")
    public String changePasswordresult(Model model, @ModelAttribute changepassword pass2){
        String new_password=pass2.getNewpassword();
        String new_password_again=pass2.getNewpasswordagain();
        String old_password=pass2.getOldpassword();
        registerResult result=new registerResult();
        if (!new_password.equals(new_password_again)){
            System.out.println("两次输入的密码不一致");
            result.setResult("操作失败");
            result.setErrorMessage("您两次输入的新密码不一致，请更正后再试。");
            result.setUsername(username);
        }
        else if(!old_password.equals(changePasswordservice.getpassword(username))){
            System.out.println("旧密码错误");
            System.out.println("两次输入的密码不一致");
            result.setResult("操作失败");
            result.setErrorMessage("您输入的原密码不正确，请更正后再试。");
            result.setUsername(username);
        }
        else{
            changePasswordservice.changepassword(username,new_password);
            System.out.println("执行了修改操作。");
            System.out.println("两次输入的密码不一致");
            result.setResult("操作成功");
            result.setErrorMessage("密码已更新。");
            result.setUsername(username);
        }
        model.addAttribute("registerresult",result);
        return "changepasswordresult";
    }

}

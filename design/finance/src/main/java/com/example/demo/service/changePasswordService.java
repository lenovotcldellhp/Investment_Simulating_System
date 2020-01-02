package com.example.demo.service;

import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class changePasswordService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private loginService loginservice;
    public String getpassword(String username){//获取某位用户的密码
        List<user> list1=loginservice.getPassword(username);
        String password="";
        for(int i=0;i<list1.size();i++){
           password=list1.get(i).getPassword();
        }
        return password;
    }

    public void changepassword(String username,String new_password){
        String sql="update user set password='"+new_password+"' where username='"+username+"';";
        int rows=jdbcTemplate.update(sql);
    }
}

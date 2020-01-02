package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class registerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void addUser(String username,String password){//在user表里添加用户记录
        String sql="insert into user values('"+username+"','"+password+"',150000)";
      //  System.out.println(sql);
        //插入用户信息的SQL语句
        int rows=jdbcTemplate.update(sql);
    }
    public void addHoldingTable(String username){
        String sql="CREATE TABLE  finance.holding_"+username+"(code VARCHAR(45),count FLOAT) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8";
        //插入用户信息的SQL语句
        int rows=jdbcTemplate.update(sql);
    }
    public void initalizeHoldingTable(String username){
        String sql1="insert into holding_"+username+" values('btc',0)";
        String sql2="insert into holding_"+username+" values('ltc',0)";
        int rows1=jdbcTemplate.update(sql1);
        int rows2=jdbcTemplate.update(sql2);
    }

    public int userExist(String username){//NOT AVALIABLE!!!!
        String sql="select count(*) from user where username='"+username+"'";
        int rows3=jdbcTemplate.update(sql);
        System.out.println("count="+rows3);
        return rows3;
    }

}

package com.example.demo.service;

import com.example.demo.btchistory;
import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class loginService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<user> getPassword(String username){//读用户的密码
        String sql="select password from user where username='"+username+"'";
        return(List<user>) jdbcTemplate.query(sql, new RowMapper<user>() {
            @Override
            public user mapRow(ResultSet rs, int rowNum) throws SQLException {
                user user1=new user();
                user1.setPassword(rs.getString("password"));
              //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }
}

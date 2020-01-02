package com.example.demo.service;

import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class balanceService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<user> getBalance(String username){//读用户的余额
        String sql="select balance from user where username='"+username+"'";
        return(List<user>) jdbcTemplate.query(sql, new RowMapper<user>() {
            @Override
            public user mapRow(ResultSet rs, int rowNum) throws SQLException {
                user user1=new user();
                user1.setBalance(rs.getFloat("balance"));
                //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }
}

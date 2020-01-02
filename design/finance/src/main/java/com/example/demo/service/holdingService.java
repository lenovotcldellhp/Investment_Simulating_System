package com.example.demo.service;

import com.example.demo.Stock;
import com.example.demo.record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class holdingService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Stock> getHolding(String username){
        System.out.println("******************************");
        String sql = "SELECT * FROM holding_"+username+";";
        return (List<Stock>) jdbcTemplate.query(sql, new RowMapper<Stock>(){

            @Override//重写方法的注解
            public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
                Stock rec = new Stock();//新建一个rec对象
                rec.setStockcode(rs.getString("code"));
                rec.setCount(rs.getFloat("count"));


                return rec;
                //  return null;
            }

        });
    }
}

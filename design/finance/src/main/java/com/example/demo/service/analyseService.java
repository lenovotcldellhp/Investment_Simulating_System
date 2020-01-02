package com.example.demo.service;

import com.example.demo.analyse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class analyseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<analyse> getAnalyse(String username){
        String sql="select * from stat where username='"+username+"';";
        return (List<analyse>) jdbcTemplate.query(sql, new RowMapper<analyse>(){

            @Override//重写方法的注解
            public analyse mapRow(ResultSet rs, int rowNum) throws SQLException {
                analyse rec = new analyse();//新建一个rec对象
                rec.setRate(rs.getFloat("change_rate"));
                rec.setTime(rs.getString("time"));


                return rec;
                //  return null;
            }

        });
    }
}

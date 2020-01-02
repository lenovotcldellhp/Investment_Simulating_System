package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.example.demo.recommend;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class recommendService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<recommend> getRecommend(){
        System.out.println("******************************");
        String sql = "SELECT * FROM recommend;";
        return (List<recommend>) jdbcTemplate.query(sql, new RowMapper<recommend>(){

            @Override//重写方法的注解
            public recommend mapRow(ResultSet rs, int rowNum) throws SQLException {
                recommend rec = new recommend();//新建一个rec对象
                rec.setStockcode(rs.getString("stockcode"));
                rec.setStockname(rs.getString("stockname"));
                rec.setChange_rate(rs.getString("change_rate"));
                rec.setNewest_price(rs.getString("newest_price"));

                return rec;
                //  return null;
            }

        });
    }
}

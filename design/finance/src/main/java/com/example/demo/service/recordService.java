package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.record;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class recordService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<record> getRecord(String username){
        System.out.println("******************************");
        String sql = "SELECT * FROM record where username='"+username+"';";
        return (List<record>) jdbcTemplate.query(sql, new RowMapper<record>(){

            @Override//重写方法的注解
            public record mapRow(ResultSet rs, int rowNum) throws SQLException {
                record rec = new record();//新建一个rec对象
                rec.setCount(rs.getFloat("count"));
                rec.setPrice(rs.getFloat("price"));
                rec.setTime(rs.getString("time"));
                rec.setCode(rs.getString("code"));
                rec.setUsername(rs.getString("username"));
                rec.setFee(rs.getFloat("fee"));

                return rec;
                //  return null;
            }

        });
    }
}

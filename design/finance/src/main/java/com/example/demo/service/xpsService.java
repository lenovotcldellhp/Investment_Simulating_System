package com.example.demo.service;
import com.example.demo.xps;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class xpsService {
    private JdbcTemplate jdbcTemplate;
    public List<xps> getXPS(){
        String sql="SELECT * FROM test";
        return (List<xps>) jdbcTemplate.query(sql, new RowMapper<xps>(){

            @Override//重写方法的注解
            public xps mapRow(ResultSet rs, int rowNum) throws SQLException {
                xps ps = new xps();//新建一个history对象
                ps.seta(rs.getInt("test"));
                System.out.println(ps);
                System.out.println(ps.geta());
                return ps;

            }

        });
    }
}

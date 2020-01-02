package com.example.demo.service;

import com.example.demo.btchistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ltchistoryService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<btchistory> getBtcpriceList(){//读比特币历史信息表
        String sql="select * from ltc_history";
        return(List<btchistory>) jdbcTemplate.query(sql, new RowMapper<btchistory>() {
            @Override
            public btchistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                btchistory price=new btchistory();
                price.setDate(rs.getString("date"));//日期
                price.setHighest(rs.getFloat("highest"));//最高价
                price.setLowest(rs.getFloat("lowest"));//最低价
                price.setFinal_price(rs.getFloat("final_price"));//收盘价
                price.setStart_price(rs.getFloat("start_price"));//开盘价
                //     System.out.println(price.getDate()+" "+price.getHighest()+" "+price.getLowest()+" "+price.getFinal_price()+" "+price.getStart_price());
                return price;
            }
        });
    }

}

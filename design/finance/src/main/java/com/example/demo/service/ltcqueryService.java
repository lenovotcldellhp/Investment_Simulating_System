package com.example.demo.service;

import com.example.demo.DateUtils;
import com.example.demo.btcprice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ltcqueryService {

    DateUtils dateUtils=new DateUtils();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<btcprice> getLtcpriceListToday(){//读今日的莱特币价格信息表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today=simpleDateFormat.format(DateUtils.getDayBegin());
        // String sql="select * from btc_price";
        //String sql="select * from btc_price where time like '"+today+"%0' or time like '"+today+"%5'";
        String sql="select * from ltc_price where time like '"+today+"%'";
        return(List<btcprice>) jdbcTemplate.query(sql, new RowMapper<btcprice>() {
            @Override
            public btcprice mapRow(ResultSet rs, int rowNum) throws SQLException {
                btcprice price=new btcprice();
                price.setPrice(rs.getFloat("price"));
                price.setTime(rs.getString("time"));
                // System.out.println(rs.getFloat("price"));
                //  System.out.println(rs.getString("time"));
                return price;
            }
        });
    }

    public List<btcprice> getLtcpriceList3day(){//读3日的莱特币价格信息表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today=simpleDateFormat.format(DateUtils.getDayBegin());
        Date date = new Date();
        String yesterday=simpleDateFormat.format(DateUtils.getFrontDay(date,1));//昨天
        String qianday=simpleDateFormat.format(DateUtils.getFrontDay(date,2));//前天
        // String sql="select * from btc_price";
        //String sql="select * from btc_price where time like '"+today+"%0' or time like '"+today+"%5'";
        String sql="select * from ltc_price where time like '"+today+"%0' or time like '"+today+"%5'"+"or time like '"+yesterday+"%0'"+"or time like '"+yesterday+"%5'"+"or time like '"+qianday+"%0'"+"or time like '"+qianday+"%5'";

        return(List<btcprice>) jdbcTemplate.query(sql, new RowMapper<btcprice>() {
            @Override
            public btcprice mapRow(ResultSet rs, int rowNum) throws SQLException{
                btcprice price=new btcprice();
                price.setPrice(rs.getFloat("price"));
                price.setTime(rs.getString("time"));
                // System.out.println(rs.getFloat("price"));
                //  System.out.println(rs.getString("time"));
                return price;
            }
        });
    }

    public List<btcprice> getLtcpriceList7day(){//读7日的莱特币价格信息表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today=simpleDateFormat.format(DateUtils.getDayBegin());
        Date date = new Date();
        String yesterday=simpleDateFormat.format(DateUtils.getFrontDay(date,1));//昨天
        String qianday=simpleDateFormat.format(DateUtils.getFrontDay(date,2));//前天
        String daQianday=simpleDateFormat.format(DateUtils.getFrontDay(date,3));//大前天
        String biggerQianday=simpleDateFormat.format(DateUtils.getFrontDay(date,4));//大大前天
        String biggestQianday=simpleDateFormat.format(DateUtils.getFrontDay(date,5));//大大大前天
        String biggerThanBiggestqianday=simpleDateFormat.format(DateUtils.getFrontDay(date,6));//大大大大前天
        // String sql="select * from btc_price";
        //String sql="select * from btc_price where time like '"+today+"%0' or time like '"+today+"%5'";
        String sql="select * from ltc_price where time like '"+today+"%0' or time like '"+today+"%5'"+"or time like '"+yesterday+"%0'"+"or time like '"+yesterday+"%5'"+"or time like '"+qianday+"%0'"+"or time like '"+qianday+"%5'"+"or time like'"+daQianday+"%0'"+"or time like '"+daQianday+"%5'"+"or time like '"+biggerQianday+"%0'"+"or time like '"+biggerQianday+"%5'"+"or time like '"+biggestQianday+"%0'"+"or time like '"+biggestQianday+"%5'"+"or time like '"+biggerThanBiggestqianday+"%0'"+"or time like '"+biggerThanBiggestqianday+"%5'";
        // System.out.print(sql);

        return(List<btcprice>) jdbcTemplate.query(sql, new RowMapper<btcprice>() {
            @Override
            public btcprice mapRow(ResultSet rs, int rowNum) throws SQLException{
                btcprice price=new btcprice();
                price.setPrice(rs.getFloat("price"));
                price.setTime(rs.getString("time"));
                // System.out.println(rs.getFloat("price"));
                //  System.out.println(rs.getString("time"));
                return price;
            }
        });
    }


}

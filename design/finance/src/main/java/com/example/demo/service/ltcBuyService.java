package com.example.demo.service;

import com.example.demo.CookieUtils;
import com.example.demo.btcprice;
import com.example.demo.exchangeRate;
import com.example.demo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ltcBuyService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getUsername(){//获取当前登录的用户身份
        CookieUtils cookieUtils=new CookieUtils();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String cookiestr=cookieUtils.getCookie(request,"username");

        return cookiestr;
    }

    public List<exchangeRate> getUSDrate(){//获取美元汇率信息
        String sql="select RATE from exchange_rate where type='USD'";
        return(List<exchangeRate>) jdbcTemplate.query(sql, new RowMapper<exchangeRate>() {
            @Override
            public exchangeRate mapRow(ResultSet rs, int rowNum) throws SQLException {
                exchangeRate user1=new exchangeRate();
                user1.setRate(rs.getFloat("RATE"));
                //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }
    public List<exchangeRate> getHKDrate(){//获取美元汇率信息
        String sql="select RATE from exchange_rate where type='HKD'";
        return(List<exchangeRate>) jdbcTemplate.query(sql, new RowMapper<exchangeRate>() {
            @Override
            public exchangeRate mapRow(ResultSet rs, int rowNum) throws SQLException {
                exchangeRate user1=new exchangeRate();
                user1.setRate(rs.getFloat("RATE"));
                //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }
    public List<btcprice> getBTCNewestPrice(){//获取美元汇率信息
        String sql="select PRICE from BTC_LTC_NEWEST_PRICE where TYPE='LTC'";
        return(List<btcprice>) jdbcTemplate.query(sql, new RowMapper<btcprice>() {
            @Override
            public btcprice mapRow(ResultSet rs, int rowNum) throws SQLException {
                btcprice user1=new btcprice();
                user1.setPrice(rs.getFloat("PRICE"));
                //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }

    public List<user> getBalance(String username){//获取用户的余额情况
        String sql="select balance from user where username='"+username+"';";
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

    public void setBalance(String username,float balance){//更新用户本金信息
        String balancestr = String.valueOf(balance);
        String insertSql = "update user set balance=" + balancestr + "where username='"+username+"';";
        int rows = jdbcTemplate.update(insertSql);
    }

    public void setHolding(String username,float count){//更新用户持有量信息
        String balancestr = String.valueOf(count);
        String insertSql = "update holding_"+username+" set LTC=" + balancestr +";";
        int rows = jdbcTemplate.update(insertSql);
    }

    public void addLTC(String username,float count){//增加用户当前持有的LTC
        //update holding_test set count=count+1 where code='btc';
        String countstr=String.valueOf(count);
        String insertSql="update holding_"+username+" set count=count+"+count+" where code='ltc'";
        int rows = jdbcTemplate.update(insertSql);
    }

    public void subLTC(String username,float count){//增加用户当前持有的LTC
        //update holding_test set count=count+1 where code='btc';
        String countstr=String.valueOf(count);
        String insertSql="update holding_"+username+" set count=count-"+count+" where code='ltc'";
        int rows = jdbcTemplate.update(insertSql);
    }

    public List<btcprice> getHolding(String username){
        String sql="select count from holding_"+username+" where code='ltc';";
        return(List<btcprice>) jdbcTemplate.query(sql, new RowMapper<btcprice>() {
            @Override
            public btcprice mapRow(ResultSet rs, int rowNum) throws SQLException {
                btcprice count1=new btcprice();
                count1.setPrice(rs.getFloat("count"));
                //  user1.setUsername(rs.getString("username"));
                return count1;


            }
        });
    }
    public void insertRecord(String username,String stockcode,float count,float price,float fee){
        String countstr=String.valueOf(count);
        String pricestr=String.valueOf(price);
        String feestr=String.valueOf(fee);
        String insertSql="insert into record (username,code,count,price,fee) values ('"+username+"','"+stockcode+"',"+countstr+","+pricestr+","+feestr+");";
        int rows=jdbcTemplate.update(insertSql);


    }
}

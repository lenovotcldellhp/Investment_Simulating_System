package com.example.demo.service;

import com.example.demo.CookieUtils;
import com.example.demo.Stock;
import com.example.demo.exchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import com.example.demo.service.btcBuyService;

@Service
public class stockbuyService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ltcBuyService ltcbuyService;

    public String getSina(String stockcode){
        try {
            URL url = new URL("http://hq.sinajs.cn/list="+stockcode);
            System.out.println("http://hq.sinajs.cn/list="+stockcode);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("请输入 URL 地址");
               // return;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;

            while ((current = in.readLine()) != null) {
                urlString += current;
                // System.out.println(current);
                // System.out.println("\n");
            }
            System.out.println("&+********");
            System.out.println(urlString);
            return urlString;
            // System.out.println(new String(urlString.getBytes("GBK")));
            //   System.out.println(new String(urlString.getBytes("GB2312"),"GBK"));
           // System.out.println("***********");
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }
    public String stocktype(String stockcode){//判定股票类型
        if(stockcode.indexOf("gb_")!=-1){
            return "US";
        }
        else if(stockcode.indexOf("hk")!=-1){
            return "HK";
        }
        else return "CN";
    }

    public String dataDivide(String information,String stocktype){//股票信息分割

        List<String> result = Arrays.asList(information.split(","));
        if(stocktype=="CN"){
            return result.get(3);
        }
        else if(stocktype=="HK"){
            return  result.get(6);
        }
        else return result.get(1);
    }

    public float getCurrentPrice(String stockcode){//获取股票实时价格，按人民币计价
        String information=this.getSina(stockcode);
        String stocktype=this.stocktype(stockcode);
        String price_str=this.dataDivide(information,stocktype);
        float original_price=Float.valueOf(price_str);
        float exchange_rate_usd=0;
        float exchange_rate_hkd=0;
        List<exchangeRate> list1=ltcbuyService.getUSDrate();
        for(int i=0;i<list1.size();i++){
            System.out.println("美元汇率是");
            exchange_rate_usd=list1.get(i).getRate();
            System.out.println(list1.get(i).getRate());
        }
        List<exchangeRate> list2=ltcbuyService.getHKDrate();
        for(int i=0;i<list2.size();i++){
            System.out.println("港币汇率是");
            exchange_rate_hkd=exchange_rate_usd=list2.get(i).getRate();
            System.out.println(list2.get(i).getRate());
        }
        if (stocktype=="CN"){
            return original_price;
        }
        else if(stocktype=="HK"){
            return  original_price*exchange_rate_hkd;
        }
        else{
            return original_price*exchange_rate_usd;
        }
    }

    public String getUsername(){//获取当前登录的用户身份
        CookieUtils cookieUtils=new CookieUtils();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String cookiestr=cookieUtils.getCookie(request,"username");

        return cookiestr;
    }

    public List<Stock> get_user_stock_list(String username, String stockcode){
        //获取某个用户的某个股票持有情况，如果数据库里无记录则返回空的list

        String sql="select * from holding_"+username+" where code='"+stockcode+"'";
        return(List<Stock>) jdbcTemplate.query(sql, new RowMapper<Stock>() {
            @Override
            public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
                Stock user1=new Stock();
                user1.setCount(rs.getFloat("count"));
                user1.setStockcode(rs.getString("code"));
                //  user1.setUsername(rs.getString("username"));
                return user1;
            }
        });
    }

    public void setHolding(String username,float count,String stockcode){//更新用户持有量信息
        String balancestr = String.valueOf(count);
        String insertSql = "update holding_"+username+" set count=" + balancestr +" where code='"+stockcode+"';";
        int rows = jdbcTemplate.update(insertSql);
    }

    public void insertHolding(String username,float count,String stockcode){
        String balancestr = String.valueOf(count);
        String insertSql = "insert into holding_"+username+"(code,count) values ('"+stockcode+"',"+balancestr+");";
        int rows = jdbcTemplate.update(insertSql);
    }
    public void insertRecord(String username,String stockcode,float count,float price,float fee){
        String countstr=String.valueOf(count);
        String pricestr=String.valueOf(price);
        String feestr=String.valueOf(fee);
        String insertSql="insert into record (username,code,count,price,fee) values ('"+username+"','"+stockcode+"',"+countstr+","+pricestr+","+feestr+");";
        int rows=jdbcTemplate.update(insertSql);


    }
}

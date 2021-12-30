package com.FastTrade.service;

import com.FastTrade.Model.Company;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class homeService {



    public List<List<String>> home() {
       List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();


        try {
            String user = "AppUser1";
            String password = "Abcd@1234";

            Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:snowflake://ie22889.ca-central-1.aws.snowflakecomputing.com/",
                    user, password)) {

                Statement stmt = con.createStatement();
                System.out.println("Connection Establish");
                String sql ="SELECT STOCK_SYMBOL,STOCK_TRADE_PRICE,STOCK_TRADE_TIMESTAMP\n" +
                        "FROM\n" +
                        "(SELECT STOCK_SYMBOL,STOCK_TRADE_PRICE,STOCK_TRADE_TIMESTAMP,\n" +
                        "row_number() OVER (partition by STOCK_SYMBOL order by STOCK_SYMBOL,STOCK_TRADE_TIMESTAMP DESC) as RN\n" +
                        "FROM STOCK_TRADE_TRAN ) AS vw\n" +
                        "WHERE RN=1;";


                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));


                   list1.add(rs.getString(1));
                   list2.add(rs.getString(2));




                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
        }
        list.add(list1);
        list.add(list2);

        System.out.println("LIST:"+list);
        return list;
    }

    public List<List<String>> getDetails(String symbol) {
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        try {
            String user = "AppUser1";
            String password = "Abcd@1234";

            Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:snowflake://ie22889.ca-central-1.aws.snowflakecomputing.com/",
                    user, password)) {

                Statement stmt = con.createStatement();
                System.out.println("Connection Establish");
                String sql ="SELECT TOP 5 STOCK_SYMBOL,STOCK_TRADE_PRICE,STOCK_TRADE_TIMESTAMP FROM\n" +
                        "(SELECT STOCK_SYMBOL,STOCK_TRADE_PRICE,STOCK_TRADE_TIMESTAMP\n" +
                        "FROM STOCK_TRADE_TRAN\n" +
                        "WHERE STOCK_SYMBOL='"+symbol+"'\n" +
                        "ORDER BY STOCK_TRADE_TIMESTAMP DESC);";


                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));


                    list1.add(rs.getString(1));
                    list2.add(rs.getString(2));
                    list3.add(rs.getString(3));




                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
        }
        list.add(list1);
        list.add(list2);
        list.add(list3);

        return list;
    }
}


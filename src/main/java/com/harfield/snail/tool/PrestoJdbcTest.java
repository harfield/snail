package com.harfield.snail.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by harfield on 2017/7/11.
 */
public class PrestoJdbcTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");
        Connection connection = DriverManager.getConnection("jdbc:presto://10.215.28.121:10080/hive/dws", "root", null);  ;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show tables ");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        connection.close();
    }

}

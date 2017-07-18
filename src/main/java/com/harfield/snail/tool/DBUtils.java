package com.harfield.snail.tool;


import java.sql.*;

/**
 * Created by harfield on 2016/8/19.
 */
public class DBUtils {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL="jdbc:mysql://10.215.28.5:3306/ad_report?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USERNAME="report";
    public static final String DB_PASSWORD="69PkFs7ty";


    public static void close(Statement statement) {
        if (statement != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                if (!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }
        }
    }


    public static void close(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        StringBuilder builder = new StringBuilder();
        try{
            connection = DBUtils.getConnection();
            psmt = connection.prepareStatement("select id,price from ad_fancy.ad_group where price>=0");
            resultSet = psmt.executeQuery();
            while (resultSet.next()){
                builder.append(resultSet.getLong(1)).append("\001").append(resultSet.getDouble(2)* 1000000L).append("\n");
            }

        }catch (Exception e){
            System.out.println("query ad_group price failed :" + e.getMessage());
        }finally {
            DBUtils.close(resultSet);
            DBUtils.close(psmt);
            DBUtils.close(connection);
        }
    }
}

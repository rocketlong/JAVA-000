package org.geek.week05.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;

public class JDBCDemo {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc_ad_data", "root", "root");
//            String sql = "SELECT * FROM ad_plan WHERE id = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, 11);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int userId = resultSet.getInt("user_id");
//                String planName = resultSet.getString("plan_name");
//                Date createTime = resultSet.getDate("create_time");
//                System.out.println("user_id :" + userId + ", plan_name :" + planName + ", createTime :" + formatDate(createTime));
//            }
            String sql = "INSERT INTO ad_plan (user_id, plan_name, plan_status, start_date, end_date, create_time, update_time) VALUES(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 3; i++) {
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setString(2, "preparedStatement" + i);
                preparedStatement.setInt(3, 1);
                preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
                preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));
                preparedStatement.setDate(6, new Date(new java.util.Date().getTime()));
                preparedStatement.setDate(7, new Date(new java.util.Date().getTime()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}

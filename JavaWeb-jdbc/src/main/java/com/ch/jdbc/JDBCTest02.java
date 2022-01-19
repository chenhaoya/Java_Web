/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/12 14:23
 * 开发名称：JDBCTest02
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.jdbc;

import java.sql.*;


public class JDBCTest02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /**配置信息*/
        String url = "jdbc:mysql://192.168.15.129:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //编写SQL
        String sql = "insert into users(id, name, password, email, birthday) value (?,?,?,?,?)";
        //获取发送sql对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,5);
        preparedStatement.setString(2,"陈5");
        preparedStatement.setString(3,"123456");
        preparedStatement.setString(4,"555@qq.com");
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));
        //执行SQL
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("成功");
        }
        preparedStatement.close();
        connection.close();

    }

}
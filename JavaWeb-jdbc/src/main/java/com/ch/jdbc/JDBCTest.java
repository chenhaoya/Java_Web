/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/12 13:50
 * 开发名称：JDBCTest
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.jdbc;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url = "jdbc:mysql://192.168.15.129:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库  connection代表数据
        Connection connection = DriverManager.getConnection(url, username, password);
        //向数据库发送SQL对象(不安全)CRUD   PreparedStatement(安全的)
        Statement statement = connection.createStatement();
        //编写SQL
//        String sql = "select * from users";
        String sql = "delete from users where id=4";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        //执行查询SQL 返回ResultSet : 结果集
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()) {
//            System.out.println("id="+resultSet.getObject("id"));
//            System.out.println("name="+resultSet.getObject("name"));
//            System.out.println("password="+resultSet.getObject("password"));
//            System.out.println("email="+resultSet.getObject("email"));
//            System.out.println("birthday="+resultSet.getObject("birthday"));
//        }
//        resultSet.close();
        statement.close();
        connection.close();
    }
}
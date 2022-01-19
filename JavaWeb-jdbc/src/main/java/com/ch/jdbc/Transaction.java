/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/12 14:47
 * 开发名称：Transaction
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.jdbc;

import org.junit.Test;

import java.sql.*;

public class Transaction {
    Connection connection=null;
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        try {
            String url = "jdbc:mysql://192.168.15.129:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            String sql = "update account set money=money+100 where name='a';";
            String sql2 = "update account set money=money-100 where name='b'";
            //开启事务
            connection.setAutoCommit(false);
            connection.prepareStatement(sql).executeUpdate();
            //制造错误
            int i = 1 / 0;
            connection.prepareStatement(sql2).executeUpdate();
            connection.commit();
            System.out.println("success");
        } catch (Exception e) {
            connection.rollback();
        }finally {
            connection.close();
        }
    }
}
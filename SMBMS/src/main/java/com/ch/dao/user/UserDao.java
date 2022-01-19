/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/13 12:38
 * 开发名称：UserDao
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.dao.user;

import com.ch.pojo.Role;
import com.ch.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    /**得到登录用于*/
    User getUser(Connection connection,String userCode);
    /**修改登录密码*/
    int upDataPwd(Connection connection, int id, String pwd);
    /**查询用户总数*/
    int getUserCount(Connection connection, String username, int userRole);
    /**获取用户列表*/
    List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize);
    /**增加用户*/
    int addUser(Connection connection, User user);
    /**更具userCode查询用户*/
    User getUserCode(Connection connection,String userCode);
    /**通过userID获取用户信息*/
    User getUserById(Connection connection, String userId);
}
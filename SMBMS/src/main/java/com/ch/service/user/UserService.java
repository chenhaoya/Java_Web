/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/13 13:02
 * 开发名称：UserService
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.service.user;


import com.ch.pojo.Role;
import com.ch.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    /**用户登录*/
    User login(String userCode,String password);
    /**更具用户id修改登录密码*/
    boolean updatePwd(int id, String pwd);
    /**查询记录数*/
    int getUserCount(String username, int userRoles);
    /**更具条件查询用户列表*/
    List<User> getUserList(String queryUserName, int queryUserRoles, int currentPageNo, int pageSize);
    /**增加用户*/
    boolean addUser(User user);
    /**更具userCode查询user*/
    User selectUserCodeExist(String userCode);
    /**通过userID获取用户信息*/
    User getUserById(String userId);
}
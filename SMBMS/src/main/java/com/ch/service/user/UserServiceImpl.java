/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/13 13:04
 * 开发名称：UserServiceImpl
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.service.user;

import com.ch.dao.BaseDAO;
import com.ch.dao.user.UserDao;
import com.ch.dao.user.UserDaoImpl;
import com.ch.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    //业务层都会调用DAO层，引入DAO层
    private UserDao userDao = null;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        connection = BaseDAO.getConnection();
        //通过业务层调用DAO层
        user = userDao.getUser(connection, userCode);
        if (user != null && user.getUserPassword().equals(password)) {
            return user;
        }
        BaseDAO.close(connection, null, null);
        return null;
    }

    @Override
    public boolean updatePwd(int id, String pwd) {
        Connection connection = null;
        connection=BaseDAO.getConnection();
        boolean flag = false;
        /*修改密码*/
        if (userDao.upDataPwd(connection, id, pwd)>0) {
            flag = true;
        }
        BaseDAO.close(connection, null, null);
        return flag;
    }

    @Override
    public int getUserCount(String username, int userRoles) {
        Connection connection = BaseDAO.getConnection();
        int userCount = userDao.getUserCount(connection, username, userRoles);
        BaseDAO.close(connection, null, null);
        return userCount;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRoles, int currentPageNo, int pageSize) {
        Connection connection = BaseDAO.getConnection();
        List<User> userList = null;
        userList = userDao.getUserList(connection, queryUserName, queryUserRoles, currentPageNo, pageSize);
        BaseDAO.close(connection, null, null);
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        int i = 0;
        Connection connection =null;
        try {
            connection = BaseDAO.getConnection();
            connection.setAutoCommit(false);
            i = userDao.addUser(connection, user);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDAO.close(connection,null,null);
        }
        if (i > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        Connection connection = BaseDAO.getConnection();
        User userCode1 = userDao.getUserCode(connection, userCode);
        BaseDAO.close(connection, null, null);
        return userCode1;
    }

    @Override
    public User getUserById(String userId) {
        Connection connection = BaseDAO.getConnection();
        User userById = userDao.getUserById(connection, userId);
        BaseDAO.close(connection, null, null);
        return userById;
    }
}
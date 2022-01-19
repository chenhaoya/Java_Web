/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/13 12:40
 * 开发名称：UserDaoImpl
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.dao.user;

import com.ch.dao.BaseDAO;
import com.ch.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(Connection connection, String userCode) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from smbms_user where userCode =?";
            Object[] params = {userCode};
            resultSet = BaseDAO.execute(connection, sql, params, resultSet, preparedStatement);
            try {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setCreationDate(resultSet.getTimestamp("creationDate"));
                    user.setModifyBy(resultSet.getInt("modifyBy"));
                    user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BaseDAO.close(null, resultSet, preparedStatement);
        }
        return user;
    }

    /**
     * 修改登录密码
     */
    @Override
    public int upDataPwd(Connection connection, int id, String pwd) {
        int execute = 0;
        if (connection != null) {
            PreparedStatement preparedStatement = null;
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object params[] = {pwd, id};
            execute = BaseDAO.execute(connection, sql, params, preparedStatement);
            BaseDAO.close(null, null, preparedStatement);
        }
        return execute;
    }

    /**
     * 根据用户名或角色查询用户总数
     */
    @Override
    public int getUserCount(Connection connection, String username, int userRole) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            //存放参数
            ArrayList<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append(" and u.userName like ?");
                list.add("%" + username + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole like ?");
                list.add(userRole);
            }
            Object[] objects = list.toArray();
            System.out.println(sql.toString());

            resultSet = BaseDAO.execute(connection, sql.toString(), objects, resultSet, preparedStatement);
            System.out.println(sql);
            try {
                if (resultSet.next()) {
                    /*从结果集中获的数量*/
                    count = resultSet.getInt("count");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BaseDAO.close(null, resultSet, preparedStatement);
        }
        return count;
    }

    /**
     * 获取用户列表
     */
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
//            sql.append("select u.*,r.roleName as u from smbms_user u,smbms_role r where u.userRole = r.id");
//            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            sql.append("select u.*,r.id, r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            /*            sql.append("select u.*,r.id from smbms_user u,smbms_role r where u.userRole = r.id");*/
            ArrayList<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }

            //在数据库中，分页使用 limit startIndex pageSize
            //当前页  （当前页-1）*页面大小
            //0,5    1   0    012345
            //6,5    2   5    26789
            //11,5   3   10
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            String s = sql.toString();
            System.out.println(s);
            Object[] objects = list.toArray();
            resultSet = BaseDAO.execute(connection, s, objects, resultSet, preparedStatement);

            try {
                while (resultSet.next()) {
                    User _user = new User();
                    _user.setId(resultSet.getInt("id"));
                    _user.setUserCode(resultSet.getString("userCode"));
                    _user.setUserName(resultSet.getString("userName"));
                    _user.setGender(resultSet.getInt("gender"));
                    _user.setBirthday(resultSet.getDate("birthday"));
                    _user.setPhone(resultSet.getString("phone"));
                    _user.setUserRole(resultSet.getInt("userRole"));
                    _user.setUserRoleName(resultSet.getString("userRoleName"));
                    userList.add(_user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            BaseDAO.close(null, resultSet, preparedStatement);
        }
        return userList;
    }

    @Override
    public int addUser(Connection connection, User user) {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into smbms_user" +
                    "(userCode, userName, userPassword, gender, birthday, " +
                    "phone, address, userRole, createdBy, creationDate)" +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object params[] = {user.getUserCode(), user.getUserName(), user.getUserPassword(),
                    user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(),
                    user.getUserRole(), user.getCreatedBy(), user.getCreationDate()};
            execute = BaseDAO.execute(connection, sql, params, preparedStatement);
            BaseDAO.close(null, null, preparedStatement);
        }
        return execute;
    }

    @Override
    public User getUserCode(Connection connection, String userCode) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "select * from smbms_user where userCode = ?";
        Object params[] = {userCode};
        resultSet = BaseDAO.execute(connection, sql, params, resultSet, preparedStatement);
        try {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close(null, resultSet, preparedStatement);
        }
        return user;
    }

    @Override
    public User getUserById(Connection connection, String userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "select u.*,r.roleName userRoleName from smbms_user u,smbms_role r where u.id = ? and r.id = u.userRole";
        Object parmas[] = {userId};
        if (connection != null) {
            resultSet = BaseDAO.execute(connection, sql, parmas, resultSet, preparedStatement);
            try {
                while (resultSet.next()){
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setCreationDate(resultSet.getTimestamp("creationDate"));
                    user.setModifyBy(resultSet.getInt("modifyBy"));
                    user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                    user.setUserRoleName(resultSet.getString("userRoleName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                BaseDAO.close(null, resultSet, preparedStatement);
            }
        }
        return user;
    }
}
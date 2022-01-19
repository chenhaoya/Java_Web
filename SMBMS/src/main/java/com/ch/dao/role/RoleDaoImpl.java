/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/15 13:27
 * 开发名称：RoleDaoImpl
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.dao.role;

import com.ch.dao.BaseDAO;
import com.ch.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Role> list = new ArrayList();
        if (connection != null) {
            String sql = "select * from smbms_role";
            Object params[] = {};
            resultSet = BaseDAO.execute(connection, sql, params, resultSet, preparedStatement);
            try {
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getInt("id"));
                    role.setRoleCode(resultSet.getString("roleCode"));
                    role.setRoleName(resultSet.getString("roleName"));
                    list.add(role);
                }
            } catch (SQLException e) {
            }
        }
        BaseDAO.close(null, resultSet, preparedStatement);
        return list;
    }
}
package com.ch.dao.role;

import com.ch.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {
    /**获取角色列表*/
    List<Role> getRoleList(Connection connection);
}

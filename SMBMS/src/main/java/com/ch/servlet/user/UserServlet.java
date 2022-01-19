/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/14 12:54
 * 开发名称：UserServlet
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.ch.pojo.Role;
import com.ch.pojo.User;
import com.ch.service.role.RoleService;
import com.ch.service.role.RoleServiceImpl;
import com.ch.service.user.UserService;
import com.ch.service.user.UserServiceImpl;
import com.ch.util.Constants;
import com.ch.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 实现Servlet复用
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null) {
            if (method.equals("add")) {
                this.add(req,resp);
            } else if (method.equals("savepwd")) {
                updatePwd(req, resp);
            } else if (method.equals("pwdmodify")) {
                pwdModify(req, resp);
            } else if (method.equals("query")) {
                this.query(req, resp);
            } else if (method.equals("getrolelist")) {
                this.getRoleList(req,resp);
            } else if (method.equals("ucexist")) {
                this.userCodeExist(req,resp);
            } else if (method.equals("view")) {
                this.viewUser(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 查看用户详细信息
     */
    private void viewUser(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        UserService userService = new UserServiceImpl();
        User userById = userService.getUserById(uid);
        req.setAttribute("user",userById);
        try {
            req.getRequestDispatcher("/statics/jsp/userview.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断新加入的用户是否可用
     */
    private void userCodeExist(HttpServletRequest req, HttpServletResponse resp) {
        String userCode = req.getParameter("userCode");
        UserService userService = new UserServiceImpl();
        User user = userService.selectUserCodeExist(userCode);
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(userCode)) {
            resultMap.put("userCode", "exist");
        } else {
            if (user != null) {
                resultMap.put("userCode", "exist");
            } else {
                resultMap.put("userCode", "unexist");
            }
        }
        //把resultMap转为json字符串的形式输出
        //配置上下文的输出类型
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();

    }

    /**获取用户列表*/
    private void getRoleList(HttpServletRequest req, HttpServletResponse resp) {
        List<Role> roleList =null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        //把rolelist转换为json对象输出
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONArray.toJSONString(roleList));
        writer.flush();
        writer.close();
    }

    /**添加用户*/
    private void add(HttpServletRequest req, HttpServletResponse resp){
        User user = new User();
        user.setUserCode(req.getParameter("userCode"));
        user.setUserName(req.getParameter("userName"));
        user.setUserPassword(req.getParameter("userPassword"));
        user.setGender(Integer.valueOf(req.getParameter("gender")));
        try {
//            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")));
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setCreationDate(new java.util.Date());
        user.setPhone(req.getParameter("phone"));
        user.setAddress(req.getParameter("address"));
        user.setUserRole(Integer.valueOf(req.getParameter("userRole")));
        user.setCreatedBy(((User) req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        UserService userService = new UserServiceImpl();
        boolean b = userService.addUser(user);
        if (b) {
            try {
                resp.sendRedirect(req.getContextPath() + "/jsp/user.do?method=query");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                req.getRequestDispatcher("useradd.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询用户列表 从前端获取数据
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) {
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();

        //第一次走这个请求一定是第一页，并且页面大小固定
        //可以写到配置文件中，方便后期修改
        //设置页面容量
        int pageSize = 6;
        //当前页码
        int currentPageNo = 1;

        if (queryUserName == null) {
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")) {
            //将前端传回来的职位解析为int，因为数据库中职位id类型时int
            //给查询赋值! 默认为0
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (NumberFormatException e) {
                System.out.println("currentPageNo");
                e.printStackTrace();
            }
        }


        /*获取用户的总数  分页：上一页，下一页 */
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        /*总页数*/
        PageSupport pageSupport = new PageSupport();
        //设置当前页面数量，从前端得到
        //设置当前页码
        pageSupport.setCurrentPageNo(currentPageNo);
        //设置页总大小
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        //设置页总数量
//        pageSupport.setTotalPageCount(totalCount);

        //控制首页和尾页
        int totalPageCount = pageSupport.getTotalPageCount();
        //控制首页和尾页
        //如果页面小于1，就显示第一页
        if (totalPageCount < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            //当前页面大于最后一页，让它为最后一页就行
            currentPageNo = totalPageCount;
        }
        /*获取用户列表展示*/
        List<User> userList;
        userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList", userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);

        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);

        req.setAttribute("queryUserName", queryUserName);
        req.setAttribute("queryUserRole", queryUserRole);

        try {
            req.getRequestDispatcher("/statics/jsp/userlist.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改密码
     */
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (attribute != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) attribute).getId(), newpassword);
            if (flag) {
                req.setAttribute("message", "密码修改成功，请重新登录");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            req.setAttribute("message", "新密码有问题");
        }
//        resp.sendRedirect(req.getContextPath()+"/logout.do");
        try {
            req.getRequestDispatcher("/statics/jsp/pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 验证旧密码
     */
    private void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map
        HashMap<String, String> resultMap = new HashMap<>();
        if (attribute == null) {
//            Session失效了，session过期了
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");
        } else {
//          Session中用户的密码
            String userPassword = ((User) (attribute)).getUserPassword();
            if (userPassword.equals(oldpassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/13 13:50
 * 开发名称：LoginServlet
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.servlet.user;

import com.ch.pojo.User;
import com.ch.service.user.UserService;
import com.ch.service.user.UserServiceImpl;
import com.ch.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    /**控制层，调用业务层代码*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("LoginServlet");
        String username = req.getParameter("userCode");
        String password = req.getParameter("userPassword");
        /*和数据库中的密码进行对比,调用业务层*/
        UserService userService = new UserServiceImpl();
        /*返回查询出的用户*/
        User login = userService.login(username, password);
        //ogin.getUserPassword().equals(password)
        if (login != null) {
            /*将用户信息放到session中*/
            req.getSession().setAttribute(Constants.USER_SESSION, login);
            /*跳转到主页*/
            resp.sendRedirect(req.getContextPath() + "/statics/jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
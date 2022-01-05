/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2021/12/10 23:19
 * 开发名称：HelloServlet
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**this.getInitParameter() 初始化参数
        this.getServletConfig() Servlet配置
        this.getServletContext() Servlet上下文*/
        ServletContext servletContext = this.getServletContext();

        String username = "陈";
        /*将一个数据保存在ServletContext中，名字为：username，值为username对象*/
        servletContext.setAttribute("username",username);

        System.out.println("helloServlet");
        resp.getWriter().println("helloServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
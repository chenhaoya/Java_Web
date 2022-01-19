/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/6 14:20
 * 开发名称：SessionDemo01
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.servlet;

import com.ch.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //结局乱码问题
/*        req.setCharacterEncoding("utf-16");
        resp.setCharacterEncoding("utf-16");
        resp.setContentType("text/html");*/
        resp.setContentType("text/html; charset=UTF-16");
        //得到Session
        HttpSession session = req.getSession();
        //给Session中村东西
        session.setAttribute("name", new Person("陈浩",22));
        //获取session的id
        String id = session.getId();

        //判断是否是新创建的session
        if (session.isNew()) {
            resp.getWriter().write("session创建成功，ID：" + id);
        } else {
            resp.getWriter().write("session在服务器中已存在，ID：" + id);
        }
        /**创建session做了什么事情
        Cookie cookie = new Cookie("JSESSIONID",id);
        resp.addCookie(cookie);*/
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
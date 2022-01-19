/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/6 13:39
 * 开发名称：Cookie_ZH
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：Cookie传递中文信息
 */
package com.ch.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Cookie_ZH extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-16");
        resp.setCharacterEncoding("utf-16");

        Cookie[] cookies = req.getCookies();
        PrintWriter out = resp.getWriter();

        if (cookies != null) {
            out.write("上一次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("name")) {
                    System.out.println(cookie.getValue());
                    out.write(cookie.getValue());
                }
            }
        } else {
            out.write("这是第一次访问本站");
        }
        Cookie cookie = new Cookie("name","陈浩");
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
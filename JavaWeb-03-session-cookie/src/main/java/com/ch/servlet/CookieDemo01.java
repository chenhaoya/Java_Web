/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/5 16:26
 * 开发名称：CookieDemo01
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：客户端技术
 */
package com.ch.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**保存用户上一次访问的时间*/
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //结局中文乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        //响应，输出对象
        PrintWriter out = resp.getWriter();

        //coolie 服务端从客户端获取；
        //这里返回数组，说明cookie可能存在多个
        Cookie[] cookies = req.getCookies();

        //判断cookie是否存在
        if (cookies != null) {
            //服务端存在cookie
            out.write("你上一次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的键
                if (cookie.getName().equals("lastLoginTime")) {
                    //根据cookie的键，获得值
                    long l = Long.parseLong(cookie.getValue());
                    Date date = new Date(l);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("这是你第一次访问网站");
        }
        //服务给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", String.valueOf(System.currentTimeMillis()));

        //设置cookie有效为一天
        cookie.setMaxAge(24*60*60);

        //将cookie添加到服务端
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
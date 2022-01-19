/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/9 16:14
 * 开发名称：CharacterEncodingFilter
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter{
    /**初始化*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化了");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //chain 链
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("CharacterEncodingFilter执行前");
        /*让我们我的程序继续运行,如果不写,程序到这里就被拦停了*/
        chain.doFilter(request,response);
        System.out.println("CharacterEncodingFilter执行后");
    }
    /**销毁:web服务器关闭的时候,过滤器销毁*/
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁了");
    }
}
/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/11 12:46
 * 开发名称：OnlineCountListener
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**统计网站在线人数:监听session的数量*/
public class OnlineCountListener implements HttpSessionListener {
    private volatile Integer onlineCount;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null) {
            synchronized (OnlineCountListener.class) {
                if (onlineCount == null) {
                    this.onlineCount = new Integer(1);
                }
            }
        } else {
            synchronized (OnlineCountListener.class) {
                int count = onlineCount.intValue();
                onlineCount = new Integer(count + 1);
            }
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null) {
            synchronized (OnlineCountListener.class) {
                if (onlineCount == null) {
                    this.onlineCount = new Integer(0);
                }
            }
        } else {
            synchronized (OnlineCountListener.class) {
                se.getSession().invalidate();
                int count = onlineCount.intValue();
                onlineCount = new Integer(count - 1);
            }
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }
    /*
    * Session 销毁方式:
    *   1. 手动销毁 : se.getSession().invalidate();
    *   2. 自动销毁 : 在web.xml 中配置Session过期时间
    * */
}
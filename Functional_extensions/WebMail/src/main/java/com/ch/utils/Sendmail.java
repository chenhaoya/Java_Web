/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/18 16:52
 * 开发名称：Sendmail
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch.utils;

import com.ch.POJO.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**网站不超过三秒：用户体验
 * 多线程实现用户体验  （异步处理）*/
public class Sendmail extends Thread{

    //用于给用户发送邮件的邮箱
    private String from = "ch111222@qq.com";
    //邮箱用户名
    private String userName = "ch111222@qq.com";
    //邮箱密码
    private String password = "onizqhhntsydcjde";
    //发送邮件的服务器地址
    private String host = "smtp.qq.com";

    private User user;

    public Sendmail(User user) {
        this.user = user;
    }

    //重写run方法，在run方法中发送邮件给指定用户

    @Override
    public void run() {
        Properties prop = new Properties();
        /*设置qq邮箱服务器*/
        prop.setProperty("mail.host","smtp.qq.com");
        /*邮件发送协议*/
        prop.setProperty("mail.transport.protocol", "smtp");
        /*需要验证用户名密码*/
        prop.setProperty("mail.smtp.auth", "true");

        try {
            /*设置SSL加密*/
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            /*使用JavaMail发送邮件的5个步骤*/
            /*创建定义整个应用程序所需要的环境信息的Session对象*/
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ch111222@qq.com", "onizqhhntsydcjde");
                }
            });
            /*开启Session的debug模式，可以看到程序发送Email的运行状态*/
            session.setDebug(true);
            /*通过session得到transport对象*/
            Transport transport = session.getTransport();
            /*使用有效的用户名和授权码脸上邮件服务器*/
            transport.connect("smtp.qq.com", "ch111222@qq.com", "onizqhhntsydcjde");
            /*创建邮件 :写邮件*/
            /*创建邮件对象*/
            MimeMessage message = new MimeMessage(session);
            /*指明邮件的发件人*/
            message.setFrom(new InternetAddress(from));
            /*指明邮件的收件人，现在发件人和收件人是一样的，给自己发*/
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            /*设置邮件主题*/
            message.setSubject("用户注册邮件");
            String info = "恭喜你注册成功，用户名："+user.getUsername()+"，密码："+user.getPassword();
            /*设置邮件的文本内容*/
            message.setContent(info, "text/html;charset=utf-8");
            /*发送邮件*/
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2022/1/18 15:40
 * 开发名称：MailDemo01
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */
package com.ch;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**发送一个简单的邮件*/
public class MailDemoPhoto {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        /*设置qq邮箱服务器*/
        prop.setProperty("mail.host","smtp.qq.com");
        /*邮件发送协议*/
        prop.setProperty("mail.transport.protocol", "smtp");
        /*需要验证用户名密码*/
        prop.setProperty("mail.smtp.auth", "true");

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
                return new PasswordAuthentication("ch111222@qq.com","onizqhhntsydcjde");
            }
        });
        /*开启Session的debug模式，可以看到程序发送Email的运行状态*/
        session.setDebug(true);
        /*通过session得到transport对象*/
        Transport transport = session.getTransport();
        /*使用有效的用户名和授权码脸上邮件服务器*/
        transport.connect("smtp.qq.com","ch111222@qq.com","onizqhhntsydcjde");
        /*创建邮件 :写邮件*/
        /*创建邮件对象*/
        MimeMessage message = new MimeMessage(session);
        /*指明邮件的发件人*/
        message.setFrom(new InternetAddress("ch111222@qq.com"));
        /*指明邮件的收件人，现在发件人和收件人是一样的，给自己发*/
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("ch111222@qq.com"));
        /*设置邮件主题*/
        message.setSubject("htllo");
        //==============================================
        /*准备图片数据*/
        MimeBodyPart img = new MimeBodyPart();
        /*图片需要经过数据处理：DataHandler*/
        DataHandler dh = new DataHandler(new FileDataSource("E:\\JavaWeb\\Functional_extensions\\mail-java\\src\\a.png"));
        /*在Body中放入处理的图片数据*/
        img.setDataHandler(dh);
        /*给图片设置ID，在后续使用*/
        img.setContentID("bz.jpg");
        /*准备正文数据*/
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<img src='cid:bz.jpg'>的邮件","text/html;charset=utf-8");
        /*描述数据关系*/
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(img);
        mm.setSubType("related");
        /*设置到消息中*/
        message.setContent(mm);
        message.saveChanges();
        //==============================================
        /*发送邮件*/
        transport.sendMessage(message,message.getAllRecipients());

        transport.close();
    }
}
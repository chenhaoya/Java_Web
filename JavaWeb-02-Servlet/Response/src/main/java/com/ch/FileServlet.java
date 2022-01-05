package com.ch;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 开发团队：复仇者联盟
 * 开发团队领导人：陈浩
 * 开发人员姓名：陈浩
 * 学号/工号：2019112102
 * 个人/公司邮箱：ch111222@qq.com
 * 时间：2021/12/25 21:14
 * 开发名称：filedown
 * 开发工具：IntelliJ IDEA
 * 当前用户：CH
 * 描述：
 */

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 要获取下载文件的路径
        String realPath = "E:\\JavaWeb\\JavaWeb-02-Servlet\\Response\\src\\main\\resources\\陈.png";
        //2. 下载的文件名？
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3. 设置想办法让浏览器能够支持下载我们需要的东西
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        //4. 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5. 创建缓冲区
        int lan = 0;
        byte[] buffer = new byte[1024];
        //6. 获得OutPutStream对象
        ServletOutputStream out = resp.getOutputStream();
        //7. 将FileOutPutStream流写入到buffer缓冲区,使用OutPutStream将缓冲区的数据输出到客户端
        while ((lan = in.read(buffer)) > 0) {
            out.write(buffer,0,lan);
        }
        out.close();
        in.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
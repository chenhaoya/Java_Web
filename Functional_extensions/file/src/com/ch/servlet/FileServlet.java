package com.ch.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

//后台代码，表单点击提交经过web.xml映射到这个servlet。
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //文件上传不能用get，有大小限制
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断上传的文件是普通的表单还是带multipart/属性的表单
        if(!ServletFileUpload.isMultipartContent(req)) {
            return;
            //终止方法运行。栈顶指针回调。
            //普通表单，直接返回。这一步也就是所谓的安全性检查，在工程代码中一开始其实有很多if进行安全性检查。
        }

        //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件；TODO 文件上传调优一
        //File uploadFile = new File(req.getContextPath() + "/WEB-INF/upload");经过检测上传位置不对

        File uploadFile = new File(req.getServletContext().getRealPath("") + "/WEB-INF/upload");

        if(!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        //临时路径，假如文件超过了预期的大小，我们就把他放到一个临时文件中，过几天自动删除，或者提醒用户转存为永久
        File tempFile = new File(req.getServletContext().getRealPath("") + "/WEB-INF/temp");
        if(!tempFile.exists()) {
            tempFile.mkdirs();
        }

        //1.创建DiskFileItemFactory对象，处理文件上传路径或者大小限制的；
        DiskFileItemFactory diskFileItemFactory = getDiskFileItemFactory(tempFile);
        //2.获取ServletFileUpload
        ServletFileUpload upload = getServletFileUpload(diskFileItemFactory);

        //3.处理上传的文件
        String msg = uploadParseRequest(upload, req, req.getServletContext().getRealPath("") + "/WEB-INF/upload");

        req.setAttribute("msg",msg);
        //req.getRequestDispatcher(req.getContextPath() + "/msg.jsp").forward(req,resp);
        req.getRequestDispatcher("/info.jsp").forward(req,resp);
        //这个转发不需要加子项目路径，否则重复,因为转发肯定在当前子项目下
    }

    private DiskFileItemFactory getDiskFileItemFactory(File tempFile) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将他放到临时文件中；TODO 调优三
        factory.setSizeThreshold(1024 * 1024);//缓存区大小为1M
        factory.setRepository(tempFile);//临时目录的保存目录
        return factory;
    }

    private ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度；
        upload.setProgressListener(new ProgressListener() {
            @Override
            //pBytesRead:已经读取到的文件大小
            //pContentLength ： 文件大小
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
            }
        });

        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值 TODO 调优三
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);

        return upload;
    }

    private String uploadParseRequest(ServletFileUpload upload,HttpServletRequest request,String uploadPath) {
        String msg = "";
        try {
            //把前端请求解析，封装成一个FileItem对象
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if(fileItem.isFormField()) {//判断包裹在这个form表单里的所有元素，区分哪个带/不带文件。
                    //getFieldName指的是前端表单控件的name；
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8"); //处理乱码
                    System.out.println(name+":"+value);
                    //例如我们例子第一项是输入的用户名。
                    //可以在这里switch 进行判断然后操作。
                } else {    //有file属性，带文件
                    //=======================处理文件===============================//
                    //拿到文件名字
                    String uploadFileName = fileItem.getName();
                    System.out.println("上传的文件名："+uploadFileName);
                    if (uploadFileName == null || uploadFileName.trim().length() == 0) {
                        continue;
                    }
                    //获得上传的文件名  /images/girl/paojie.png
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
                    //获得文件的后缀名
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                    /*
                        如果文件后缀名 fileExtName 不是我们所需要的
                        就直接return，不处理，告诉用户文件类型不对。 TODO 优化四
                    */

                    System.out.println("文件信息 [文件名："+fileName+"---文件类型"+fileExtName+"]");

                    //可以使用UUID（唯一识别的通用码），保证文件名唯一；
                    //UUID.randomUUID()，随机生一个唯一识别的通用码；
                    String uuidPath = UUID.randomUUID().toString();

                    //=======================处理文件完毕===============================//

                    //存到哪？ uploadPath
                    //文件真实存在的路径 realPath
                    String realPath =   uploadPath+"\\"+uuidPath;   //TODO 优化二 使用UUID和包机制保证唯一
                    //给每个文件创建一个对应的文件夹
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()){
                        realPathFile.mkdirs();
                    }

                    //=======================存放地址完毕===============================//

                    //获得文件上传的流
                    InputStream inputStream = fileItem.getInputStream();
                    //创建一个文件输出流
                    //realPath = 真实的文件夹；
                    //差了一个文件; 加上输出文件的名字+"/"+uuidFileName
                    FileOutputStream fos = new FileOutputStream(realPath+"\\"+fileName);

                    //创建一个缓冲区
                    byte[] buffer = new byte[1024*1024];
                    //判断是否读取完毕
                    //int len = 0;
                    int len;
                    //如果大于0说明还存在数据；
                    while ((len=inputStream.read(buffer))>0){
                        fos.write(buffer,0,len);
                    }
                    //关闭流
                    fos.close();
                    inputStream.close();
                    msg = "文件上传成功！";
                    fileItem.delete(); //上传成功，清除临时文件
                    //=======================文件传输完毕===============================//
                }
            }
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}


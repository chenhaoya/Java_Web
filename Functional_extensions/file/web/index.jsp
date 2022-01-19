<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/18
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>


<%--  通过表单上传文件
    get： 上传文件大小有限制
    post：上传文件没有大小限制
  --%>

<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
  上传用户：<input type="text" name="username"><br/>
  上传文件1：<input type="file" name="file1"><br/>
  上传文件2：<input type="file" name="file2"><br/>
  <p><input type="submit" value="提交"> | <input type="reset" name="重置"></p>
</form>




  </body>
</html>

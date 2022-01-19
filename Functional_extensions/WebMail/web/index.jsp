<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/18
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
    用户名：<input type="text" name="username"><br/>
    密 码：<input type="password" name="password"><br>
    邮 箱：<input type="text" name="email"><br>
    <input type="submit" value="注册">
  </form>
  </body>
</html>

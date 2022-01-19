<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/9
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  <h1>当前在线人数:</h1>
<%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%>

  </body>
</html>

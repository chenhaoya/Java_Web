<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/8
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>jsptag1</h1>
<%--页面转发的同时携带参数--%>
<%--http://localhost:8080/JavaWeb_jsp_war_exploded/jsptag.jsp?name=chen&age=2--%>
<jsp:forward page="jsptag2.jsp">
    <jsp:param name="name" value="chen"/>
    <jsp:param name="age" value="22"/>
</jsp:forward>
</body>
</html>

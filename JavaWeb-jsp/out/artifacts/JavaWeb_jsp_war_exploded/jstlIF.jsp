<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/8
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%--引用JSTL核心标签库--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>if测试</h1>
<hr>
<form action="jstlIF.jsp" method="get">
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>
<%--if判断,如果用户是admin
<%
    if (request.getParameter("username").equals("admin")){
        out.print("登录成功");
    }else{
        out.print("不是管理员");
    }
%>
--%>
<%--var 不能为中文--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员"/>
</c:if>
<c:out value="${isAdmin}"/>

</body>
</html>

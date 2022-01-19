<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/8
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List people = new ArrayList<String>();
    people.add(0, "aaa");
    people.add(1, "bbb");
    people.add(2, "ccc");
    people.add(3, "ddd");
    people.add(4, "eee");
    request.setAttribute("list", people);
%>
<%--var：；每一次遍历处理来的元素
    items：要遍历的对象--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>

</body>
</html>

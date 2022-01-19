<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/8
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page isELIgnored="false" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="score" value="85"/>
<c:choose>
  <c:when test="${score>=90}">
    你的成绩为优秀
  </c:when>
  <c:when test="${score>=70}">
    你的成绩为良好
  </c:when>
  <c:when test="${score>=60}">
    你的成绩为及格
  </c:when>
  <c:when test="${score<60}">
    你的成绩为不及格
  </c:when>
</c:choose>

</body>
</html>

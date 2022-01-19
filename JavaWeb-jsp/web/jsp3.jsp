<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/7
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="common/header.jsp" %>
    <h1>网页主体</h1>
    <%@ include file="common/footer.jsp"%>
    <hr>
    <jsp:include page="/common/header.jsp"/>
    <h1>网页主体</h1>
    <jsp:include page="/common/footer.jsp"/>
</body>
</html>

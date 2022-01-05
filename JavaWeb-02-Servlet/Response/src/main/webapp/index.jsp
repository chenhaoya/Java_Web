
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<%--这里的路径需要寻找到项目的路径--%>
<%--代表当前项目：${pageContext.request.contextPath}--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name="username"><br>
    密 码：<input type="password" name="password"><br>
    <input type="submit">
</form>
</body>
</html>

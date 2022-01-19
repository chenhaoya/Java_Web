<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/5
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username" required="required"><br>
        密 码：<input type="password" name="password" required="required"><br>
        爱 好：
        <input type="checkbox" name="hobbys" value="女孩"> 女孩
        <input type="checkbox" name="hobbys" value="唱歌"> 唱歌
        <input type="checkbox" name="hobbys" value="代码"> 代码
        <input type="checkbox" name="hobbys" value="健身"> 健身
        <br>
        <input type="submit">
    </form>
</div>
</body>
</html>

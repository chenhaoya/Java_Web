<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/7
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--内置对象--%>
<%
    pageContext.setAttribute("name1","chen1"); //保存的数据只在一个页面中有效
    request.setAttribute("name2","chen2");     //保存的数据只在一个请求中有效,请求转发会携带这个数据
    session.setAttribute("name3","chen3");     //保存的数据只在一次会话中有效,从打开浏览器到关闭浏览器
    application.setAttribute("name4","chen4"); //保存的数据只在服务器中有效,  从打开服务器到关闭服务器
%>
<%
    //通过pageContext取东西
    String name11 = (String) pageContext.findAttribute("name1");
    String name22 = (String) pageContext.findAttribute("name2");
    String name33 = (String) pageContext.getAttribute("name3");
    String name44 = (String) pageContext.getAttribute("name4");
    String name55 = (String) pageContext.getAttribute("name5");
%>
<%--使用EL表达式输出--%>
<h1>取出的值为:</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3><%=name55%></h3>
</body>
</html>

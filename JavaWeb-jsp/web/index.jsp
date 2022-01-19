<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/7
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  JSP表达式--%>
  <%=new Date()%>>
<hr>
<%--  JSP脚本片段--%>
  <%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum+=i;
    }
    out.println("<h1> sum="+sum+"<h1>");
  %>
  <%
    int x = 10;
    out.println(x);
  %>
  <%
    out.println(x);
  %>
  <hr>
<%--  在代码中嵌入HTML代码--%>
  <%
    for (int i = 0; i < 5; i++) {
  %>
  <h1>hello,word <%=i%></h1>
  <%
    }
  %>
  <%!
    static {
      System.out.println("Loading Servlet");
    }
    private static int global = 1;
    public void chen(){
      System.out.println("进入了方法chen");
    }
  %>
  </body>
</html>

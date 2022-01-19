<%@ page import="com.ch.pojo.People" %>
Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/9
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
//    People people = new People();
//    people.setAddress("六安");
//    people.setAge(5);
//    people.setId(0);
//    people.setName("chen");
%>
<%--与下面等价--%>
<jsp:useBean id="people"  class="com.ch.pojo.People" scope="page"/>
<jsp:setProperty name="people" property="address" value="六安"/>
<jsp:setProperty name="people" property="id" value="0"/>
<jsp:setProperty name="people" property="age" value="5"/>
<jsp:setProperty name="people" property="name" value="chen"/>

<br>
ID：<jsp:getProperty name="people" property="id"/>
<br>
姓名：<jsp:getProperty name="people" property="name"/>
<br>
年龄：<jsp:getProperty name="people" property="age"/>
<br>
地址：<jsp:getProperty name="people" property="address"/>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: CH
  Date: 2022/1/13
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Ajax</title>
    <%--使用jQuery，导入 注意路径问题--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.0.js"/>
  </head>
  <body>

  <script type="text/javascript">

    function a1() {
      $.ajax({
        // 发给服务器
        url:"${pageContext.request.contextPath}/ajax/a1",
        data:{"name":}
      });
      //获得文本框输入的值
      ${"textName"}.val();


      // 接收服务器返回的数据

    }

  </script>

用户名：
<%--onabort:失去焦点产生事件--%>
<input type="text" id="textName" onabort="a1()">

  </body>
</html>

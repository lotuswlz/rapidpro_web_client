<%--
  Created by IntelliJ IDEA.
  User: lzwu
  Date: 2/3/16
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h3>Register</h3>
<%=request.getAttribute("message")%>
<p>
  <a href="<%=request.getContextPath()%>">back</a>
</p>
</body>
</html>

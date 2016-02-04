<%--
  Created by IntelliJ IDEA.
  User: lzwu
  Date: 2/3/16
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp"%>
<html>
<head>
    <title>Settings</title>
  <style>
    .tb td {
      border-collapse: collapse;
      border: 1px solid black;
      padding: 3px;
    }
  </style>
</head>
<body>
<h1>Settings</h1>
<form action="<%=basePath%>/settings" method="post">
<table class="tb">
  <tr>
    <td>RapidPro URL:</td>
    <td><input type="text" name="rapidProUrl" value="<%=request.getAttribute("rapidProUrl")%>"/></td>
  </tr>
  <tr>
    <td>Receiver URL:</td>
    <td><%=request.getAttribute("rapidProReceivedUrl")%></td>
  </tr>
  <tr>
    <td>Channel:</td>
    <td><input type="text" name="channelId" value="<%=request.getAttribute("channelId")%>"/></td>
  </tr>
  <tr>
    <td>Channel Name:</td>
    <td><input type="text" name="channelName" value="<%=request.getAttribute("channelName")%>"/></td>
  </tr>
  <tr>
    <td>Channel UUID:</td>
    <td><input type="text" name="channelUUID" value="<%=request.getAttribute("channelUUID")%>" size="50"/></td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="submit" value="Save"/>
    </td>
  </tr>
</table>
</form>
<p>
  <a href="<%=basePath%>/home">Home</a>
</p>
</body>
</html>

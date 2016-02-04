<%@ page import="java.util.List" %>
<%@ page import="cathywu.rapidpro.webclient.models.Message" %>
<%@ page import="cathywu.rapidpro.webclient.models.Direction" %>
<%--
  Created by IntelliJ IDEA.
  User: lzwu
  Date: 2/4/16
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp"%>
<html>
<head>
    <script src="<%=basePath%>/static/js/jquery.min.js"></script>
    <meta http-equiv="refresh" content="10">
</head>
<body>
<table width="100%">
    <tr>
        <td style="width: 80px;">&nbsp;</td>
        <td style="width: auto;">&nbsp;</td>
        <td style="width: 80px;">&nbsp;</td>
    </tr>
    <%
        List<Message> messages = (List<Message>) session.getAttribute("messages");
        if (messages != null && messages.size() > 0) {
            for (Message message : messages) {
                if (message.getDirection().getValue() == Direction.TO_USER.getValue()) {
    %>
    <tr style="text-align: left">
        <td><%=message.getDatetimeStr()%></td>
        <td colspan="2">&nbsp;</td>
    </tr>
    <tr style="text-align: left">
        <td colspan="2" style="padding-left: 150px;"><%=message.getContent()%>
        </td>
        <td>&nbsp;</td>
    </tr>
    <%
    } else {
    %>
    <tr style="text-align: right">
        <td colspan="2">&nbsp;</td>
        <td><%=message.getDatetimeStr()%>
        </td>
    </tr>
    <tr style="text-align: right">
        <td>&nbsp;</td>
        <td colspan="2" style="padding-right: 150px;"><%=message.getContent()%></td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
</body>
<script type="text/javascript">

</script>
</html>

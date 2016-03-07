<%--
  Created by IntelliJ IDEA.
  User: lzwu
  Date: 2/4/16
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp" %>
<html>
<head>
    <title>Receiver: <%=session.getAttribute("userId")%>
    </title>
    <script src="<%=basePath%>/static/js/jquery.min.js"></script>
</head>
<body>
<h1>Receiver (<%=session.getAttribute("phoneNumber")%>) &nbsp; <input type="button" id="clearHistory" value="Clear History"/></h1>
<iframe src="<%=basePath%>/show_messages" width="90%" height="70%"></iframe>
<p>&nbsp;</p>
<input type="hidden" id="phoneNumber" name="phoneNumber" value="<%=session.getAttribute("phoneNumber")%>"/>
<textarea name="content" id="content" style="width: 90%; height: 80px;"></textarea>
<br/>
<input id="btnReply" type="button" value="Send" style="width: 120px;"/>

<script type="text/javascript">
    $(document).ready(function() {
        basePath = '<%=basePath%>';
    });

    $('#btnReply').click(function(){

        var dataJson = {
            'from': $('#phoneNumber').val(),
            'text': $('#content').val()
        };

        $.ajax({
            type: "POST",
            url: basePath + "/reply",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(dataJson),
            success: function (result) {
                location.reload();
                console.log(result)
            },
            error: function(xhr, status, error) {
                location.reload();
                console.log(xhr.responseText)
            }
        });

    });

    $("#clearHistory").click(function(){
        $.ajax({
            type: "DELETE",
            url: basePath + "/messages/" + $('#phoneNumber').val(),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (result) {
                alert("123");
                location.reload();
                console.log(result)
            },
            error: function(xhr, status, error) {
                location.reload();
                console.log(xhr.responseText)
            }
        });

    });
</script>
</body>
</html>

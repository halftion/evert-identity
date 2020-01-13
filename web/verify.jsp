<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2020/1/9
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设置会话秘钥</title>
    <script src="./js/jsencrypt.js"></script>
    <script src="./js/login.js"></script>
    <script src="./js/jquery-3.3.1.min.js"></script>
</head>
<body>
    <script src="./js/jquery.cookie.js"></script>
    <form id="verify" action="verify" method="post">
        <input name="success" id="success" type="text" value="success" hidden>
        <input type="submit">
    </form>
    <script>
        var message = $.cookie("info");
        var key =<%
         String key = request.getSession().getId();
         out.println("\""+key+"\"");
         %>;
        alert("会话秘钥：" + key);
        localStorage.setItem("sessionKey", key);
        $("#verify").submit();
        verify(key,message);
        var success = encrypt(sessionStorage.getItem("key"));
        $("#verify").submit();
    </script>
</body>
</html>

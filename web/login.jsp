<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/11/27
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
        <meta name="keywords" content="异闻">
        <link href="css/reset.css" rel="stylesheet">
        <link href="css/log_style.css" rel="stylesheet">
        <script src="./js/jsencrypt.js"></script>
        <script src="./js/login.js"></script>
        <script src="./js/jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <div class="container">
            <!-- 注册界面 -->
            <div class="signbox">
                <h1>异&nbsp;闻</h1>
                <h1>EVENT</h1>
                <form class="login" action="login" method="POST" id="form">
                    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="手机号">
                    <div class="hint">6~14个字符</div>
                    <input type="password" name="Password" placeholder="登录密码">
                    <div class="hint">6~16个字符</div>
                    <button onclick="submits()">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                </form>
                <a href="./signup.jsp">未有账号？注册</a>
                <a href="#">忘记密码？</a>
            </div>
            <p class="copyright">版权所有 © 2019-现在 shijiewubaiqiang.top</p>
            <!-- 动态方块 -->
            <ul class="background-pattern">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/11/27
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cn">
    <head>
        <title>注册</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
        <meta name="keywords" content="异闻">
        <link href="css/reset.css" rel="stylesheet">
        <link href="css/log_style.css" rel="stylesheet">
    </head>
    <body>
        <c:if test="${state==500}">
            <script>
                alert("注册失败，请检查输入是否正确");
            </script>
        </c:if>
        <div class="container">
            <!-- 注册界面 -->
            <div class="signbox">
                <h1>异&nbsp;闻</h1>
                <h1>EVENT</h1>
                <form id="sign" method="post" action="sign" class="login">
                    <input type="text" id="Phone" name="Phone" placeholder="请输入手机号">
                    <div class="hint">11个字符</div>
                    <input type="password" id="Password" name="Password" placeholder="请设置登录密码">
                    <div class="hint">6~16个字符</div>
                    <!--<input type="text" name="email" class="email" placeholder="请输入验证邮箱">
                    <button class="catch">获取验证码</button> -->
                    <input type="password" id="Password2" name="Password2" placeholder="请再次输入密码">
                    <button onclick="submits()">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
                </form>
                <a href="./login.jsp" class="log">已有账号？登录</a>
            </div>
            <script src="js/jquery-3.3.1.min.js"></script>
            <script>
                function submits() {
                    var reg =/^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
                    var phone = $("#Phone").val();
                    var password = $("#Password").val();
                    var password2 = $("#Password2").val();
                    if(reg.test(phone)){
                        if((password.length <= 16) && (password.length >= 6)){
                            if(password == password2){
                                $('#sign').submit();
                            }else{
                                alert("两次密码不一致!");
                                location.reload();
                            }
                        }else {
                            alert("密码长度错误！");
                            location.reload();
                        }
                    }else {
                        alert("手机号码错误！");
                        location.reload();
                    }

                }

            </script>
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
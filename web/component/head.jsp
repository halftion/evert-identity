<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/12/8
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="common-container">
        <a class="menus" href="./index">首页</a>
        <form action="search" method="post" class="search">
            <input type="text" name="keyword" placeholder="搜索感兴趣的内容">
            <span>&#xe600;</span>
            <input type="submit" value="搜 索">
        </form>
        <c:choose>
            <c:when test="${user!=null}">
                <ul class="list clearfix">
                    <a href="./mytopic" class="personal-news"><img src="${user.headPic}" class="personal-news"><span>个人中心</span></a>
                    <li><a href="./edit.jsp">&#xe625; 我的资料</a></li>
                    <li><a href="./editnote">&#xe634; 创建新留言</a></li>
                    <li><a href="./myreply">&#xe649; 互动</a><i class="personal-letter">99+</i></li>
                    <li><a href="./logout.jsp">&#xe652; 退出</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="sign">
                    <a href="./login.jsp">登录</a>|
                    <a href="./signup.jsp">注册</a>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>
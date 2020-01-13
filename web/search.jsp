<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/12/8
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <title>异闻</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="event" />
        <link href="css/common.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/index.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all"/>
        <script src="js/index.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="./component/head.jsp"%>
        <div style="margin-top: 100px">
            <div class="topics container">
                <c:forEach items="${messages}" var="message">
                    <div class="topic">
                        <img src="${message.headpic}" class="left clearfix">

                        <ul class="date1">
                            <li>${fn:substring(message.creatTime,5,7)}</li>
                            <li>/</li>
                            <li>${fn:substring(message.creatTime,8,10)}</li>
                        </ul>
                        <div class="con">
                            <h3><a href="./note?id=${message.messageid}">${message.title}</a></h3>
                            <p>${message.digest}</p>
                            <ul>
                                <li><i class="iconfont">&#xe60c;</i> 点赞</li>
                                <li><a target="_blank" onclick="open('comment.html','评论','width=900px,height=290px,left=150,top=150,resizable=no,scrollbars=no,status=no,toolbar=no,location=no,menubar=no,menu=no')"><i class="iconfont">&#xe60d;</i> 评论</a></li>
                                <li><a target="_blank" onclick="open('repeat.html','转发','width=900px,height=514px,left=150,top=150,resizable=no,scrollbars=no,status=no,toolbar=no,location=no,menubar=no,menu=no')"><i class="iconfont">&#xe6f3;</i> 转发</a></li>
                                <li><a href="./mytopic?autherid=${message.autherid}"><i class="iconfont">&#xe610;</i>${message.auther}</a></li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@include file="./component/footer.jsp"%>
    </body>
</html>

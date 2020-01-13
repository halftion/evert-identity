<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
        <div>
            <div class="banner">
                <div class="boxs" id="boxs">
                    <div class="box clearfix"><img src="https://i.loli.net/2019/11/12/wMS95T3B812l7n4.jpg" class="pics"></div>
                    <div class="box clearfix"><img src="https://i.loli.net/2019/11/12/Mg5aZieXO9NEI2U.png" class="pics"></div>
                    <div class="box clearfix"><img src="https://i.loli.net/2019/11/13/DhtfNVCG49zvjyE.jpg" class="pics"></div>
                </div>
                <ul>
                    <li id="li1"></li>
                    <li id="li2"></li>
                    <li id="li3"></li>
                </ul>
            </div>

            <ul class="mouths">
                <a href="##"><li class="mouth4">9月</li></a>
                <a href="##"><li class="mouth3">10月</li></a>
                <a href="##"><li class="mouth2">11月</li></a>
                <li class="mouth1">12月</li>
                <a href="##"><li class="mouth2">1月</li></a>
                <a href="##"><li class="mouth3">2月</li></a>
                <a href="##"><li class="mouth4">3月</li></a>
            </ul>

            <div class="topics container">
                <div class="introduce2">
                    <h1>校园留言NOTE</h1>
                    <h2>娱乐/学习/政治/文化</h2>
                </div>

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
                                <li><a href="./note?id=${message.messageid}"><i class="iconfont">&#xe60d;</i> 评论</a></li>
                                <li><a><i class="iconfont">&#xe6f3;</i> 转发</a></li>
                                <li><a href="./mytopic?autherid=${message.autherid}"><i class="iconfont">&#xe610;</i>${message.auther}</a></li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>

                <p class="more container"><a href="###">查看更多》</a></p>
            </div>
        </div>
        <%@include file="./component/footer.jsp"%>
    </body>
</html>
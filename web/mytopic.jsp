<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/12/8
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if(request.getAttribute("subuser")==null){
        request.getRequestDispatcher("/").forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>个人中心</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="" />
        <link href="css/common.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/topic.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/pageNav.css" rel="stylesheet" type="text/css" media="all"/>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    </head>

    <body>
        <%@include file="component/head.jsp"%>
        <div class="container clearfix">
            <div class="cardcase">
                <div class="coverbox">
                    <img src="${subuser.coverPic}">
                    <c:if test="${(user != null)&&(subuser.userid == user.userid)}">
                        <a href="./edit.jsp">编辑封面图片</a>
                    </c:if>
                </div>
                <div class="card">
                    <div class="head-pic-ctn">
                        <img src="${subuser.headPic}">
                        <c:if test="${(user != null)&&(subuser.userid == user.userid)}">
                            <a href="./edit.jsp" class="changhead">点击编辑个人资料</a>
                        </c:if>
                    </div>
                    <div class="user-name">
                        <h1>${subuser.userName}</h1>
                        <img <c:if test="${subuser.sexNumber == 1}">src="./image/boy.svg"</c:if> <c:if test="${subuser.sexNumber == 2}">src="./image/girl.svg"</c:if> <c:if test="${subuser.sexNumber == 0}">src="./image/unknown.svg"</c:if> >
                        <a href="./edit.jsp"><c:if test="${subuser.signature != null}">${subuser.signature}</c:if><c:if test="${subuser.signature == null}">暂无个人签名，点击此处编辑</c:if> <span>&#xe661;</span></a>
                    </div>
                    <c:if test="${(user!=null)&&(subuser.userid == user.userid)}">
                        <a href="./edit.jsp" class="change-card">编辑个人资料</a>
                    </c:if>
                </div>
            </div>
            <div class="topicbox">
                <ul class="topic-menu">
                    <li class="this-page"><a href="./mytopic">话题</a></li>
                    <li><a href="./myreply<c:if test="${(user == null)||(subuser.userid != user.userid)}">?autherid=${subuser.userid}</c:if>">回复</a></li>

                </ul>

                <ul>
                    <c:forEach items="${messages}" var="message">
                        <li class="topic">
                            <h1>${message.title}</h1>
                            <h2>${message.creatTime}</h2>
                            <img src="${message.headpic}">
                            <p>${message.digest}</p>
                            <a href="./note?id=${message.messageid}">阅读全文>></a>
                            <p><span>&#xe661;</span> 666条评论</p>
                            <p><span>&#xe646;</span> 233条收藏</p>
                            <p><span>&#xe672;</span> 123个赞</p>
                            <ul>
                                <i>...</i>
                                <c:if test="${(user!=null)&&(subuser.userid == user.userid)}">
                                    <li><a style="color:#000" href="./editnote?messageid=${message.messageid}">编辑</a></li>
                                </c:if>
                                <c:if test="${(user!=null)&&(subuser.userid == user.userid)}">
                                    <li><a style="color:#000" href="./deleteMessage?messageid=${message.messageid}">删除</a></li>
                                </c:if>
                            </ul>
                        </li>
                    </c:forEach>

                </ul>
            </div>
            <div class="fanbox">
                <c:if test="${(user!=null)&&(user.control == 0)}">
                    <ul class="fantab clearfix" id="tabs">
                        <li class="select" title="fans">
                            <h1>正常用户</h1>
                            <p></p>
                        </li>
                        <li title="focus">
                            <h1>被禁言用户</h1>
                            <p></p>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${(user==null)||(user.control != 0)}">
                    <ul class="fantab clearfix" id="tabs">
                        <li class="select" title="fans">
                            <h1>我的关注</h1>
                            <p></p>
                        </li>
                        <li title="focus">
                            <h1>我的粉丝</h1>
                            <p></p>
                        </li>
                    </ul>
                </c:if>
                <div id="content">
                    <c:if test="${users!=null}">
                        <ul class="friend show" id="fans">
                            <c:forEach items="${users}" var="userlist">
                                <c:if test="${userlist.state == 0}">
                                    <li>
                                    <a href="./mytopic?autherid=${userlist.userid}">${userlist.userName}</a>
                                    <img src="${userlist.headPic}">
                                    <p>${userlist.signature}</p>
                                    <a href="./banSpeaking?id=${userlist.userid}">
                                        X 禁言
                                    </a>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${users!=null}">
                        <ul class="friend" id="focus">
                            <c:forEach items="${users}" var="userlist">
                                <c:if test="${userlist.state == 1}">
                                    <li>
                                        <a href="./mytopic?autherid=${userlist.userid}">${userlist.userName}</a>
                                        <img src="${userlist.headPic}">
                                        <p>${userlist.signature}</p>
                                        <a href="./banSpeaking?id=${userlist.userid}">
                                            取消禁言
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
                <script src="./js/jquery-3.3.1.min.js"></script>
                <script type="text/javascript" src="./js/main.js"></script>
                <%--<a href="###" class="morefriend">--%>
                    <%--更&nbsp;&nbsp;多......--%>
                <%--</a>--%>
            </div>
        </div>
        <%@include file="./component/footer.jsp"%>
    </body>
</html>

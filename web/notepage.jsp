<%--
  Created by IntelliJ IDEA.
  User: halftion
  Date: 2019/11/13
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>留言详情</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="" />
        <link href="css/common.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/note.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all"/>
        <script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <%@include file="component/head.jsp"%>
        <div class="body container">
            <h1>${message.title}</h1>
            <h2>${message.creatTime} 来自：${message.auther}</h2>
            <img src="${message.headpic}">
            <div class="text">
                ${message.content}
            </div>
        </div>
        <c:if test="${user.state == 0}">
            <form class="container reply" method="post" action="editReply" style="margin-bottom:10px">
                <h1>发表评论:</h1>
                <div class="editor" id="editor"></div>
                <input type="text" id="content" name="content" hidden/>
                <input type="text" id="digest" name="digest" hidden/>
                <input type="number" name="messageId" value="${message.messageid}" hidden/>
                <input type="text" name="messageTitle" value="${message.title}" hidden/>
                <input type="text" name="messageDigest" value="${message.digest}" hidden/>
                <input type="text" name="messageAuther" value="${message.auther}" hidden/>
                <button onclick="submits()">提交</button>
                <script type="text/javascript" src="./js/wangEditor.min.js"></script>
                <script type="text/javascript">
                    var E = window.wangEditor;
                    var editor = new E('#editor');
                    var string = "";
                    setTimeout(function(){
                        editor.create();
                        editor.txt.append(string);
                    },0);
                    function submits() {
                        string = escape(editor.txt.html());
                        $("#content").val(string);
                        var digest = editor.txt.text().slice(0,80) + "...";
                        $("#digest").val(digest);
                        $("from").submit();
                    }
                </script>
            </form>
        </c:if>
        <ul class="container" style="margin-bottom:50px">
            <c:forEach items="${replies}" var="reply">
                <li class="comment">
                    <img src="${reply.autherHeadPic}">
                    <div>
                        <h1>${reply.autherName}</h1>
                        <h2>${reply.creatTime}</h2>
                    </div>
                    <p>
                        <c:if test="${reply.replyTo != 0}">回复${reply.replyToUserName}：</c:if>
                        ${reply.content}
                    </p>
                </li>
            </c:forEach>
        </ul>
        <%@include file="./component/footer.jsp"%>
    </body>
</html>

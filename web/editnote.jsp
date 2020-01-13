<%@ page import="Users.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 程宥霖
  Date: 2019/11/13
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(request.getSession().getAttribute("user")==null){
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }else if (((User)request.getSession().getAttribute("user")).getState() == 1){
        response.sendRedirect(request.getContextPath()+"/index");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>编辑留言</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <link href="css/common.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/note.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/reset.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="./js/jquery-3.3.1.min.js"></script>
</head>

    <body>

        <%@include file="component/head.jsp"%>
        <form method="POST" action="messageEdit" class="edit-container">
            <div class="edit-text">
                <input class="edit-title" type="text" name="title" value="${message.title}" placeholder="请输入标题">
                <div class="edit-img">
                    <a href="javascript:$('#headpic').click()"><img id="head" src="${message.headpic}"></a>
                    <input type="text" name="headPic" value="${message.headpic}" hidden>
                    <input type="file" id="headpic" onchange="submitpic()" hidden>
                </div>
                <div id="editor"></div>
                <input type="text" id="content" name="content" value="" hidden>
                <input type="text" id="digest" name="digest" value="" hidden>
                <input type="text" id="messageid" name="messageid" value="${message.messageid}" hidden>
            </div>
            <script>
                function submitpic() {
                    var formData = new FormData();
                    formData.append("smfile",document.getElementById("headpic").files[0]);
                    $.ajax({
                        type : "POST",
                        contentType: false,
                        processData: false,
                        url : "https://sm.ms/api/v2/upload",
                        data : formData,
                        dataType:"json",
                        success :function(response){
                            if(response.code === "success"){
                                alert("上传成功");
                                $("#head").attr('src',response.data.url);
                                $("input[name='headPic']").val(response.data.url);
                            }},
                        error: function () {
                            alert("上传失败")
                        }
                    });
                }
            </script>
            <script type="text/javascript" src="./js/wangEditor.min.js"></script>
            <script type="text/javascript">
                var E = window.wangEditor;
                var editor = new E('#editor');
                var string = unescape("${message.content}");
                setTimeout(function(){
                    editor.create();
                    editor.txt.append(string);
                },0);
                function submits(){
                    string = escape(editor.txt.html());
                    $("#content").val(string);
                    var digest = editor.txt.text().slice(0,80) + "...";
                    $("#digest").val(digest);
                    $("from").submit();
                }
            </script>
            <button onclick="javascript:submits()">提交</button>
        </form>

        <%@include file="./component/footer.jsp"%>
    </body>
</html>

<!--
 * @Author: halftion 
 * @Date: 2019-10-23 08:44:10
 * @Last Modified by: halftion
 * @Last Modified time: 2019-12-7 08:40:47
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if(request.getSession().getAttribute("user")==null){
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>我的资料</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="" />
        <link href="css/common.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/personal.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all"/>
    </head>
    <body>
        <div>
            <%@ include file="component/head.jsp"%>
            <div class="container">
                <form action="personalEdit" method="post" class="cardcase">
                    <div class="coverbox">
                        <img id="cover" src="${user.coverPic}">
                        <a href="javascript:$('#coverpic').click();">编辑封面图片</a>
                        <input type="file" id="coverpic" style="display: none" onchange="submitcoverPic()" />
                        <input type="text" name="coverPic" value="${user.coverPic}" hidden>
                    </div>
                    <div class="editcard">
                        <div class="head-pic-ctn">
                            <img id="head" src="${user.headPic}">
                            <a href="javascript:$('#headpic').click();">点击上传头像</a>
                            <input type="file" id="headpic" style="display: none" onchange="submitheadPic()" />
                            <input type="text" name="headPic" value="${user.headPic}" hidden>
                        </div>
                        <input type="text" value="${user.userName}" id="UserName" name="UserName" placeholder="用户名" />
                        <div class="input signal-input">
                            <h1>性别:</h1>
                            <label>
                                <input type="radio" <c:if test="${user.sexNumber==2}">checked="checked"</c:if> name="sexNumber" value="2" />
                                <span></span>
                            </label>
                            <p>女</p>
                            <label>
                                <input type="radio" name="sexNumber" <c:if test="${user.sexNumber==1}">checked="checked"</c:if>  value="1" />
                                <span></span>
                            </label>
                            <p>男</p>
                            <label>
                                <input type="radio" name="sexNumber" <c:if test="${user.sexNumber==0}">checked="checked"</c:if>  value="0" />
                                <span></span>
                            </label>
                            <p>保密</p>
                        </div>
                        <div class="input signal-input">
                            <h1>年龄:</h1>
                            <i> &#xe661;</i>
                            <input type="text" name="age" placeholder="马上填写，让合适的人找到你" value="${user.age}" />
                        </div>
                        <div class="input signal-input">
                            <h1>个性签名:</h1>
                            <i> &#xe661;</i>
                            <input type="text" name="signature" placeholder="马上填写，让更多的人了解到你哦" value="${user.signature}" />
                        </div>
                        <div class="input signal-input">
                            <h1>居住地址:</h1>
                            <i> &#xe661;</i>
                            <input type="text" name="address" placeholder="与更多的人相遇吧" value="${user.address}" />
                        </div>
                        <div class="input signal-input">
                            <h1>联系方式:</h1>
                            <i> &#xe661;</i>
                            <input type="text" name="phoneNumber" placeholder="让更多的人联系到你吧" value="${user.phoneNumber}" readonly />
                        </div>
                        <div class="input signal-input">
                            <h1>所在行业:</h1>
                            <i> &#xe661;</i>
                            <input type="text" name="profession" placeholder="马上填写，让合适的人找到你" value="${user.profession}" />
                        </div>
                        <div class="input">
                            <h1>个人简介:</h1>
                            <i> &#xe661;</i>
                            <div id="editor"></div>
                            <input id="introduction" name="introduction" value="${user.introduction}" hidden/>
                            <script type="text/javascript" src="./js/wangEditor.min.js"></script>
                            <script type="text/javascript">
                                var E = window.wangEditor;
                                var editor = new E('#editor');
                                var string = unescape("${user.introduction}");
                                setTimeout(function(){
                                    editor.create();
                                    editor.txt.append(string);
                                },0);
                                function submits() {
                                    string = escape(editor.txt.html());
                                    $("#introduction").val(string);
                                    $("from").submit();
                                }
                            </script>
                        </div>
                        <button onclick="javascript:submits()">提交</button>

                        <script src="./js/jquery-3.3.1.min.js"></script>
                        <script>
                            /* 头像提交 */
                            function submitheadPic() {
                                var formData = new FormData();
                                //console.log(document.getElementById("headpic").files)
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
                            /* 封面提交 */
                            function submitcoverPic() {
                                var formData = new FormData();
                                //console.log(document.getElementById("headpic").files)
                                formData.append("smfile",document.getElementById("coverpic").files[0]);
                                $.ajax({
                                    type : "POST",
                                    contentType: false,
                                    processData: false,
                                    url : "https://sm.ms/api/v2/upload",
                                    data : formData,
                                    dataType:"json",
                                    success :function(response){
                                        //console.log(response);
                                        //console.log(response.data.url);
                                        if(response.code === "success"){
                                            alert("上传成功");
                                            $("#cover").attr('src',response.data.url);
                                            $("input[name='coverPic']").val(response.data.url);
                                        }},
                                    error: function () {
                                        alert("上传失败")
                                    }
                                });
                            }
                            /* 服务提交 */

                        </script>
                        <script src="./js/main.js"></script>
                        <a href="./mytopic">返回我的主页></a>
                     </div>
                </form>
            </div>
            <%@ include file="component/footer.jsp"%>
        </div>
    </body>


</html>
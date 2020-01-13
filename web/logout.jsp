<%--
  Created by IntelliJ IDEA.
  User: halftion
  Date: 2019/12/4
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    request.getRequestDispatcher("/index").forward(request, response);
%>

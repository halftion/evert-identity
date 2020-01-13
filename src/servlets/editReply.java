package servlets;

import Users.*;
import messages.*;
import Replys.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editReply")
public class editReply extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/login.jsp";
        int state = 500;
        if(request.getSession().getAttribute("user") != null){
            User user = (User)request.getSession().getAttribute("user");
            try {
                Reply reply = new Reply();
                reply.setMessageId(Integer.parseInt(request.getParameter("messageId")));
                reply.setAutherId(user.getUserid());
                reply.setAutherName(user.getUserName());
                reply.setAutherHeadPic(user.getHeadPic());
                reply.setContent(request.getParameter("content"));
                reply.setMessageTitle(request.getParameter("messageTitle"));
                reply.setMessageDigest(request.getParameter("messageDigest"));
                reply.setMessageAuther(request.getParameter("messageAuther"));
                reply.setDigest(request.getParameter("digest"));
                if(request.getParameter("replyTo") != null){
                    reply.setReplyTo(Integer.parseInt(request.getParameter("replyTo")));
                    reply.setReplyToUserName(request.getParameter("replyToUserName"));
                }
                Replydao replydao = new Replydao(reply);
                replydao.insert();
                Reply replies[] = replydao.getReplyByMessage(reply.getMessageId());
                request.setAttribute("replies",replies);
                message Message = new message();
                if(request.getParameter("messageId") != null){
                    int id = Integer.parseInt(request.getParameter("messageId"));
                    Messagedao messagedao = new Messagedao(Message);
                    Message = messagedao.getmessage(id);
                }
                request.setAttribute("message",Message);
                state = 200;
                url = "/notepage.jsp";
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package servlets;

import Users.User;
import Replys.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteReply")
public class deleteReply extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/myreply.jsp";
        int state = 500;
        try {
            if(request.getParameter("replyid") != null){
                int id = Integer.parseInt(request.getParameter("replyid"));
                if(request.getSession().getAttribute("user") != null){
                    User user = (User)request.getSession().getAttribute("user");
                    Reply reply = new Reply();
                    Replydao replydao = new Replydao(reply);
                    reply = replydao.getReply(id);
                    if(user.getUserid() == reply.getAutherId()){
                        reply.setReplyId(id);
                        replydao = new Replydao(reply);
                        replydao.delete();
                        state = 200;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

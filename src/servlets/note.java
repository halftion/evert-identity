package servlets;

import Replys.Reply;
import Replys.Replydao;
import messages.Messagedao;
import messages.message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 留言详情处理 */
@WebServlet("/note")
public class note extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/default.jsp";
        int state = 500;
        try {
            if(request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                message Message = new message();
                Messagedao messagedao = new Messagedao(Message);
                Message = messagedao.getmessage(id);
                request.setAttribute("message",Message);
                Reply reply = new Reply();
                Replydao replydao = new Replydao(reply);
                Reply replies[] = replydao.getReplyByMessage(id);
                request.setAttribute("replies",replies);
                state = 200;
                url = "/notepage.jsp";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}

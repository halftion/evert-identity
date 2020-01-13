package servlets;

import messages.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editnote")
public class editnote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/default.jsp";
        int state = 500;
        try {
            message Message = new message();
            if(request.getParameter("messageid") != null){
                int id = Integer.parseInt(request.getParameter("messageid"));
                Messagedao messagedao = new Messagedao(Message);
                Message = messagedao.getmessage(id);
                Message.setContent(EscapeUnescape.escape(Message.getContent()));
            }
            request.setAttribute("message",Message);
            state = 200;
            url = "/editnote.jsp";
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

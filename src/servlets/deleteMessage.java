package servlets;

import Users.*;
import messages.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteMessage")
public class deleteMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/mytopic.jsp";
        int state = 500;
        try {
            if(request.getParameter("messageid") != null){
                int id = Integer.parseInt(request.getParameter("messageid"));
                if(request.getSession().getAttribute("user") != null){
                    User user = (User)request.getSession().getAttribute("user");
                    message Message = new message();
                    Messagedao messagedao = new Messagedao(Message);
                    Message = messagedao.getmessage(id);
                    if(user.getUserid() == Message.getAutherid()){
                        Message.setMessageid(id);
                        messagedao = new Messagedao(Message);
                        messagedao.delete();
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

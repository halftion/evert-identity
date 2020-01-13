package servlets;

import Users.*;
import Replys.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myreply")
public class myreply extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/404.html";
        int state = 500;
        User users[] = null;
        try {
            int autherid = 0;
            if(request.getParameter("autherid")!=null){
                autherid = Integer.parseInt(request.getParameter("autherid"));
                state = 200;
                url = "/myreply.jsp";
                User user = new User();
                userdao userd = new userdao(user);
                user = userd.getUser(autherid);
                request.setAttribute("subuser",user);
            }else if(request.getSession().getAttribute("user")!=null){
                User user = (User)request.getSession().getAttribute("user");
                if (user.getControl() == 0){
                    userdao userd = new userdao(user);
                    users = userd.getUsers();
                }
                autherid = user.getUserid();
                state = 200;
                url = "/myreply.jsp";
                request.setAttribute("subuser",user);
            }else {
                url = "/default.jsp";
            }
            Reply reply = new Reply();
            Replydao replydao = new Replydao(reply);
            Reply replies[] = replydao.getReplyByAutner(autherid);
            request.setAttribute("replies",replies);
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("users",users);
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package servlets;

import Users.*;
import messages.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 个人中心 */
@WebServlet("/mytopic")
public class mytopic extends HttpServlet {
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
                url = "/mytopic.jsp";
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
                url = "/mytopic.jsp";
                request.setAttribute("subuser",user);
            }else {
                url = "/default.jsp";
            }
            message Message = new message();
            Messagedao messagedao = new Messagedao(Message);
            message Messages[] = messagedao.getmessagesByAutherid(autherid);
            request.setAttribute("messages",Messages);
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

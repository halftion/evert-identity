package servlets;

import Users.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/banSpeaking")
public class banSpeaking extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/mytopic.jsp";
        int state = 500;
        try {
            if(request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                if(request.getSession().getAttribute("user") != null){
                    User admin = (User)request.getSession().getAttribute("user");
                    User user = new User();
                    if(admin.getControl() == 0){
                        userdao userd = new userdao(user);
                        user = userd.getUser(id);
                        if (user.getState()==0){
                            user.setState(1);
                        }else {
                            user.setState(0);
                        }
                        userd = new userdao(user);
                        userd.update();
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

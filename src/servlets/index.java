package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messages.*;

import java.io.IOException;

/* 主页数据获取 */
@WebServlet("/index")
public class index extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/404.html";
        int state = 500;
        try {
            message Message = new message();
            Messagedao messagedao = new Messagedao(Message);
            message Messages[] = messagedao.getmessages(0);
            request.setAttribute("messages",Messages);
            state = 200;
            url = "/index.jsp";
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

package servlets;

import Users.*;
import messages.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 留言板编辑 */
@WebServlet("/messageEdit")
public class messageEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/login.jsp";
        int state = 500;
        try {
            if (request.getSession().getAttribute("user") != null){
                User user = (User)request.getSession().getAttribute("user");
                int messageid = Integer.parseInt(request.getParameter("messageid"));
                String title = request.getParameter("title");
                String headPic = request.getParameter("headPic");
                String content = request.getParameter("content");
                String digest = request.getParameter("digest");
                message Message = new message();
                Message.setDigest(digest);
                Message.setAuther(user.getUserName());
                Message.setAutherid(user.getUserid());
                Message.setHeadpic(headPic);
                Message.setTitle(title);
                Message.setContent(content);
                Messagedao messagedao = new Messagedao(Message);
                int sta = 0;
                if (messageid == 0){
                    sta = messagedao.insert();
                }else {
                    Message = messagedao.getmessage(messageid);
                    if(user.getUserid() == Message.getAutherid()){
                        Message.setDigest(digest);
                        Message.setAuther(user.getUserName());
                        Message.setAutherid(user.getUserid());
                        Message.setHeadpic(headPic);
                        Message.setTitle(title);
                        Message.setContent(content);
                        Message.setMessageid(messageid);
                        messagedao = new Messagedao(Message);
                        sta = messagedao.update();
                    }
                }
                if (sta != 0) state = 200;
                url = "/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        response.sendRedirect(request.getContextPath() + url);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

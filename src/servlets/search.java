package servlets;

import messages.Messagedao;
import messages.message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 搜索处理 */
@WebServlet("/search")
public class search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/";
        int state = 500;
        try {
            String keyword = "";
            if(request.getParameter("keyword")!=null){
                keyword = request.getParameter("keyword");
            }
            message Message = new message();
            Messagedao messagedao = new Messagedao(Message);
            message Messages[] = messagedao.search(keyword);
            request.setAttribute("messages",Messages);
            url = "/search.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
